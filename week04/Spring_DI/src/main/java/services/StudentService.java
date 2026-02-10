package services;

import java.util.List;

import model.Student;

public interface StudentService {

    Student save(Student student);

    List<Student> findAll();

    Student findById(Integer id);

    void deleteById(Integer id);

    List<Student> searchByName(String keyword);
}
