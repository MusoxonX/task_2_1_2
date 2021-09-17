package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_1_2.entity.Category;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.repository.CategoryRepository;
import uz.pdp.task_2_1_2.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
// add category
    public ApiResponse addCategory(Category categoryDto){
        boolean b = categoryRepository.existsByName(categoryDto.getName());
        if (b){
            return new ApiResponse("category already exists",false);
        }
        categoryRepository.save(categoryDto);
        return new ApiResponse("category added",true);
    }
//    get all category
    public List<Category> getCategory(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }

//    get language by id
    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return null;
        }
        return optionalCategory.get();
    }
//    edit language
    public ApiResponse editCategory(Integer id,Category categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("category not found",false);
        }
        boolean b = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (b) {
            return new ApiResponse("Category already exists",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Category edited",true);
    }


    public ApiResponse deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("category deleted",true);
        }
        return new ApiResponse("category not found",false);
    }
}
