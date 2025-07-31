package app.repository.postgres;

import app.model.postgres.Order;
import app.model.postgres.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserAndId(UserEntity user, Long id);
}
