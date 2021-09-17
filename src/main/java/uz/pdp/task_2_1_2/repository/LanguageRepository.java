package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Language;

import javax.validation.constraints.NotNull;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(@NotNull(message = "name must be written") String name);
    boolean existsByNameAndIdNot(@NotNull(message = "name must be written") String name, Integer id);
}
