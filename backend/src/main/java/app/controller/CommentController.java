package app.controller;

import app.model.dto.CommentDto;
import app.model.dto.ProductCommentDto;
import app.model.UserEntity;
import app.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public Map<String, ProductCommentDto> getCommentFromOrderId(
            @RequestParam("orderId") Long orderId,
            @AuthenticationPrincipal UserEntity user
    ) {

        return Map.of(
                "data",
                commentService.getCommentFromOrderId(orderId, user)
        );
    }


    @PostMapping
    public void addComment(
            @Valid @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserEntity user
    ) {
        commentService.addComment(commentDto, user);
    }

    @PutMapping
    public void updateComment(
            @Valid @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserEntity user
    ) {
        commentService.updateComment(commentDto, user);
    }
}
