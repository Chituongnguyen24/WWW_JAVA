package com.example.lab04_springboot.service;

import com.example.lab04_springboot.model.Student;
import com.example.lab04_springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // READ - all
    public List<Student> getAll() {
        return repository.findAll();
    }

    // CREATE + UPDATE
    public Student save(Student student) {
        return repository.save(student);
    }

    // READ - by id
    public Student getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // DELETE
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // CHECK EXIST (optional – hay dùng cho update)
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
    public List<Student> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        return repository
                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

}
