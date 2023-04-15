package com.example.controller;

import com.example.api.ApiResult;
import com.example.domin.param.TagParams;
import com.example.domin.vo.TagVo;
import com.example.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tags")
@AllArgsConstructor
public class TagController {
    final private TagService tagService;

    @GetMapping
    public ApiResult getAllTags() {
        return ApiResult.ok(tagService.findAll());
    }

    @PostMapping
    public ApiResult createTag(@RequestBody @Valid TagParams tagParams) {
        tagService.create(tagParams);
        return ApiResult.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ApiResult deleteTag(@PathVariable("id") Long id) {
        tagService.deleteById(id);
        return ApiResult.ok("ok");
    }

    @GetMapping("/{id}")
    public ApiResult<TagVo> getTagById(@PathVariable("id") Long id) {
        TagVo tagVo = tagService.findById(id);
        return ApiResult.ok(tagVo);
    }

    @PutMapping
    public ApiResult<TagVo> updateTag(@RequestBody TagParams tagParams) {
        TagVo tagVo = tagService.update(tagParams);
        return ApiResult.ok(tagVo);
    }
}
