package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.Work;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.payload.WorkDto;
import uz.pdp.task_2_1_2.service.WorkService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/work")
public class WorkController {
    @Autowired
    WorkService workService;

    @PostMapping()
    public HttpEntity<ApiResponse> addWork(@Valid @RequestBody WorkDto workDto){
        ApiResponse apiResponse = workService.addWork(workDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//  get work all
    @GetMapping()
    public HttpEntity<?> getWork(){
        List<Work> work = workService.getWork();
        return ResponseEntity.ok(work);
    }
//    get work by id
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Work work = workService.getWorkById(id);
        return new HttpEntity<>(work);
    }
//    edit work
    @PutMapping("/{id}")
    public HttpEntity<?> editWork(@PathVariable Integer id,@RequestBody WorkDto workDto){
        ApiResponse apiResponse = workService.editWork(id, workDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//    delet work
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWork(@PathVariable Integer id){
        ApiResponse apiResponse = workService.deleteWork(id);
        return new  HttpEntity<>(apiResponse);
    }



//    exceptions
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
