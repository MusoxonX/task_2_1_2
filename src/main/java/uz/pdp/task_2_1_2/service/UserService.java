package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_1_2.entity.User;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
//  add user
    public ApiResponse addUser(User user){
        boolean b = userRepository.existsByEmail(user.getEmail());
        if (b) {
            return new ApiResponse("email already exists",false);
        }
        userRepository.save(user);
        return new ApiResponse("user successfully added",true);
    }
//    get user
    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }
//  edit user
    public ApiResponse editUser(Integer id,User userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new ApiResponse("user not found",false);
        }

        boolean b = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (b) {
            return new ApiResponse("email already exists",false);
        }
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("user edited",true);
    }

    public ApiResponse deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("user deleted",true);
        }
        return new ApiResponse("user not found",false);
    }
}
