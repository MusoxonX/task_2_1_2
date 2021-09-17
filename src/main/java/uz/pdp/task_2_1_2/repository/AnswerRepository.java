package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Answer;
import uz.pdp.task_2_1_2.entity.Language;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
