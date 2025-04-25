package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.ToDo;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            // ToDo todo = ToDo.builder()
            // .content("안녕" + i)
            // .completed(false)
            // .importanted(false)
            // .build();
            ToDo todo = new ToDo();
            todo.setContent("강아지 산책" + i);
            todoRepository.save(todo);
        });
    }

    @Test
    public void testRead() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testRead2() {
        // 완료 목록 추출
        todoRepository.findByCompleted(true)
                .forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testRead3() {
        // 안중요 목록 추출
        todoRepository.findByImportanted(false)
                .forEach(todo -> System.out.println(todo));
    }

    // todo 삭제
    @Test
    public void testDelete() {
        todoRepository.deleteById(10L);
    }

    // todo 수정-완료

    @Test
    public void testUpdate() {

        ToDo todo = todoRepository.findById(5L).get();
        todo.setCompleted(true);
        // todo.setContent("고양이 산책");
        todoRepository.save(todo);
    }
}
