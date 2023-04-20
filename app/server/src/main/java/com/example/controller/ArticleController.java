package com.example.controller;

import com.example.api.ApiResult;
import com.example.domin.param.ArticleParam;
import com.example.domin.vo.ArticleVo;
import com.example.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    final private ArticleService articleService;

    @GetMapping
    public ApiResult<List<ArticleVo>> getAllArticles() {
        return ApiResult.ok(articleService.findAll());
    }

    @PostMapping
    public ApiResult<String> createArticle(@RequestBody @Valid ArticleParam articleParam) {
        articleService.create(articleParam);
        return ApiResult.ok("创建成功");
    }

    @GetMapping("/{id}")
    public ApiResult<ArticleVo> getArticleById(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.findById(id);
        return ApiResult.ok(articleVo);
    }

    @PutMapping
    public ApiResult<ArticleVo> updateArticle(@RequestBody ArticleParam articleParam) {
        ArticleVo articleVo = articleService.update(articleParam);
        return ApiResult.ok(articleVo);
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteById(id);
        return ApiResult.ok(null);
    }

}
