package com.example.board.repositroy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.board.dto.PageRequestDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.MemberRole;
import com.example.board.entity.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void listReplyTest() {
        Board board = Board.builder().bno(100L).build();
        List<Reply> list = replyRepository.findByBoardOrderByRno(board);
        System.out.println(list);
    }

    @Test
    public void insertMemberTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .name("USER" + i)
                    .build();
            member.addMemberRole(MemberRole.USER);

            if (i > 5) {
                member.addMemberRole(MemberRole.MANAGER);
            }
            if (i > 7) {
                member.addMemberRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void insertBoardTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            int no = (int) (Math.random() * 10) + 1;
            Member member = Member.builder().email("user" + no + "@gmail.com").build();

            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .member(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void insertReplyTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            long no = (int) (Math.random() * 100) + 1;
            Board board = Board.builder().bno(no).build();

            int id = (int) (Math.random() * 10) + 1;
            Member member = Member.builder().email("user" + id + "@gmail.com").build();

            Reply reply = Reply.builder()
                    .text("Reply...." + i)
                    .replyer(member)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }

    @Test
    public void readBoardTest() {
        Board board = boardRepository.findById(2L).get();
        System.out.println(board);
    }

    // @Transactional
    // @Test
    // public void readBoardTest2() {
    // Board board = boardRepository.findById(2L).get();
    // System.out.println(board.getMember());
    // }

    // @Transactional
    // @Test
    // public void readBoardTest3() {
    // Board board = boardRepository.findById(46L).get();
    // System.out.println(board.getMember());
    // System.out.println(board.getReplies());
    // }

    @Transactional
    @Test
    public void readReplyTest() {
        Reply reply = replyRepository.findById(2L).get();
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    // querydsl
    @Transactional
    @Test
    public void listTest() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(0)
                .size(10)
                .type("tc")
                .keyword("title")
                .build();
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.list(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

        // Page<Object[]> result = boardRepository.list();
        // for (Object[] objects : result) {
        // Board board = (Board) objects[0];
        // Member member = (Member) objects[1];
        // Long replyCount = (Long) objects[2];
        // System.out.println(board);
        // System.out.println(member);
        // System.out.println(replyCount);
        // }
    }

    @Transactional
    @Test
    public void rowTest() {
        Object[] result = boardRepository.getBoardByBno(40L);
        System.out.println(Arrays.toString(result));
    }
}
