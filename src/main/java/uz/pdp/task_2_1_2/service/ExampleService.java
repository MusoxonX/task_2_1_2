package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_1_2.entity.Example;
import uz.pdp.task_2_1_2.entity.Work;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.payload.ExampleDto;
import uz.pdp.task_2_1_2.repository.ExampleRepository;
import uz.pdp.task_2_1_2.repository.WorkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    WorkRepository workRepository;
//    add example
    public ApiResponse addE(ExampleDto exampleDto){
        Optional<Work> optionalWork = workRepository.findById(exampleDto.getWorkId());
        if (!optionalWork.isPresent()){
            return new ApiResponse("work not found",false);
        }
        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setWork(optionalWork.get());
        exampleRepository.save(example);
        return new ApiResponse("example saved",true);
    }

//    get example
    public List<Example> getE(){
        List<Example> all = exampleRepository.findAll();
        return all;
    }

//    get example by id
    public Example getById(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isPresent()){
            return optionalExample.get();
        }
        return null;
    }


//    edit Example
    public ApiResponse editE(Integer id,ExampleDto exampleDto){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return new ApiResponse("example not found",false);
        }
        Optional<Work> optionalWork = workRepository.findById(exampleDto.getWorkId());
        if (!optionalWork.isPresent()){
            return new ApiResponse("work not found",false);
        }

        Example example = optionalExample.get();
        example.setText(exampleDto.getText());
        example.setWork(optionalWork.get());
        exampleRepository.save(example);
        return new ApiResponse("example edited",true);
    }

//    delete example
    public ApiResponse deletE(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isPresent()){
            exampleRepository.deleteById(id);
            return new ApiResponse("example deleted",true);
        }
        return new ApiResponse("example not found",false);
    }
}
