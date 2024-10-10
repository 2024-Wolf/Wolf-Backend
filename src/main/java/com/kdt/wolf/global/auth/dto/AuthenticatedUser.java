package com.kdt.wolf.global.auth.dto;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import java.time.LocalDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class AuthenticatedUser implements UserDetails {

    private final transient UserEntity user;
    private final UserRoleType userType;

    public AuthenticatedUser(UserEntity user, UserRoleType userType) {
        this.user = user;
        this.userType = userType;
    }

    // 유저의 권한 목록을 반환합니다. 권한이 없으면 빈 리스트를 반환합니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한이 있으면 여기에 추가하는 로직이 들어갈 수 있음
        if (userType == UserRoleType.ADMIN) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    // 사용자의 계정이 만료되지 않았는지 확인합니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자의 계정이 잠겨 있지 않은지 확인합니다.
    @Override
    public boolean isAccountNonLocked() {
        if(user.getStatus() == Status.SUSPENDED) {
            return user.getSuspensionDate().isAfter(LocalDate.now());
        }
        return user.getStatus() != Status.BANNED;
    }

    // 사용자의 자격 증명(비밀번호)이 만료되지 않았는지 확인합니다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자가 활성화되어 있는지 여부를 반환합니다.
    @Override
    public boolean isEnabled() {
        return user.getStatus() == Status.ACTIVE;
    }

    // 추가로 사용자 정보를 반환하기 위한 커스텀 메서드도 만들 수 있음
    public String getEmail() {
        return user.getEmail();
    }

    public String getNickname() {
        return user.getNickname();
    }

    public Long getUserId() {
        return user.getUserId();
    }
}
