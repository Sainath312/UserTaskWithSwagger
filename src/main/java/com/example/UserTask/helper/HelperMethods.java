package com.example.UserTask.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HelperMethods {
    public Pageable createPageable(int page, int size, String sortBy) {
        Sort sort = null;
        sort = Sort.by(Sort.Direction.ASC, "id");
        return PageRequest.of(page, size, sort);
    }
}
