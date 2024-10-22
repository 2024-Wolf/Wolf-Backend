package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.repository.GroupNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupNewsDao {
    private final GroupNewsRepository groupNewsRepository;
}
