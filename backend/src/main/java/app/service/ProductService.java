package app.service;

import app.model.Product;
import app.model.ProductElasticsearch;
import app.model.criteria.ProductPage;
import app.model.dto.ProductDto;
import app.model.dto.ProductListDto;
import app.model.mapper.ProductMapper;
import app.repository.postgres.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    private ProductListDto getProductListFromPage(Page<Product> page) {
        List<ProductElasticsearch> list = page.getContent().stream()
                .map(ProductMapper::mapToElastic).toList();
        return ProductListDto.builder()
                .maxPage(page.getTotalPages())
                .page(page.getNumber())
                .results(page.getTotalElements())
                .products(list)
                .build();
    }

    public ProductDto getProductDetail(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Invalid product id !!"));
    }

    public ProductListDto getAllProduct(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 30, Sort.by("id").descending());
        Page<Product> page = productRepository.findAll(pageable);
        return getProductListFromPage(page);
    }

    public ProductListDto getQueryList(Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(sortBy).descending());
        Page<Product> page = productRepository.findAll(pageable);
        return getProductListFromPage(page);
    }

    public ProductListDto searchProduct(String search, ProductPage productPage) {
        return new ProductListDto();
    }
}
