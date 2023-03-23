package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.SkillRequirement;
import net.blogteamthreecoderhivebe.entity.Technology;
import net.blogteamthreecoderhivebe.entity.UserTechnology;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CoderHiveRepositoryTest {

    @Autowired
    private UserRepository userRepository;
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
    private UserApplyRepository userApplyRepository;

    @Autowired
    private UserTechnologyRepository userTechnologyRepository;


    @Test
    public void isNotNullRepository() {
        assertThat(userRepository).isNotNull();
        assertThat(postRepository).isNotNull();
        assertThat(replyRepository).isNotNull();
        assertThat(jobRepository).isNotNull();
        assertThat(likePostRepository).isNotNull();
        assertThat(locationRepository).isNotNull();
        assertThat(postJobRepository).isNotNull();
        assertThat(skillRequirementRepository).isNotNull();
        assertThat(technologyRepository).isNotNull();
        assertThat(userApplyRepository).isNotNull();
        assertThat(userTechnologyRepository).isNotNull();
    }



}
