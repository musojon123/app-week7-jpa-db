package uz.pdp.appweek7jpadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appweek7jpadb.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
