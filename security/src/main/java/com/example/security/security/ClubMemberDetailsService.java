package com.example.security.security;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.entity.ClubMember;
import com.example.security.repository.ClubMemberRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ClubMemberDetailsService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    public ClubMemberDetailsService(ClubMemberRepository clubMemberRepository) {
        this.clubMemberRepository = clubMemberRepository;
    }

    // 로그인 처리 해주는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username {}", username);

        ClubMember clubmember = clubMemberRepository.findByEmailAndFromSocial(username, false);

        if (clubmember == null)
            throw new UsernameNotFoundException("이메일 확인");

        // entity => dto 로 바꾸는작업
        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(clubmember.getEmail(),
                clubmember.getPassword(), clubmember.isFromSocial(),
                clubmember.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()));

        clubAuthMemberDTO.setName(clubmember.getName());
        clubAuthMemberDTO.setFromSocial(clubmember.isFromSocial());

        return clubAuthMemberDTO;
    }

}
