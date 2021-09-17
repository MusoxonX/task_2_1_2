package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.Answer;
import uz.pdp.task_2_1_2.payload.AnswerDto;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.service.AnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
//    answer add
    public HttpEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(200).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//    get all answers
    @GetMapping()
    public HttpEntity<?> getAnswer(){
        List<Answer> answer = answerService.getAnswer();
        return ResponseEntity.ok(answer);
    }

//    get answers by id
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Answer answer = answerService.getAnswerById(id);
        return new HttpEntity<>(answer);
    }

//  edit answers
    @PutMapping("/{id}")
    public HttpEntity<?> editAnswer(@Valid @PathVariable Integer id, @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.editAnswer(id, answerDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
//    delete answer
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
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
