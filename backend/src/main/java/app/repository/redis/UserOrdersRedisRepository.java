package app.repository.redis;

import app.model.redis.UserOrders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRedisRepository extends CrudRepository<UserOrders, String> {
}
