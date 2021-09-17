package uz.pdp.task_2_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_1_2.entity.User;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
//    user add
    @PostMapping()
    public HttpEntity<?> addUser(@Valid @RequestBody User userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(202).body(apiResponse);
    }
//    get user
    @GetMapping()
    public HttpEntity<?> getUser(){
        List<User> user = userService.getUser();
        return new HttpEntity<>(user);
    }

//  gte User by id
    @GetMapping()
    public HttpEntity<?> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        if (user.equals(null)){
            return ResponseEntity.status(404).body("user not found");
        }
        return new HttpEntity<>(user);
    }

//  edit user
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id,@RequestBody User user){
        ApiResponse apiResponse = userService.editUser(id, user);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
//      delete user
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(200).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

}
