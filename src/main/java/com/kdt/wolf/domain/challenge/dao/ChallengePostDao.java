package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest;
import com.kdt.wolf.domain.challenge.entity.*;
import com.kdt.wolf.domain.challenge.repository.*;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public List<ChallengeRegistrationEntity> findCertifiableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiableChallenges(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findCertifiedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiedChallenges(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findCompletedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCompletedChallenges(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findPayableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findPayableChallenge(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findJoinableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findJoinableChallenges(groupId, userId);
    }

    public List<ChallengePostEntity> findAvailableChallenges(Long groupId) {
        return challengeRegistrationQueryRepository.findApplicableChallenges(groupId);
    }

    // 챌린지 신청(그룹장)
    public void createChallengeRegistration(ChallengeRegistrationRequest request) {
        GroupPostEntity group = groupPostRepository.findById(request.getGroupPostId()).orElseThrow(NotFoundException::new);
        ChallengePostEntity challengePost = challengePostRepository.findById(request.getChallengePostId()).orElseThrow(NotFoundException::new);

        ChallengeRegistrationEntity entity = new ChallengeRegistrationEntity(
                challengePost,
                group,
                Long.parseLong(request.getChallengeAmount() == null ? "0" :request.getChallengeAmount())
        );

        challengeRegistrationQueryRepository.save(entity);
    }

    // 챌린지 참여
    public void createChallengeRegistrations(ChallengeRegistrationRequest request, long userId) {
        ChallengeRegistrationEntity registration = challengeRegistrationQueryRepository
                .findChallengeRegistration(request.getGroupPostId(), request.getChallengePostId());
        UserEntity user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        GroupChallengeParticipantEntity entity = new GroupChallengeParticipantEntity(registration, user);
        entity.updatePaymentStatus();
        groupChallengeParticipantRepository.save(entity);
    }


    // 챌린지 인증
    @Transactional
    public void updateVerification(ChallengeVerificationRequest request, long userId) {
        ChallengeRegistrationEntity registration = challengeRegistrationQueryRepository
                .findChallengeRegistration(request.getGroupPostId(), request.getChallengePostId());
        UserEntity user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        if(request.getStatus().equals("Y")){
            GroupChallengeParticipantEntity entity = groupChallengeParticipantRepository.findGroupChallengeParticipantEntity(registration, user);
            entity.updateParticipationStatus();
            // 아래 문장을 주석처리해도 반영이 되는지 확인. (Entity로 선언한 영속상태라면 반영이 된다고 함)
            // groupChallengeParticipantRepository.save(entity);
        }

        VerificationEntity verificationEntity = new VerificationEntity(
                registration,
                registration.getChallengePost(),
                user,
                request.getCertificationNo(),
                request.getInstitutionName(),
                request.getVerificationContent()
        );

        verificationRepository.save(verificationEntity);
    }

    // 챌린지 생성
    public void createChallenge(ChallengeCreationRequest request,Long userId){
        ChallengePostEntity entity = new ChallengePostEntity(
                userId,
                request.getImg(),
                request.getTitle(),
                request.getContent(),
                request.getManner(),
                request.getAwardContent(),
                request.getDeadline()
        );
        challengePostRepository.save(entity);
    }

    // 챌린지 수정
    public void updateChallenge(ChallengeCreationRequest request,Long challengePostId){
        ChallengePostEntity entity = new ChallengePostEntity(
                challengePostRepository.findById(challengePostId).orElseThrow(NotFoundException::new).getChallengePostId(),
                request.getImg(),
                request.getTitle(),
                request.getContent(),
                request.getManner(),
                request.getAwardContent(),
                request.getDeadline()
        );

        challengePostRepository.save(entity);
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

        PaymentEntity paymentEntity = new PaymentEntity(registration, user);
        challengePaymentRepository.save(paymentEntity);

    }
}
