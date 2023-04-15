package com.example.controller;

import com.example.api.ApiResult;
import com.example.domin.vo.ArticleVo;
import com.example.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
