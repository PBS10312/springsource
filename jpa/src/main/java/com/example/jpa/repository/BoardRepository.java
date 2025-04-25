package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // // where b.writer = 'user4' <= db문구
    // List<Board> findByWriter(String writer);

    // List<Board> findByTitle(String title);

    // // b.writer like '%user4'
    // List<Board> findByWriterStartingWith(String writer);

    // // b.writer like 'user4%'
    // List<Board> findByWriterEndingWith(String writer);

    // // b.writer like '%user4%'
    // List<Board> findByWriterContaining(String writer);

    // // b.writer like '%user%' or b.content like '%내용%'
    // List<Board> findByWriterContainingOrContentContaining(String writer, String
    // content);

    // // b.writer like '%user%' and b.content like '%내용%'
    // List<Board> findByWriterContainingAndContentContaining(String writer, String
    // content);

    // // bno > 5 게시물 조회
    // List<Board> findByBnoGreaterThan(Long bno);

    // // bno > 0 order by bno desc
    // List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // // bno >= 5 and bno <= 18
    // // where bno between 5 and 10

    // List<Board> findByBnoBetween(Long start, Long end);

    // -----------------------------------------------------------------------------
    // @Query
    // -----------------------------------------------------------------------------
    // 아래 Board 는 entity 이름 , b 는 별칭 , from 은 entity 기준
    @Query("select b from Board b where b.writer = ?1")
    List<Board> findByWriter(String writer);

    @Query("select b from Board b where b.writer like ?1%")
    List<Board> findByWriterStartingWith(String writer);

    @Query("select b from Board b where b.writer like %?1%")
    List<Board> findByWriterContaining(String writer);

    // @Query("select b from Board b where b.bno > ?1")

    // sql 구문 형식 사용
    // 아래 둘중하나 사용하기 근데 잘 안씀
    // @Query(value = "select * from Board b where b.bno > ?1", nativeQuery = true)
    @NativeQuery("select * from Board b where b.bno > ?1")
    List<Board> findByBnoGreaterThan(Long bno);
}
