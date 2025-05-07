package com.example.jpa.repository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.QBoard;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryDslTest() {
        // Q파일 사용
        QBoard board = QBoard.board;

        // where b.title = 'title1'
        // System.out.println(boardRepository.findAll(board.title.eq("title1")));

        // where b.title like 'title1%'
        // System.out.println(boardRepository.findAll(board.title.startsWith("t")));

        // where b.title like '%title1'
        // System.out.println(boardRepository.findAll(board.title.endsWith("title1")));

        // where b.title like '%title1%'
        // System.out.println(boardRepository.findAll(board.title.contains("title1")));

        // where b.title like '%title1%' and b.bno > 0 order by bno desc
        // findAll(Predicate predicate , Sort sort)
        Iterable<Board> boards = boardRepository.findAll(board.title.contains("title1")
                .and(board.bno.gt(0L)), Sort.by("bno").descending());
        System.out.println(boards);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(board.bno.gt(0L), pageable);
        System.out.println("page size " + result.getSize());
        System.out.println("page TotalPage " + result.getTotalPages());
        System.out.println("page TotalElements " + result.getTotalElements());
        System.out.println("page content " + result.getContent());
    }

    @Test
    public void queryMethodTest() {
        System.out.println(boardRepository.findByWriter("user4"));
        // System.out.println(boardRepository.findByTitle("title"));
        // System.out.println(boardRepository.findByWriterStartingWith("user")); //
        // user%
        // System.out.println(boardRepository.findByWriterEndingWith("user")); // %user
        // System.out.println(boardRepository.findByWriterContaining("user")); // %user%

        // System.out.println(boardRepository.findByWriterContainingOrContentContaining("5",
        // "9"));
        // System.out.println(boardRepository.findByWriterContainingAndContentContaining("5",
        // "9"));
        // System.out.println(boardRepository.findByBnoGreaterThan(5L));
        // System.out.println(boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L));
        // System.out.println(boardRepository.findByBnoBetween(5L, 10L));

        List<Object[]> result = boardRepository.findByTitle("title");
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            String title = (String) objects[0];
            String writer = (String) objects[1];
            System.out.println("title : " + title + "writer : " + writer);
            System.out.println("=======================================");
        }

    }

    // CRUD
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 100).forEach(i -> {

            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("writer" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {
        // Memo memo = Memo.builder().mno(1L).memoText("memoText update").build();
        Board board = boardRepository.findById(3L).get();
        board.setTitle("Title update");
        boardRepository.save(board);
    }

    @Test
    public void readTest() {
        Board board = boardRepository.findById(3L).get();
        System.out.println(board);
    }

    @Test
    public void listTest() {
        // boardRepository.findAll().forEach(board -> System.out.println(board));

        // pageNumber : 0 => 1 page
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.findAll(pageable).forEach(board -> System.out.println(board));
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(10L);
    }

}
