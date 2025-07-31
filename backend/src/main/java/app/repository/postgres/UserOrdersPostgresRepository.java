package app.repository.postgres;

import app.model.postgres.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersPostgresRepository extends JpaRepository<UserOrders, String> {
}
