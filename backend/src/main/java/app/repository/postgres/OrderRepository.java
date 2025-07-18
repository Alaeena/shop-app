package app.repository.postgres;

import app.model.Order;
import app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserAndId(UserEntity user, Long id);
}
