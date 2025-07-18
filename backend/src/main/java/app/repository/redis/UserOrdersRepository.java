package app.repository.redis;

import app.model.serializable.UserOrders;
import org.springframework.data.repository.CrudRepository;

public interface UserOrdersRepository extends CrudRepository<UserOrders, String> {
}
