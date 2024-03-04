package com.example.UserTask.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HelperMethods {
    public Pageable createPageable(int page, int size, String sortBy) {
        // Parse and apply optional custom sort
        Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty()) {
            String[] sortParts = sortBy.split(",");
            if (sortParts.length > 0) {
                String field = sortParts[0];
                Sort.Direction direction = (sortParts.length > 1) ?
                        Sort.Direction.fromString(sortParts[1]) : Sort.Direction.ASC;
                sort = Sort.by(direction, field);
            }
        }

        // Create Pageable object with default sort if no custom sort provided
        if (sort == null) {
            sort = Sort.by(Sort.Direction.ASC, "id");
        }

        return PageRequest.of(page, size, sort);
    }
}
