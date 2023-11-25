package app.Dao;

import app.Model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends PagingAndSortingRepository<Comment, Long> {
    Page<Comment> findAllByProduct_Id(Long id, Pageable pageable);

    Page<Comment> findAllByProduct_IdAndRating(Long id, Integer rating, Pageable pageable);
}
