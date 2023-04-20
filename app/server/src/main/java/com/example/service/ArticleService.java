package com.example.service;

import com.example.domin.entity.Article;
import com.example.domin.entity.Tag;
import com.example.domin.param.ArticleParam;
import com.example.domin.vo.ArticleVo;
import com.example.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    final private ArticleRepository articleRepository;

    final private TagService tagService;


    public List<ArticleVo> findAll() {
        List<Article> all = articleRepository.findAll();
        return all.stream().map(ArticleVo::new).toList();
    }

    public void create(ArticleParam articleParam) {
        Article article = new Article();
        article.setTitle(articleParam.getTitle());
        article.setContent(articleParam.getContent());
        List<Tag> tags = tagService.findAllById(articleParam.getTagIds());
        article.setTags(tags);
        articleRepository.save(article);
    }

    public ArticleVo findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return new ArticleVo(article);
    }

    public void deleteById(Long id) {
        // TODO: 优化删除找不到的报错信息
        articleRepository.deleteById(id);
    }

    public ArticleVo update(ArticleParam articleParam) {
        Article article = articleRepository.findById(articleParam.getId()).orElseThrow();
        article.setTitle(articleParam.getTitle());
        article.setContent(articleParam.getContent());
        List<Tag> tags = tagService.findAllById(articleParam.getTagIds());
        article.setTags(tags);
        articleRepository.save(article);
        return new ArticleVo(article);
    }
}
