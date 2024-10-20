package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest.ChallengeCreateRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest.VerificationRequest;
import com.kdt.wolf.domain.challenge.entity.*;
import com.kdt.wolf.domain.challenge.repository.*;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengePostDao {

    private final ChallengeRegistrationQueryRepository challengeRegistrationQueryRepository;
    private final ChallengePostRepository challengePostRepository;
    private final GroupPostRepository groupPostRepository;
    private final UserRepository userRepository;
    private final GroupChallengeParticipantRepository groupChallengeParticipantRepository;
    private final ChallengePaymentRepository challengePaymentRepository;
    private final VerificationRepository verificationRepository;

    // 챌린지(단일) 불러오기
    public ChallengePostEntity findById(Long challengePostId){
        return challengePostRepository.findById(challengePostId).orElseThrow(NotFoundException::new);
    }

    // 챌린지 목록 불러오기(관리자)
    public List<ChallengePostEntity> findAll(){
        return challengePostRepository.findAll();
    }

    // 챌린지 목록 불러오기(회원)
//    public List<ChallengeRegistrationEntity> findCertifiableChallenges(Long groupId, Long userId) {
//        return challengeRegistrationQueryRepository.findCertifiableChallenges(groupId, userId);
//    }
//
//    public List<ChallengeRegistrationEntity> findCertifiedChallenges(Long groupId, Long userId) {
//        return challengeRegistrationQueryRepository.findCertifiedChallenges(groupId, userId);
//    }
//
//    public List<ChallengeRegistrationEntity> findCompletedChallenges(Long groupId, Long userId) {
//        return challengeRegistrationQueryRepository.findCompletedChallenges(groupId, userId);
//    }
//
//    public List<ChallengeRegistrationEntity> findPayableChallenges(Long groupId, Long userId) {
//        return challengeRegistrationQueryRepository.findPayableChallenge(groupId, userId);
//    }
//
//    public List<ChallengeRegistrationEntity> findJoinableChallenges(Long groupId, Long userId) {
//        return challengeRegistrationQueryRepository.findJoinableChallenges(groupId, userId);
//    }

    public Page<ChallengeRegistrationEntity> findChallengesByStatus(Long groupId, Long userId, ChallengeStatus status, Pageable pageable) {
        return switch (status) {
            case CERTIFICATION -> challengeRegistrationQueryRepository.findCertifiableChallenges(groupId, userId, pageable);
            case CERTIFICATION_COMPLETE -> challengeRegistrationQueryRepository.findCertifiedChallenges(groupId, userId, pageable);
            case RESULT_CONFIRM -> challengeRegistrationQueryRepository.findCompletedChallenges(groupId, userId, pageable);
            case PAY -> challengeRegistrationQueryRepository.findPayableChallenge(groupId, userId, pageable);
            case PARTICIPATE -> challengeRegistrationQueryRepository.findJoinableChallenges(groupId, userId, pageable);
            default -> throw new IllegalArgumentException("Unexpected status: " + status);
        };
    }

    public Page<ChallengePostEntity> findAvailableChallenges(Long groupId, Pageable pageable) {
        //ChallengeStatus.APPLY
        return challengeRegistrationQueryRepository.findApplicableChallenges(groupId, pageable);
    }


    // 챌린지 신청(그룹장)
    public void createChallengeRegistration(ChallengeRegistrationRequest request, Long userId) {
        GroupPostEntity group = groupPostRepository.findById(request.getGroupPostId()).orElseThrow(NotFoundException::new);
        ChallengePostEntity challengePost = challengePostRepository.findById(request.getChallengePostId()).orElseThrow(NotFoundException::new);

        ChallengeRegistrationEntity registration = new ChallengeRegistrationEntity(
                challengePost,
                group,
                Long.parseLong(request.getChallengeAmount() == null ? "0" :request.getChallengeAmount())
        );

        challengeRegistrationQueryRepository.save(registration);
    }

    // 챌린지 참여
    public void createChallengeRegistrations(ChallengeRegistrationRequest request, long userId) {
        ChallengeRegistrationEntity registration = challengeRegistrationQueryRepository
                .findChallengeRegistration(request.getGroupPostId(), request.getChallengePostId());
        UserEntity user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        GroupChallengeParticipantEntity entity = new GroupChallengeParticipantEntity(registration, user);
        groupChallengeParticipantRepository.save(entity);
    }


    // 챌린지 인증
    @Transactional
    public Long updateVerification(VerificationRequest request, long id) {
        ChallengeRegistrationEntity registration = challengeRegistrationQueryRepository
                .findChallengeRegistration(request.groupPostId(), request.challengePostId());
        VerificationEntity verificationEntity = verificationRepository.findById(id).orElseThrow(NotFoundException::new);
        UserEntity user = userRepository.findByNickname(request.nickname()).orElseThrow(UserNotFoundException::new);

//        VerificationEntity verificationEntity = new VerificationEntity(
//                registration,
//                registration.getChallengePost(),
//                user,
//                request.getCertificationNo(),
//                request.getInstitutionName(),
//                request.getVerificationContent()
//        );
        boolean status = request.status().equals("success");
        GroupChallengeParticipantEntity entity = groupChallengeParticipantRepository.findGroupChallengeParticipantEntity(registration, user);
        entity.updateParticipationStatus();
        groupChallengeParticipantRepository.save(entity);
        verificationEntity.updateVerification(status);
//        if(request.status().equals("success")) {
//            verificationEntity.updateVerification();
//        };

        return verificationRepository.save(verificationEntity).getVerificationId();
    }

    // 챌린지 생성
    public Long createChallenge(ChallengeCreateRequest request,Long userId){
        ChallengePostEntity entity = ChallengePostEntity.builder()
                .userId(userId)
                .img(request.img())
                .title(request.title())
                .content(request.content())
                .manner(request.manner())
                .awardContent(request.awardContent())
                .deadline(request.deadline())
                .build();
        return challengePostRepository.save(entity).getChallengePostId();
    }

    // 챌린지 수정
    public Long updateChallenge(ChallengeCreateRequest request,Long challengePostId){
        ChallengePostEntity entity = challengePostRepository.findById(challengePostId).orElseThrow(NotFoundException::new);
        entity.updateChallengePost(request);
        return challengePostRepository.save(entity).getChallengePostId();
    }

    // 챌린지 삭제
    public void deleteChallenge(Long challengePostId){
        challengePostRepository.deleteById(challengePostId);
    }

    // 챌린지 결제
    public void payChallenge(ChallengePaymentRequest request, Long userId){
        ChallengeRegistrationEntity registration = challengeRegistrationQueryRepository
                .findChallengeRegistration(request.getGroupPostId(), request.getChallengePostId());
        UserEntity user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        if(request.getPayStatus().equals("Y")){
            GroupChallengeParticipantEntity participantEntity = groupChallengeParticipantRepository
                    .findGroupChallengeParticipantEntity(registration, user);
            participantEntity.updatePaymentStatus();
            groupChallengeParticipantRepository.save(participantEntity);
        }

        PaymentEntity paymentEntity = new PaymentEntity(registration, user, request.getAmount());
        challengePaymentRepository.save(paymentEntity);
    }

    // 결제 정보 조회
    public PaymentEntity getPayment(Long paymentId){
        return challengePaymentRepository.findById(paymentId).orElseThrow(NotFoundException::new);
    }

    public List<VerificationPreview> getAllVerifications() {
        return verificationRepository.findAll()
                .stream().map(
                        verification -> new VerificationPreview(
                                verification.getVerificationId(),
                                verification.getUser().getNickname(),
                                verification.getChallengePost().getTitle(),
                                verification.getRegistration().getGroupPost().getName(),
                                verification.getCreatedTime().toLocalDate().toString(),
                                verification.isVerification() ? "인증 성공" : "인증 실패"
                        )
                ).toList();
    }

    public VerificationDetail getVerification(Long verificationId) {
        VerificationEntity verification = verificationRepository.findById(verificationId).orElseThrow(NotFoundException::new);
        return new VerificationDetail(
                verification.getVerificationId(),
                verification.getRegistration().getGroupPost().getGroupPostId(),
                verification.getChallengePost().getChallengePostId(),
                verification.getUser().getNickname(),
                verification.getChallengePost().getTitle(),
                verification.getCertificationNo(),
                verification.getInstitutionName(),
                verification.getVerificationContent(),
                verification.getCreatedTime().toLocalDate().toString(),
                verification.isVerification() ? "인증 성공" : "인증 실패"
        );
    }
}
