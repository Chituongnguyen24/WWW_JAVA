package controller;


import model.Student;
import org.springframework.web.bind.annotation.*;
import services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
