package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.QuestionBoardRepository;
import com.kdt.wolf.domain.group.repository.QuestionCommentRepository;
import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.report.repository.ReportCategoryRepository;
import com.kdt.wolf.domain.report.repository.ReportRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class ReportDataInitializer implements CommandLineRunner {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final GroupPostRepository groupPostRepository;
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (reportRepository.count() >= 1) {
            System.out.println("Report 더미 데이터가 이미 존재합니다.");
            return;
        }
        insertRandomReports();
        System.out.println("Report 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    protected void insertRandomReports() {
        List<ReportEntity> reports = generateDummyReports();
        reportRepository.saveAll(reports);
    }

    public List<ReportEntity> generateDummyReports() {

        // 1. UserEntity 더미 데이터 생성 (생성자 사용)
        UserEntity reporter = new UserEntity("reporterNick", "Reporter Name", "reporter@example.com", "/images/reporter.png", SocialType.GOOGLE, Status.ACTIVE);
        UserEntity reportedUser = new UserEntity("reportedNick", "Reported User", "reported@example.com", "/images/reported.png", SocialType.GOOGLE, Status.ACTIVE);

        userRepository.save(reporter);
        userRepository.save(reportedUser);
        userRepository.flush();

        // 2. GroupPostEntity 더미 데이터 생성 (생성자 사용)
        GroupPostEntity groupPost = new GroupPostEntity(
                "Backend Study Group", reporter, GroupType.STUDY,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31),
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15),
                "Short intro for backend study", "backend, java",
                "Preferred Java experience", 10, "/images/thumbnail.png",
                "Backend Development", "Group study for backend",
                "Please adhere to the rules", 'Y'
        );
        groupPostRepository.save(groupPost);
        groupPostRepository.flush();

        // 3. QuestionBoardEntity 더미 데이터 생성 (생성자 사용)
        QuestionBoardEntity question = new QuestionBoardEntity(
                groupPost, reporter, BoardType.QUESTION, LocalDateTime.now(),
                "How to set up a Spring Boot application?", "/images/question.png"
        );
        questionBoardRepository.save(question);
        questionBoardRepository.flush();

        // 4. QuestionCommentEntity 더미 데이터 생성 (생성자 사용)
        QuestionCommentEntity comment = new QuestionCommentEntity(
                question, null, reporter, LocalDateTime.now(),
                "You can refer to the Spring documentation.", "/images/comment.png"
        );
        questionCommentRepository.save(comment);
        questionCommentRepository.flush();

        // 5. ReportCategoryEntity 더미 데이터 생성 (생성자 사용)
        ReportCategoryEntity category = new ReportCategoryEntity("Inappropriate content");
        reportCategoryRepository.save(category);
        reportCategoryRepository.flush();

        // 6. 다양한 리포트 유형에 대한 더미 데이터 생성 (생성자 사용)
        ReportEntity reportForUser = new ReportEntity(reporter, reportedUser, category, "User violated community guidelines.");

        ReportEntity reportForGroupPost = new ReportEntity(reporter, groupPost, category, "This post contains inappropriate content.");

        ReportEntity reportForReply = new ReportEntity(reporter, comment, category, "This reply is offensive.");

        ReportEntity reportForQuestion = new ReportEntity(reporter, question, category, "This question violates the rules.");

        // 더미 데이터 리스트 반환
        return List.of(reportForUser, reportForGroupPost, reportForReply, reportForQuestion);
    }
}
