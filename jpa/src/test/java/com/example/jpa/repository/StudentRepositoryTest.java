package com.example.jpa.repository;

import java.rmi.server.LogStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;
import com.example.jpa.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest // test 용 클래스라는걸 알려주는 어노테이션
public class StudentRepositoryTest {

    @Autowired // = new StudentRepository()
    private StudentRepository studentRepository;

    // CRUD test
    // Repositiory, Entity 확인
    // C(insert) : save(Entity)
    // U(update) : save(Entity)
    // 구별은 어떻게 하는가? 둘다 동일한 save() 호출인데
    // 원본과 변경된 부분이 있다면 update로 실행해줌

    @Test // 테스트 메소드임 ( 테스트 메소드는 무조건 void)
    public void insertTest() {

        LongStream.range(1, 11).forEach(i -> {
            // Entity 생성
            Student student = Student.builder()
                    .name("홍길동" + i)
                    .grade(Grade.JUNIOR)
                    .gender("M")
                    .build();
            // insert
            studentRepository.save(student);

        });

    }

    @Test
    public void updateTest() {
        // findById(1L) : select * from 테이블명 where id=1;
        Student student = studentRepository.findById(1L).get();
        student.setGrade(Grade.SENIOR);

        // update
        studentRepository.save(student);

    }

    @Test
    public void selectOneTest() {

        // Optional<Student> student = studentRepository.findById(1L);

        // if (student.isPresent()) {
        // System.out.println(student.get());
        // }

        // null 이 아닌걸 알때
        // 없는걸 찾을때 오류NoSuchElementException: No value present
        // Student student = studentRepository.findById(3L).get();
        // System.out.println(student);

        // EntityNotFoundException : Optional을 사용하지 않고, null일때 오류를 발생시킴
        Student student = studentRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
        System.out.println(student);

    }

    @Test
    public void selectTest() {
        // List<Student> list = studentRepository.findAll();

        // for (Student student : list) {
        // System.out.println(student);
        // }

        studentRepository.findAll().forEach(student -> System.out.println(student));
    }

    @Test
    public void deleteTest() {

        // Student student = studentRepository.findById(11L).get();
        // studentRepository.delete(student);

        studentRepository.deleteById(10L);
    }

}
