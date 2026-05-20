package com.project.memberapi.service;

import com.project.memberapi.dto.MemberRequestDto;
import com.project.memberapi.dto.MemberResponseDto;
import com.project.memberapi.entity.Member;
import com.project.memberapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

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

    // 프로필 이미지 업로드
    public String uploadProfileImage(Long id, MultipartFile file) throws IOException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀원이 존재하지 않습니다. id=" + id));

        String key = s3Service.upload(file);
        member.updateProfileImageUrl(key);
        memberRepository.save(member);

        log.info("[API - LOG] 프로필 이미지 업로드 완료: id={}", id);
        return key;
    }

    // 프로필 이미지 Presigned URL 조회
    public String getProfileImageUrl(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀원이 존재하지 않습니다. id=" + id));

        if (member.getProfileImageUrl() == null) {
            throw new IllegalArgumentException("프로필 이미지가 없습니다. id=" + id);
        }

        log.info("[API - LOG] 프로필 이미지 조회: id={}", id);
        return s3Service.getPresignedUrl(member.getProfileImageUrl());
    }
}
