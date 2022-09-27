package com.search.statutesearching.controller;

import com.search.statutesearching.dto.reponse.ResponseDto;
import com.search.statutesearching.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/search")
@RestController
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/{keyword}")
    public ResponseDto<?> search(@PathVariable String keyword){
        return searchService.search(keyword);
    }

}