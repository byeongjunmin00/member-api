package com.project.memberapi.dto;

import com.project.memberapi.entity.Member;
import lombok.Getter;

// 응답용 DTO
@Getter
public class MemberResponseDto {

    private Long id;
    private String name;
    private int age;
    private String mbti;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.mbti = member.getMbti();
    }
}
