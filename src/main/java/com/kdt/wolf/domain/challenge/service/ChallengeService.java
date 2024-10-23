package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeAdminPreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeAdminPageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;

import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest.ChallengeCreateRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest.VerificationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.PaymentEntity;
import com.kdt.wolf.domain.challenge.entity.VerificationEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePaymentRepository;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupNewsActionType;
import com.kdt.wolf.domain.group.service.GroupNewsService;
import com.kdt.wolf.global.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;
    private final ChallengePaymentRepository challengePaymentRepository;
    private final GroupNewsService  groupNewsService;
    private final GroupPostDao  groupPostDao;

    public String CheckStatus(ChallengePostEntity post){
        LocalDateTime now = LocalDateTime.now();

        // 시작 시간, 종료 시간을 기준으로 상태 결정
        if (now.isBefore(post.getCreatedTime())) {
            return  "진행 전";
        } else if (now.isAfter(post.getDeadline().atStartOfDay())) {
            return  "종료";
        } else {
            return  "진행 중";
        }
    }
    //챌린지 불러오기
    public ChallengeDetail getChallenge(Long challengePostId){
        ChallengePostEntity post = challengePostDao.findById(challengePostId);
        return new ChallengeDetail(
                post.getChallengePostId(),
                post.getImg(),
                post.getTitle(),
                post.getContent(),
                post.getManner(),
                post.getAwardContent(),
                post.getCreatedTime().toLocalDate(),
                post.getDeadline(),
                CheckStatus(post)


        );
    }

    //챌린지 단일 조회(관리자)
    public ChallengeDetail getChallengeDetail(Long challengePostId){
        ChallengePostEntity post = challengePostDao.findById(challengePostId);
        return new ChallengeDetail(
                challengePostId,
                post.getImg(),
                post.getTitle(),
                post.getContent(),
                post.getManner(),
                post.getAwardContent(),
                post.getCreatedTime().toLocalDate(),
                post.getDeadline(),
                CheckStatus(post)
        );
    }

    // 챌린지 목록 불러오기
    public ChallengeAdminPageResponse getAllChallenges(Pageable pageable){
        Page<ChallengePostEntity> challenges = challengePostDao.findAll(pageable);

        if(challenges.isEmpty()){
            return new ChallengeAdminPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new ChallengeAdminPageResponse(
                challenges.getContent().stream()
                        .map(challengeEntity -> new ChallengeAdminPreview(challengeEntity.getChallengePostId(), challengeEntity.getImg(), challengeEntity.getTitle(), challengeEntity.getCreatedTime().toLocalDate(), challengeEntity.getDeadline(), CheckStatus(challengeEntity)))
                        .toList(),
                new PageResponse(challenges)
        );
    }

    // 챌린지 목록 불러오기(그룹)
    public ChallengePageResponse getChallengesByStatus(ChallengeStatus status, Long groupId, Long userId,
                                                       Pageable pageable){
        Page<ChallengePreview> challenges;
        List<ChallengePreview> challengePreview;
        if(status == ChallengeStatus.APPLY) {
            challengePreview = challengePostDao.findAvailableChallenges(groupId, pageable)
                    .stream()
                    .map(post -> new ChallengePreview(
                            post.getChallengePostId(),
                            post.getImg(),
                            post.getTitle(),
                            post.getCreatedTime().toLocalDate(),
                            post.getDeadline(),
                            status
                    )).toList();
        } else {
            challengePreview = challengePostDao.findChallengesByStatus(groupId, userId, status, pageable)
                    .stream()
                    .map(post -> new ChallengePreview(
                            post.getChallengePost().getChallengePostId(),
                            post.getChallengePost().getImg(),
                            post.getChallengePost().getTitle(),
                            post.getRegistrationDate(),
                            post.getChallengePost().getDeadline(),
                            status
                    ))
                    .toList();
        }
        if(challengePreview.isEmpty()){
            return new ChallengePageResponse(List.of(), new PageResponse(Page.empty()));
        }
        challenges = new PageImpl<>(challengePreview, pageable, challengePreview.size());
        return new ChallengePageResponse(challenges.toList(), new PageResponse(challenges));
    }

    // 챌린지 신청
    public void createChallengeRegistration(ChallengeRegistrationRequest request, Long userId){
        GroupPostEntity group = groupPostDao.findById(request.getGroupPostId());
        ChallengePostEntity challengePost = challengePostDao.findById(request.getChallengePostId());
        challengePostDao.createChallengeRegistration(group, challengePost, request.getChallengeAmount());
        groupNewsService.createGroupNews(
                group,
                challengePost.getTitle() + GroupNewsActionType.APPLY_CHALLENGE
        );
    }

    // 챌린지 참여
    public void createChallengeRegistrations(ChallengeRegistrationRequest request, Long userId){
        challengePostDao.createChallengeRegistrations(request, userId);
    }

    // 챌린지 인증
    public Long updateVerification(VerificationRequest request, Long id){

        return challengePostDao.updateVerification(request, id);
    }

    // 챌린지 생성
    public Long  registerChallenge(ChallengeCreateRequest request, Long userId){
        return challengePostDao.createChallenge(request, userId);
    }

    // 챌린지 수정
    public Long updateChallenge(ChallengeCreateRequest request, Long challengePostId){
        return challengePostDao.updateChallenge(request, challengePostId);
    }

    // 챌린지 삭제
    public void deleteChallenge(Long challengePostId){
        challengePostDao.deleteChallenge(challengePostId);
    }

    // 챌린지 결제
    public void challengePayment(ChallengePaymentRequest request, Long userId){
        challengePostDao.payChallenge(request, userId);
    }

    // 챌린지 결제 단일 조회
    public PaymentResponse getPayment(Long paymentId){
        PaymentEntity paymentEntity = challengePostDao.getPayment(paymentId);
        return new PaymentResponse(
                paymentId,
                paymentEntity.getUser().getName(),
                paymentEntity.getRegistration().getGroupPost().getName(),
                paymentEntity.getRegistration().getChallengePost().getTitle(),
                paymentEntity.getRegistration().getChallengeAmount(),
                paymentEntity.getPaymentDate()
        );
    }


    public VerificationPageResponse getAllVerifications(Pageable pageable) {
        Page<VerificationEntity> verifications = challengePostDao.getAllVerifications(pageable);

        if(verifications.isEmpty()){
            return new VerificationPageResponse(List.of(), new PageResponse(Page.empty()));
        }
        return new VerificationPageResponse(
                verifications.getContent().stream()
                        .map(verificationEntity -> new VerificationPreview(verificationEntity.getVerificationId(), verificationEntity.getUser().getNickname(), verificationEntity.getChallengePost().getTitle(), verificationEntity.getRegistration().getGroupPost().getName(), verificationEntity.getCreatedTime().toLocalDate().toString(), verificationEntity.isVerification() ? "인증 성공" : "인증 실패"))
                        .toList(),
                new PageResponse(verifications)
        );
    }

    public VerificationDetail getVerification(Long verificationId) {
        return challengePostDao.getVerification(verificationId);
    }
}
