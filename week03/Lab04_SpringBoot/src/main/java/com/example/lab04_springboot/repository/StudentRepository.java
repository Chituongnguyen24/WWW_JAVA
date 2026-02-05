package com.example.lab04_springboot.repository;

import com.example.lab04_springboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email
    );
}