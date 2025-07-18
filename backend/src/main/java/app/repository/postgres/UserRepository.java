package app.repository.postgres;

import app.model.UserEntity;
import app.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    List<UserEntity> findAllByRoleAndDisabled(Role role, Boolean disabled);
}
