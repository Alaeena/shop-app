package app.repository.postgres;

import app.model.Cart;
import app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.user.email = ?1")
    Optional<Cart> findByUserEmail(String email);

    Optional<Cart> findByUser(UserEntity user);
}
