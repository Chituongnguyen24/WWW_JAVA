package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Student;
import services.StudentService;

@Controller
@RequestMapping("/")
public class WebController {

    private final StudentService service;

    public WebController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String index(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("students", service.searchByName(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("students", service.findAll());
        }
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Integer id, Model model) {
        Student student = service.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("students", service.findAll());
        model.addAttribute("isEdit", true);
        return "index";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/";
    }
}
