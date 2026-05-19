package com.project.memberapi.controller;

import com.project.memberapi.dto.MemberRequestDto;
import com.project.memberapi.dto.MemberResponseDto;
import com.project.memberapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> saveMember(@RequestBody MemberRequestDto requestDto) {
        log.info("[API - LOG] POST /api/members 요청 수신");
        MemberResponseDto responseDto = memberService.saveMember(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        log.info("[API - LOG] GET /api/members/{} 요청 수신", id);
        MemberResponseDto responseDto = memberService.getMember(id);
        return ResponseEntity.ok(responseDto);
    }
}
