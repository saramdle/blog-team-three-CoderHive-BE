package net.blogteamthreecoderhivebe.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.blogteamthreecoderhivebe.entity.Job;
import net.blogteamthreecoderhivebe.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DisplayName("Table 정보 불러오기")
@Transactional
@SpringBootTest
class TableInfoServiceTest {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    void searchJobs() {
        List<Job> jobs = jobRepository.findAll();
        System.out.println(jobs.size());
        List<Map<String, Object>> groupedJobs = jobs.stream()
                .collect(Collectors.groupingBy(
                        Job::getMain,
                        Collectors.mapping(
                                job -> Map.of("jobId", job.getId(), "fields", job.getDetail()),
                                Collectors.toList()
                        )
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("main", entry.getKey());
                    result.put("details", entry.getValue());
                    return result;
                })
                .toList();

        System.out.println(groupedJobs);

    }

}