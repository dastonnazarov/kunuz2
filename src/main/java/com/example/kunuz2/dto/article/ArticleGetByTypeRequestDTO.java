package com.example.kunuz2.dto.article;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleGetByTypeRequestDTO {
    private List<String> idList;
}
