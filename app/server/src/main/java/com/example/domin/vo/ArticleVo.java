package com.example.domin.vo;

import com.example.domin.entity.Article;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ArticleVo {
    private Long id;
    private String title;
    private String content;
    private Instant updatedAt;
    private List<TagVo> tags;

    public ArticleVo(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.updatedAt = article.getUpdatedAt();
        this.tags = article.getTags().stream().map(TagVo::new).toList();
    }
}
