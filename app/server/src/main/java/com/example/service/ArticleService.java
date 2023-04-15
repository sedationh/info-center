package com.example.service;

import com.example.domin.entity.Article;
import com.example.domin.vo.ArticleVo;
import com.example.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public List<ArticleVo> findAll() {
        List<Article> all = articleRepository.findAll();
        return all.stream().map(ArticleVo::new).toList();
    }
}
