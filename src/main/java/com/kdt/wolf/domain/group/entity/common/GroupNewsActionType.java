package com.kdt.wolf.domain.group.entity.common;

public enum GroupNewsActionType {
    JOIN_GROUP("그룹에 참여했습니다."),
    BECOME_LEADER("스터디/프로젝트장이 되었습니다."),
    APPLY_CHALLENGE("챌린지가 신청되었습니다."),
    LEAVE_GROUP("그룹에서 탈퇴했습니다."),
    UPDATE_GROUP_INFO("그룹 정보를 수정했습니다."),
    ADD_SCHEDULE("일정이 추가되었습니다.");

    private final String actionMessage;

    GroupNewsActionType(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    public String getMessage() {
        return actionMessage;
    }
}
