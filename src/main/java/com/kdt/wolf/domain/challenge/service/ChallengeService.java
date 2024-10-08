package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;
import java.util.HashMap;
import java.util.Map;

import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.entity.PaymentEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;
    private final ChallengePaymentRepository challengePaymentRepository;

    //챌린지 불러오기
    public ChallengePreview getChallenge(Long challengePostId){
        ChallengePostEntity post = challengePostDao.findById(challengePostId);
        return new ChallengePreview(
                post.getChallengePostId(),
                post.getImg(),
                post.getTitle(),
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

    // 챌린지 목록 불러오기
    public Map<String, List<ChallengePreview>> getAllChallenges(Long groupId, Long userId){
        Map<String, List<ChallengePreview>> challengesByStatus = new HashMap<>();

        challengesByStatus.put(ChallengeStatus.CERTIFICATION.getDescription(),
                challengePostDao.findCertifiableChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.CERTIFICATION;
                            return new ChallengePreview(
                                    post.getChallengePost().getChallengePostId(),
                                    post.getChallengePost().getImg(),
                                    post.getChallengePost().getTitle(),
                                    post.getRegistrationDate(),
                                    post.getChallengePost().getDeadline(),
                                    status
                            );
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.PAY.getDescription(),
                challengePostDao.findPayableChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.PAY;
                            return new ChallengePreview(
                                    post.getChallengePost().getChallengePostId(),
                                    post.getChallengePost().getImg(),
                                    post.getChallengePost().getTitle(),
                                    post.getRegistrationDate(),
                                    post.getChallengePost().getDeadline(),
                                    status
                            );
                        })
                        .toList()
            );

        challengesByStatus.put(ChallengeStatus.CERTIFICATION_COMPLETE.getDescription(),
                challengePostDao.findCertifiedChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.APPLY;
                            return new ChallengePreview(
                                    post.getChallengePost().getChallengePostId(),
                                    post.getChallengePost().getImg(),
                                    post.getChallengePost().getTitle(),
                                    post.getRegistrationDate(),
                                    post.getChallengePost().getDeadline(),
                                    status
                            );
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.RESULT_CONFIRM.getDescription(),
                challengePostDao.findCompletedChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.RESULT_CONFIRM;
                            return new ChallengePreview(
                                    post.getChallengePost().getChallengePostId(),
                                    post.getChallengePost().getImg(),
                                    post.getChallengePost().getTitle(),
                                    post.getRegistrationDate(),
                                    post.getChallengePost().getDeadline(),
                                    status
                            );
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.APPLY.getDescription(),
                challengePostDao.findAvailableChallenges(groupId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.APPLY;
                            return new ChallengePreview(
                                    post.getChallengePostId(),
                                    post.getImg(),
                                    post.getTitle(),
                                    post.getCreatedTime().toLocalDate(),
                                    post.getDeadline(),
                                    status
                            );
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.PARTICIPATE.getDescription(),
                challengePostDao.findJoinableChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.PARTICIPATE;
                            return new ChallengePreview(
                                    post.getChallengePost().getChallengePostId(),
                                    post.getChallengePost().getImg(),
                                    post.getChallengePost().getTitle(),
                                    post.getRegistrationDate(),
                                    post.getChallengePost().getDeadline(),
                                    status
                            );
                        })
                        .toList()
        );
        return challengesByStatus;
    }

    // 챌린지 신청
    public void createChallengeRegistration(ChallengeRegistrationRequest request){
        challengePostDao.createChallengeRegistration(request);
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
    public void registerChallenge(ChallengeCreationRequest request, Long userId){
        challengePostDao.createChallenge(request, userId);
    }

    // 챌린지 수정
    public void updateChallenge(ChallengeCreationRequest request, Long challengePostId){
        challengePostDao.updateChallenge(request, challengePostId);
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


}
