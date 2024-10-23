package com.kdt.wolf.domain.group.dto;

public class GroupNewsDto {
    public record GroupNews(
        Long id,
        String newsContent,
        String createDate
    ) { }

}
