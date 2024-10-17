package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class UserDataInitializer  implements CommandLineRunner {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 1) {
            return;
        }
        insertRandomUsers();
        System.out.println("User 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    protected void insertRandomUsers() {
        List<UserEntity> users = List.of(
                new UserEntity("testNickname", "testName", "testEmail", "testImageUrl", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("하늘빛", "이하늘", "skyblue@example.com", "profile1.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("맑은봄", "김봄", "springclear@example.com", "profile2.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("여름밤", "박여름", "summernight@example.com", "profile3.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("겨울눈", "최겨울", "wintereye@example.com", "profile4.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("푸른하늘", "정하늘", "bluesky@example.com", "profile5.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("반짝별", "이별", "starshine@example.com", "profile6.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("솔바람", "김솔", "pinewind@example.com", "profile7.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("푸른바다", "박바다", "blueocean@example.com", "profile8.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("초록빛", "이초록", "greenlight@example.com", "profile9.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("노을빛", "최노을", "sunsetglow@example.com", "profile10.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("햇살가득", "김햇살", "fullsun@example.com", "profile11.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("가을바람", "정가을", "autumnwind@example.com", "profile12.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("별빛밤", "이별빛", "starlight@example.com", "profile13.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("노란달", "박달", "yellowmoon@example.com", "profile14.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("은하수", "최은하", "milkyway@example.com", "profile15.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("구름처럼", "김구름", "likecloud@example.com", "profile16.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("아침햇살", "정아침", "morningsun@example.com", "profile17.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("맑은하늘", "이맑음", "clearsky@example.com", "profile18.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("초록들", "박들", "greenfield@example.com", "profile19.jpg", SocialType.GOOGLE, Status.ACTIVE),
                new UserEntity("차가운바람", "최바람", "coldwind@example.com", "profile20.jpg", SocialType.GOOGLE, Status.ACTIVE)
        );

        userRepository.saveAll(users);
    }

}
