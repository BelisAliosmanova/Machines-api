package com.machines.machines_api.services.impl;

import com.machines.machines_api.models.entity.Search;
import com.machines.machines_api.repositories.SearchRepository;
import com.machines.machines_api.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;

    @Override
    public List<Search> getAll() {
        return searchRepository.findAll();
    }

    @Override
    public Search create(Search search) {
        return searchRepository.save(search);
    }
}
