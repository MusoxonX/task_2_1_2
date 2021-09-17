package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.Category;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.service.CategoryService;
import uz.pdp.task_2_1_2.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
//    add laguage
    @PostMapping()
    public HttpEntity<ApiResponse> addCategory(@Valid @RequestBody Category categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//    get all category
    @GetMapping()
    public HttpEntity<List<Category>> getCategory(){
        List<Category> category = categoryService.getCategory();
        return new HttpEntity<>(category);
    }

//    get category by id
    @GetMapping("/{id}")
    public HttpEntity<Category> getCategoryById(@PathVariable Integer id){
        Category category = categoryService.getCategoryById(id);
        return new HttpEntity<>(category);
    }
//      edit category
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @PathVariable Integer id,@RequestBody Category categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return new HttpEntity<>(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
