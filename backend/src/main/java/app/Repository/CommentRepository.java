package app.Repository;

import app.Model.Comment;
import app.Model.Product;
import app.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByUserAndProduct(UserEntity user, Product product);

    List<Comment> findAllByUserAndProductIn(UserEntity user, List<Product> products);

    Optional<Comment> findByUserAndAndId(UserEntity user, Long id);
}
