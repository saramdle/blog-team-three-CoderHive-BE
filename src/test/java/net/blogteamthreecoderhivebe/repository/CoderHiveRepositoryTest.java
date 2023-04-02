package net.blogteamthreecoderhivebe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.blogteamthreecoderhivebe.config.TestJpaConfig;
import net.blogteamthreecoderhivebe.config.TestQueryDslConfig;
import net.blogteamthreecoderhivebe.entity.Job;
import net.blogteamthreecoderhivebe.entity.Member;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
@Import({TestJpaConfig.class, TestQueryDslConfig.class})
public class CoderHiveRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private LikePostRepository likePostRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PostJobRepository postJobRepository;

    @Autowired
    private SkillRequirementRepository skillRequirementRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private MemberApplyRepository memberApplyRepository;

    @Autowired
    private MemberTechnologyRepository memberTechnologyRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @DisplayName("isNotNull 테스트")
    @Test
    public void isNotNullRepository() {
        assertThat(memberRepository).isNotNull();
        assertThat(postRepository).isNotNull();
        assertThat(replyRepository).isNotNull();
        assertThat(jobRepository).isNotNull();
        assertThat(likePostRepository).isNotNull();
        assertThat(locationRepository).isNotNull();
        assertThat(postJobRepository).isNotNull();
        assertThat(skillRequirementRepository).isNotNull();
        assertThat(technologyRepository).isNotNull();
        assertThat(memberApplyRepository).isNotNull();
        assertThat(memberTechnologyRepository).isNotNull();
    }

    @DisplayName("job 테스트")
    @Test
    public void testDataInsertingWorkFine() throws Exception {
        // Job
        final Job job = Job.builder()
                .main("백엔드 개발")
                .detail("웹 서버")
                .build();


    }

    @DisplayName("Member - 유저정보 조회 - Id")
    @Test
    public void getMemberById() {
        Job job = Job.of("백엔드 개발", "웹 서버");
        System.out.println("wait");
        jobRepository.save(job);
        //long id = 0;
        String nickname = "한샘";
//        Member member = Member.of(job, "example@coderhive.com", MemberLevel.BEGINNER, MemberCareer.ASSOCIATE, nickname);

        Job savedJob = jobRepository.findById((long)1).get();

        Member member = Member.builder()
                        .job(savedJob)
                        .email("example@coderhive.com")
                        .level(MemberLevel.BEGINNER)
                        .career(MemberCareer.ASSOCIATE)
                        .nickname(nickname)
                        .build();



        memberRepository.save(member);
        System.out.println("wait");
        Optional<Member> savedMember = memberRepository.findByNickname(nickname);

        assertThat(member.equals(savedMember));

    }
/*

    @DisplayName("QueryFactory 테스트")
    @Test
    public void QueryFactoryTest() {
        Job job = Job.of("백엔드 개발", "웹 서버");
        System.out.println("wait");
        jobRepository.save(job);
        //long id = 0;
        String nickname = "한샘";
//        Member member = Member.of(job, "example@coderhive.com", MemberLevel.BEGINNER, MemberCareer.ASSOCIATE, nickname);

        Job savedJob = jobRepository.findById((long)1).get();

        Member mem = Member.builder()
                .job(savedJob)
                .email("example@coderhive.com")
                .level(MemberLevel.BEGINNER)
                .career(MemberCareer.ASSOCIATE)
                .nickname(nickname)
                .build();

        memberRepository.save(mem);

        Member fetch = queryFactory.selectFrom(member)
                .fetchOne();

        assertThat(member.equals(mem));
    }

    @DisplayName("Member - 유저/직무/기술 정보 조회 - Id")
    @Test
    public void getMemberByIdWithTechnologyAndApply() {

    }

    @DisplayName("Member - 유저 정보 조회 - ")
    @Test
    public void queryDsl_findMemberByNickname() {

    }

*/


}
