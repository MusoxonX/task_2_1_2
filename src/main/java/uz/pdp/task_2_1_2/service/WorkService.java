package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_1_2.entity.Category;
import uz.pdp.task_2_1_2.entity.Work;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.payload.WorkDto;
import uz.pdp.task_2_1_2.repository.CategoryRepository;
import uz.pdp.task_2_1_2.repository.WorkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    @Autowired
    WorkRepository workRepository;

    @Autowired
    CategoryRepository categoryRepository;

//    add work
    public ApiResponse addWork(WorkDto workDto){

        Optional<Category> optionalCategory = categoryRepository.findById(workDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("category not found",false);
        }

        Work work = new Work();
        work.setName(workDto.getName());
        work.setText(workDto.getText());
        work.setQuestion(workDto.getQuestion());
        work.setSolution(workDto.getSolution());
        work.setStar(true);
        work.setCategory(optionalCategory.get());
        workRepository.save(work);
        return new ApiResponse("work saved",true);
    }
//    get all works
    public List<Work> getWork(){
        List<Work> all = workRepository.findAll();
        return all;
    }
//    get work by id
    public Work getWorkById(Integer id){
        Optional<Work> optionalWork = workRepository.findById(id);
        if (!optionalWork.isPresent()){
            return null;
        }
        return optionalWork.get();
    }
//    edit work
    public ApiResponse editWork(Integer id,WorkDto workDto){

        Optional<Category> optionalCategory = categoryRepository.findById(workDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ApiResponse("category not found",false);
        }

        Optional<Work> optionalWork = workRepository.findById(id);
        if (!optionalWork.isPresent()){
            return new ApiResponse("work not found",false);
        }
        Work work = optionalWork.get();
        work.setName(workDto.getName());
        work.setText(workDto.getText());
        work.setQuestion(workDto.getQuestion());
        work.setSolution(workDto.getSolution());
        work.setStar(true);
        work.setCategory(optionalCategory.get());
        workRepository.save(work);
        return new ApiResponse("work edit successfully",true);
    }
//    delete work

    public ApiResponse deleteWork(Integer id){
        Optional<Work> optionalWork = workRepository.findById(id);
        if (optionalWork.isPresent()){
            workRepository.deleteById(id);
            return new ApiResponse("work deleted",true);
        }
        return new ApiResponse("work not found",false);
    }
}
