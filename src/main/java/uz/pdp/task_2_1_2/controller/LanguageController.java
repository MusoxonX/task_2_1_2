package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;
//    add laguage
    @PostMapping()
    public HttpEntity<ApiResponse> addLanguage(@Valid @RequestBody Language languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//    get all language
    @GetMapping()
    public HttpEntity<List<Language>> getLanguage(){
        List<Language> language = languageService.getLanguage();
        return new HttpEntity<>(language);
    }

//    get language by id
    @GetMapping("/{id}")
    public HttpEntity<Language> getLanguageById(@PathVariable Integer id){
        Language language = languageService.getLanguageById(id);
        return new HttpEntity<>(language);
    }
//      edit language
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@Valid @PathVariable Integer id,@RequestBody Language language){
        ApiResponse apiResponse = languageService.editLanguage(id, language);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
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
