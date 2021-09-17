package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.Example;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.payload.ExampleDto;
import uz.pdp.task_2_1_2.repository.ExampleRepository;
import uz.pdp.task_2_1_2.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;
//    add exmple
    @PostMapping()
    public HttpEntity<?> addE(@Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addE(exampleDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

//    get example
    @GetMapping()
    public HttpEntity<?> getE(){
        List<Example> e = exampleService.getE();
        return new HttpEntity<>(e);
    }


//    get Example by id
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Example example = exampleService.getById(id);
        return ResponseEntity.ok(example);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editE(@Valid @PathVariable Integer id, @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.editE(id, exampleDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

//    delete example
    public HttpEntity<?> deleteE(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deletE(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(200).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
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
