package fit.iuh.library.service;

import fit.iuh.library.entity.Book;
import org.springframework.stereotype.Service;
import fit.iuh.library.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo){
        this.repo=repo;
    }

    public List<Book> getAll(){
        return repo.findAll();
    }
    public Book getById(Long id){
        return repo.findById(id).orElse(null);
    }
    public void save(Book book){
        repo.save(book);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
    public List<Book> search(String keyword){
        return repo.findBooksByTitleOrAuthor(keyword,keyword);
    }
    public List<Book> filter(String category){
        return repo.findBooksByCategory(category);
    }
}

