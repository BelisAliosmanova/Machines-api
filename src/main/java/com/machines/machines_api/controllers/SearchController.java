package com.machines.machines_api.controllers;

import com.machines.machines_api.models.entity.Search;
import com.machines.machines_api.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/searches")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Search>> getAll() {
        return ResponseEntity.ok(searchService.getAll());
    }
}
