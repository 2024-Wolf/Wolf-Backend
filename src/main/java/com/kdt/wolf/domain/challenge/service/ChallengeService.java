package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;

import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest.ChallengeCreateRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.PaymentEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePaymentRepository;
import com.kdt.wolf.global.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;
    private final ChallengePaymentRepository challengePaymentRepository;

    //챌린지 불러오기
    public ChallengeDto.ChallengeDetail getChallenge(Long challengePostId){
        ChallengePostEntity post = challengePostDao.findById(challengePostId);
        return new ChallengeDto.ChallengeDetail(
                post.getChallengePostId(),
                post.getTitle(),
                post.getCreatedTime().toLocalDate(),
                post.getDeadline(),
                post.getContent(),
                post.getManner(),
                post.getAwardContent()
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
                null
        );
    }

    // 챌린지 목록 불러오기
    public List<ChallengePreview> getAllChallenges(){
        List<ChallengePostEntity> dataList = challengePostDao.findAll();

        return dataList.stream().map(data -> new ChallengePreview(
           data.getChallengePostId(),
           data.getImg(),
           data.getTitle(),
           data.getCreatedTime().toLocalDate(),
           data.getDeadline(),
           null
        )).toList();
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
        challengePostDao.createChallengeRegistration(request, userId);
    }

    // 챌린지 참여
    public void createChallengeRegistrations(ChallengeRegistrationRequest request, Long userId){
        challengePostDao.createChallengeRegistrations(request, userId);
    }

    // 챌린지 인증
    public void updateVerification(ChallengeVerificationRequest request, Long userId){

        challengePostDao.updateVerification(request, userId);
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


    public List<VerificationPreview> getAllVerifications() {
        return challengePostDao.getAllVerifications();
    }

    public VerificationDetail getVerification(Long verificationId) {
        return challengePostDao.getVerification(verificationId);
    }
}
