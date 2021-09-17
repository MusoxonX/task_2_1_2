package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;
import uz.pdp.task_2_1_2.entity.Answer;
import uz.pdp.task_2_1_2.entity.User;
import uz.pdp.task_2_1_2.entity.Work;
import uz.pdp.task_2_1_2.payload.AnswerDto;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.repository.AnswerRepository;
import uz.pdp.task_2_1_2.repository.UserRepository;
import uz.pdp.task_2_1_2.repository.WorkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    WorkRepository workRepository;

    @Autowired
    UserRepository userRepository;

//    add answer
    public ApiResponse addAnswer(AnswerDto answerDto){
        Optional<Work> optionalWork = workRepository.findById(answerDto.getWorkId());
        if (!optionalWork.isPresent()){
            return new ApiResponse("work not found",false);
        }
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new ApiResponse("user not found",false);
        }
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setWork(optionalWork.get());
        answer.setUser(optionalUser.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("answer saved",true);
    }

//    get answer
    public List<Answer> getAnswer(){
        List<Answer> all = answerRepository.findAll();
        return all;
    }

//    get answers by id
    public Answer getAnswerById(Integer id){
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.get();
    }

//    edit answers
    public ApiResponse editAnswer(Integer id,AnswerDto answerDto){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ApiResponse("answer not found", false);
        }
        Optional<Work> optionalWork = workRepository.findById(answerDto.getWorkId());
        if (!optionalWork.isPresent()){
            return new ApiResponse("work not found",false);
        }
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new ApiResponse("user not found",false);
        }
        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.setUser(optionalUser.get());
        answer.setWork(optionalWork.get());
        answer.setCorrect(answerDto.isCorrect());
        answerRepository.save(answer);
        return new ApiResponse("answer saved",true);
    }

//    delete answer
    public ApiResponse deleteAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()){
            answerRepository.deleteById(id);
            return new ApiResponse("answer deleted",true);
        }
        return new ApiResponse("answer not found",false);
    }
}
