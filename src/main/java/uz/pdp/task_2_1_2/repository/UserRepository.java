package uz.pdp.task_2_1_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.entity.User;

import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(@NotNull(message = "email required") String email);
    boolean existsByEmailAndIdNot(@NotNull(message = "email required") String email, Integer id);
}
