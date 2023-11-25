package app.Repository;

import app.Model.OrderItem;
import app.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByCartIdAndProduct(Long cartId, Product product);

    List<OrderItem> findAllByOrder_Id(Long id);
}
