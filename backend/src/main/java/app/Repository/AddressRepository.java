package app.Repository;

import app.Model.Address;
import app.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserId(Long id);

    Optional<Address> findByUserAndId(UserEntity user, Long id);
}
