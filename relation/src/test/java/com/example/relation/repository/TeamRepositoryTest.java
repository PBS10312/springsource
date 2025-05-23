package com.example.relation.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.team.Team;
import com.example.relation.entity.team.TeamMember;
import com.example.relation.repository.TeamMemberRepository;
import com.example.relation.repository.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Test
    public void insertTest() {
        // 팀(부모) 정보 삽입
        Team team = teamRepository.save(Team.builder().teamName("team2").build());

        // 회원(자식) 정보 삽입
        // teamMemberRepository.save(TeamMember.builder().userName("user1").team(team).build());
    }

    @Test
    public void insertTest2() {
        // 팀 정보 삽입
        Team team = teamRepository.findById(1L).get();

        // 회원 정보 삽입
        teamMemberRepository.save(TeamMember.builder().userName("user2").team(team).build());
    }

    @Test
    public void readTest1() {
        // 팀 조회
        Team team = teamRepository.findById(1L).get();
        // 멤버 조회
        TeamMember teamMember = teamMemberRepository.findById(1L).get();

        System.out.println(team);
        System.out.println(teamMember);

    }

    @Test
    public void readTest2() {

        // 멤버의 팀 정보 조회
        TeamMember teamMember = teamMemberRepository.findById(1L).get();
        System.out.println(teamMember);
        // 객체 그래프 탐색
        System.out.println(teamMember.getTeam());

    }

    @Test
    public void readTest3() {
        Team team = Team.builder()
                .id(2L)
                .build();
        List<TeamMember> list = teamMemberRepository.findByTeam(team);
        System.out.println(list); // [TeamMember(id=1, userName=user1), TeamMember(id=2, userName=user1)]

    }

    @Test
    public void findByMemberEqualTeamTest() {
        List<Object[]> result = teamMemberRepository.findByMemberEqualTeam(2L);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void updateTest() {
        // 1번 팀원의 팀 변경 => 2번팀으로 변경
        TeamMember member = teamMemberRepository.findById(1L).get();
        Team team = teamRepository.findById(2L).get();
        member.setTeam(team);

        teamMemberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // 1번 팀 삭제
        // 무결성 제약조건(C##JAVA.FK9UBP79EI4TV4CRD0R9N7U5I6E)이 위배되었습니다- 자식 레코드가 발견되었습니다
        // teamRepository.deleteById(1L);

        // 해결 방안
        // 1. 삭제하려고 하는 팀의 팀원들을 다른 팀으로 이동 (null 값 지정)
        // 2. 자식 삭제 후 부모 삭제

        // 1번 팀원의 팀을 2번 팀으로 변경
        TeamMember member = teamMemberRepository.findById(2L).get();
        Team team = teamRepository.findById(2L).get();
        member.setTeam(team);
        teamMemberRepository.save(member);

        // 팀 삭제
        teamRepository.deleteById(1L);
    }

    // ----------------------------------------------------
    // 양방향 연관관계 : @OneToMany
    // ㄴ 단방향 2개 연것
    // -----------------------------------------------------

    // 팀 정보 => 회원 정보 접근
    // @Transactional
    @Test
    public void readByTest1() {

        // 멤버의 팀 정보 조회
        // org.hibernate.LazyInitializationException: failed to lazily initialize a
        // collection of role: com.example.relation.entity.team.Team.members: could not
        // initialize proxy - no Session
        // LazyInitializationException : 이 오류가 났을때
        // 1) 첫번째 방법 : @Transactional 쓰기
        // 2) FetchType 변경

        Team team = teamRepository.findById(2L).get();
        System.out.println(team);
        // 객체 그래프 탐색
        team.getMembers().forEach(member -> System.out.println(member));

    }
    // ----------------------------------------------------
    // 양방향
    // 영속성 전이 : Cascade
    // -----------------------------------------------------

    @Test
    public void insertTest3() {

        Team team = Team.builder().teamName("team3").build();

        TeamMember teamMember = TeamMember.builder().userName("홍길동").team(team).build();
        team.getMembers().add(teamMember);

        // teamMemberRepository.save(member);
        teamRepository.save(team);
    }

    @Test
    public void deleteTest2() {
        // 부모 삭제 시 자식도 같이 삭제
        // deleteTest() 와 비교
        teamRepository.deleteById(4L);
    }
}
