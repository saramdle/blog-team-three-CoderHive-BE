package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Technology {
    @Id
    @Column(name = "technology_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;
}
