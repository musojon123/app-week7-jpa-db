package uz.pdp.appweek7jpadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek7jpadb.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
