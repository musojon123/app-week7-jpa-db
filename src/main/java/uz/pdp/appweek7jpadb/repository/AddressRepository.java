package uz.pdp.appweek7jpadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appweek7jpadb.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
