package com.machines.machines_api.services;

import com.machines.machines_api.models.entity.Search;

import java.util.List;

public interface SearchService {
    List<Search> getAll();

    Search create(Search search);
}
