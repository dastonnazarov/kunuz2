package com.example.kunuz2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article_save")
public class ArticleSaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profile_id;
    @Column(name = "article_id")
    private String article_id;
    @Column(name = "created_date")
    private LocalDateTime created_date;

}
