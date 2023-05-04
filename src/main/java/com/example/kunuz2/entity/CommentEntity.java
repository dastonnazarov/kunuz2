package com.example.kunuz2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "content")
    private String content;

    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "article_id")
    private String articleId;


}
