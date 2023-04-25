package net.blogteamthreecoderhivebe.domain.info.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Job{
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String main;
    private String detail;
}
