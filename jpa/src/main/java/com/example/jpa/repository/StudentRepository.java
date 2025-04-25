package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.entity.Student;

// <Entity명,@Id타입>
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
