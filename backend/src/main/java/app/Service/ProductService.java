package app.Service;

import app.Model.Criteria.ProductPage;
import app.Model.Dto.ProductDto;
import app.Model.Dto.ProductListDto;
import app.Model.Mapper.ProductMapper;
import app.Model.Product;
import app.Model.ProductElasticsearch;
import app.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchOperations operations;


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
        Query nativeQuery = NativeQuery.builder()
                .withQuery(query -> query
                        .multiMatch(match -> match
                                .fields(List.of("name", "shopName", "categoryName"))
                                .fuzziness("AUTO")
                                .query(search)
                        )
                )
                .withPageable(PageRequest.of(productPage.getPageNumber(), productPage.getPageSize()))
                .build();
        SearchHits<ProductElasticsearch> hits = operations.search(nativeQuery, ProductElasticsearch.class);
        List<ProductElasticsearch> products = hits.stream()
                .map(hit -> hit.getContent())
                .toList();
        Page<SearchHit<ProductElasticsearch>> page = SearchHitSupport.searchPageFor(hits, nativeQuery.getPageable());
        return ProductListDto.builder()
                .maxPage(page.getTotalPages())
                .page(page.getNumber())
                .results(page.getTotalElements())
                .products(products)
                .build();
    }
}
