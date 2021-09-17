package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Category;
import uz.pdp.task_2_1_2.entity.Language;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
