package app.Controller;

import app.Model.Criteria.ProductPage;
import app.Model.Dto.CommentListDto;
import app.Model.Dto.ProductDto;
import app.Model.Dto.ProductListDto;
import app.Service.CommentService;
import app.Service.ProductService;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping("/{productId}")
    public Map<String, ProductDto> getProductDetail(
            @NotNull @PathVariable("productId") Long productId
    ) {
        return Map.of(
                "data",
                productService.getProductDetail(productId)
        );
    }

    @GetMapping("/{productId}/comment")
    public Map<String, CommentListDto> getProductComment(
            @PathVariable("productId") Long productId,
            @RequestParam("pageNumber") Integer pageNumber,
            @PathParam("rating") Integer rating
    ) {

        return Map.of(
                "data",
                commentService.getCommentFromProductId(pageNumber, productId, rating)
        );
    }

    @GetMapping("/search")
    public Map<String, ProductListDto> searchProduct(
            @ModelAttribute ProductPage productPage,
            @RequestParam(name = "query") String query
    ) {

        return Map.of(
                "data",
                productService.searchProduct(query, productPage)
        );
    }

    @GetMapping("/all")
    public Map<String, ProductListDto> getAllProduct(
            @RequestParam("pageNumber") Integer pageNumber
    ) {
        return Map.of(
                "data",
                productService.getAllProduct(pageNumber)
        );
    }

    @GetMapping("/query")
    public Map<String, ProductListDto> getQueryList(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortBy") String sortBy
    ) {
        return Map.of(
                "data",
                productService.getQueryList(pageSize, sortBy)
        );
    }


}
