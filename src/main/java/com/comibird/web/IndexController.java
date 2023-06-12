package com.comibird.web;

import com.comibird.service.post.PostService;
import com.comibird.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postUpdate(@PathVariable("id") Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}

