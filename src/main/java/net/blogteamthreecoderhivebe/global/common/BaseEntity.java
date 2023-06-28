package net.blogteamthreecoderhivebe.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends BaseTimeEntity {

    @Column(length = 100, updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(length = 100)
    @LastModifiedBy
    protected String modifiedBy;
}
