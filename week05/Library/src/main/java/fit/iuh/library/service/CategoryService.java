package fit.iuh.library.service;

import fit.iuh.library.entity.Category;
import fit.iuh.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo){
        this.repo=repo;
    }
    public List<Category> getAll(){
        return repo.findAll();
    }
}
