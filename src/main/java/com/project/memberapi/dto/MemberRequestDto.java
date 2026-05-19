package com.project.memberapi.dto;

import lombok.Getter;

// 요청용 DTO
@Getter
public class MemberRequestDto {

    private String name;
    private int age;
    private String mbti;
}
