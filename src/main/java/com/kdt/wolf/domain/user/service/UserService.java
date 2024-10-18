package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.link.dao.LinkDao;
import com.kdt.wolf.domain.link.dto.LinkResponse;
import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.report.service.ReportAction;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserDetailResponse;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserPreviewResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserLinkUpdateRequest;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final LinkDao linkDao;

    public UserProfileDetailResponse getUserProfileDetail(Long userId) {
        UserEntity user = userDao.findById(userId);
        List<ExternalLinksEntity> links = getExternalLinks(user);

        return new UserProfileDetailResponse(
                user.getUserId(),
                user.getNickname(),
                user.getName(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getActivityMetrics().toResponse(),
                user.getJobTitle(),
                user.getOrganization(),
                user.getExperience(),
                user.getInterests(),
                user.getRefundAccount(),
                user.getIntroduction(),
                links.stream().map(LinkResponse::new).toList()
        );
    }

    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userDao.findById(userId);
        return new UserProfileResponse(
                user.getUserId(),
                user.getNickname(),
                user.getProfilePicture(),
                user.getActivityMetrics().toResponse()
        );
    }

    public void completeSignUpProcess(long userId, SignUpRequest request) {
        userDao.updateUser(userId, request);
    }

    @Transactional
    public UserProfileDetailResponse updateMyProfile(Long userId, UserUpdateRequest request) {
        if (!userId.equals(request.id())) {
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }

        UserEntity user = userDao.findById(userId);

        // 외부 링크 업데이트
        updateExternalLinks(user, request.links());

        // 최신 외부 링크 목록 조회
        List<ExternalLinksEntity> updatedLinks = getExternalLinks(user);

        // 유저 정보 업데이트
        userDao.updateUser(user.updateProfile(request));
        return new UserProfileDetailResponse(
                user.getUserId(),
                user.getNickname(),
                user.getName(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getActivityMetrics().toResponse(),
                user.getJobTitle(),
                user.getOrganization(),
                user.getExperience(),
                user.getInterests(),
                user.getRefundAccount(),
                user.getIntroduction(),
                updatedLinks.stream().map(LinkResponse::new).toList()
        );
    }

    private void updateExternalLinks(UserEntity user, List<UserLinkUpdateRequest> linkRequests) {

        // 기존 링크를 Map으로 변환
        Map<Long, ExternalLinksEntity> existingLinks = getExternalLinks(user).stream()
                .collect(Collectors.toMap(ExternalLinksEntity::getLinkId, Function.identity()));

        // 새 링크 추가
        List<ExternalLinksEntity> newLinks = linkRequests.stream()
                .filter(linkRequest -> linkRequest.linkId() == null)
                .map(linkRequest -> new ExternalLinksEntity(
                        user,
                        LinkType.valueOf(linkRequest.linkType().toUpperCase()),
                        linkRequest.linkUrl())
                )
                .toList();

        // 기존 링크 업데이트
        Set<Long> requestLinkIds = linkRequests.stream()
                .filter(linkRequest -> linkRequest.linkId() != null)
                .map(linkRequest -> {
                    ExternalLinksEntity link = existingLinks.get(linkRequest.linkId());
                    if (link != null) {
                        link.updateLink(linkRequest);
                    }
                    return linkRequest.linkId();
                })
                .collect(Collectors.toSet());

        // 삭제할 링크 처리
        existingLinks.keySet().stream()
                .filter(linkId -> !requestLinkIds.contains(linkId))
                .forEach(linkDao::deleteLink);

        // 새 링크 저장
        linkDao.saveAll(newLinks);
    }

    public List<UserPreviewResponse> getUserList() {
        return userDao.findAllUserPreview();
    }

    public UserDetailResponse getUserDetail(Long userId) {
        return userDao.findUserDetail(userId);
    }

    public Status banUser(Long userId) {
        return userDao.changeUserStatus(userId, Status.BANNED);
    }

    public Status unbanUser(Long userId) {
        return userDao.changeUserStatus(userId, Status.ACTIVE);
    }

    public Status suspendUser(Long userId) {
        Status status = userDao.changeUserStatus(userId, Status.SUSPENDED);
        if(status == Status.SUSPENDED) {
            userDao.suspendUser(userDao.findById(userId));
        }
        return status;
    }

    private List<ExternalLinksEntity> getExternalLinks(UserEntity user) {
        return linkDao.findAll(user);
    }

    public Long penaltyUser(Long userId, ReportAction reportAction, String processContent) {
        //TODO : processContent는 어디에 저장 ? 알림 기능도 연결 X
        UserEntity user = userDao.findById(userId);

        if(reportAction == ReportAction.NOTHING) {
            return userDao.activateUser(user);
        }

        return  userDao.panaltyUser(user, reportAction);
    }
}
