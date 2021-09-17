package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.entity.Work;

public interface WorkRepository extends JpaRepository<Work,Integer> {
}
