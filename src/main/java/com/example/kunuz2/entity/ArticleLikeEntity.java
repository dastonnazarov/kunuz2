package com.example.kunuz2.entity;


import com.example.kunuz2.enums.ArticleLikeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "article_like")
@Entity
public class ArticleLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private Integer profile_id;

    @OneToMany
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private Integer comment_id;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "status")
    private ArticleLikeStatus status;

}
