package app.service;

import app.dao.CommentDao;
import app.model.dto.CommentDto;
import app.model.dto.CommentListDto;
import app.model.dto.ProductCommentDto;
import app.model.dto.SimpleProductDto;
import app.model.mapper.CommentMapper;
import app.model.mapper.SimpleProductMapper;
import app.model.postgres.Comment;
import app.model.postgres.OrderItem;
import app.model.postgres.Product;
import app.model.postgres.UserEntity;
import app.repository.postgres.CommentRepository;
import app.repository.postgres.OrderItemRepository;
import app.repository.postgres.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentDao commentDao;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public ProductCommentDto getCommentFromOrderId(Long orderId, UserEntity user) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder_Id(orderId);
        List<Product> products = orderItems.stream()
                .map(OrderItem::getProduct)
                .toList();
        List<CommentDto> comments = commentRepository
                .findAllByUserAndProductIn(user, products)
                .stream().map(CommentMapper::mapToDto).toList();
        List<SimpleProductDto> productDtos = products.stream()
                .map(SimpleProductMapper::mapToDTo)
                .toList();

        return ProductCommentDto.builder()
                .comments(comments)
                .products(productDtos)
                .build();
    }

    public void addComment(CommentDto commentDto, UserEntity user) {
        Optional<Product> optionalProduct = productRepository.findById(commentDto.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Invalid product id");
        }

        Comment comment = new Comment(commentDto.getMessage(), commentDto.getRating());
        comment.setUser(user);
        comment.setProduct(optionalProduct.get());
        commentRepository.save(comment);
    }

    public void updateComment(CommentDto commentDto, UserEntity user) {
        Optional<Comment> optional = commentRepository.findByUserAndAndId(user, commentDto.getId());
        if (optional.isPresent()) {
            Comment comment = optional.get();
            comment.setRating(commentDto.getRating());
            comment.setMessage(commentDto.getMessage());
            commentRepository.save(comment);
        }
    }

    public CommentListDto getCommentFromProductId(
            Integer pageNumber,
            Long productId,
            Integer rating
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Comment> page = rating == null
                ? commentDao.findAllByProduct_Id(productId, pageable)
                : commentDao.findAllByProduct_IdAndRating(productId, rating, pageable);


        List<CommentDto> comments = page.getContent().stream()
                .map(CommentMapper::mapToDto).toList();
        return CommentListDto.builder()
                .comments(comments)
                .maxPage(page.getTotalPages())
                .page(page.getNumber())
                .build();
    }
}
