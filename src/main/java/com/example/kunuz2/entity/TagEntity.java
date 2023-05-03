package com.example.kunuz2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "moderator_id")
    private Integer moderatorId;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<ArticleEntity> entityList;


}
