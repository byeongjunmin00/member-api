package com.project.memberapi.service;

import com.project.memberapi.dto.MemberRequestDto;
import com.project.memberapi.dto.MemberResponseDto;
import com.project.memberapi.entity.Member;
import com.project.memberapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 저장
    public MemberResponseDto saveMember(MemberRequestDto requestDto) {
        Member member = new Member(requestDto.getName(), requestDto.getAge(), requestDto.getMbti());
        Member savedMember = memberRepository.save(member);
        log.info("[API - LOG] 팀원 저장 완료: id={}, name={}", savedMember.getId(), savedMember.getName());
        return new MemberResponseDto(savedMember);
    }

    // 조회
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀원이 존재하지 않습니다. id=" + id));
        log.info("[API - LOG] 팀원 조회 완료: id={}, name={}", member.getId(), member.getName());
        return new MemberResponseDto(member);
    }
}
