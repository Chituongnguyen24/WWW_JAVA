package com.example.lab04_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lab04_springboot.model.Student;
import com.example.lab04_springboot.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // Redirect root to students list
    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // READ - danh sách
    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", service.getAll());
        return "students";
    }

    // CREATE - mở form thêm mới
    @GetMapping("/students/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // CREATE + UPDATE - lưu (dùng chung)
    @PostMapping("/students/save")
    public String save(@Valid @ModelAttribute("student") Student student,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "student-form";
        }
        service.save(student);
        return "redirect:/students";
    }

    // READ - xem chi tiết
    @GetMapping("/students/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-detail";
    }

    // UPDATE - mở form chỉnh sửa
    @GetMapping("/students/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-form";
    }

    // DELETE
    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/students";
    }
    @GetMapping("/students/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("students", service.search(keyword));
        model.addAttribute("keyword", keyword);
        return "students";
    }

}
