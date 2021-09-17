package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Example;
import uz.pdp.task_2_1_2.entity.Language;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
}
