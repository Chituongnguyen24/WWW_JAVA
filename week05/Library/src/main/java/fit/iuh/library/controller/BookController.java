package fit.iuh.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fit.iuh.library.entity.Book;
import fit.iuh.library.service.BookService;
import fit.iuh.library.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping({"","/"})
    public String list(Model model){
        model.addAttribute("books",bookService.getAll());
        model.addAttribute("categories",categoryService.getAll());
        return "books";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAll());
        return "form";
    }
    @PostMapping("/save")
    public String save(Book book){
        bookService.save(book);
        return "redirect:/books/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("categories", categoryService.getAll());
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("books", bookService.search(keyword));
        model.addAttribute("categories", categoryService.getAll());
        return "books";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String name, Model model) {
        model.addAttribute("books", bookService.filter(name));
        model.addAttribute("categories", categoryService.getAll());
        return "books";
    }
}
