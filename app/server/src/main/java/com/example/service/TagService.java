package com.example.service;

import com.example.domin.entity.Tag;
import com.example.domin.param.TagParams;
import com.example.domin.vo.TagVo;
import com.example.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    final private TagRepository tagRepository;

    public List<TagVo> findAll() {
        return tagRepository.findAll().stream().map(TagVo::new).toList();
    }

    public List<Tag> findAllById(Long[] ids) {
        return tagRepository.findAllById(Arrays.asList(ids));
    }

    public void create(TagParams tagParams) {
        Tag tag = new Tag();
        tag.setName(tagParams.getName());
        tag.setColor(tagParams.getColor());
        tagRepository.save(tag);
    }

    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    public TagVo findById(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        return new TagVo(tag);
    }

    public TagVo update(TagParams tagParams) {
        Tag tag = tagRepository.findById(tagParams.getId()).orElseThrow();
        tag.setName(tagParams.getName());
        tag.setColor(tagParams.getColor());
        tagRepository.save(tag);
        return new TagVo(tag);
    }
}
