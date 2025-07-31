package app.service;

import app.dao.ProductDao;
import app.httpDto.CategoryDetail;
import app.model.criteria.ProductCriteria;
import app.model.criteria.ProductPage;
import app.model.dto.CategoryDto;
import app.model.dto.ProductListDto;
import app.model.dto.SubCategoryDto;
import app.model.mapper.CategoryMapper;
import app.model.mapper.ProductMapper;
import app.model.mapper.SubCategoryMapper;
import app.model.postgres.Category;
import app.model.postgres.Product;
import app.model.postgres.ProductElasticsearch;
import app.repository.postgres.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductDao productDao;

    public List<SubCategoryDto> findAll() {
        return categoryRepository.findMainCategory().stream()
                .map(SubCategoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDetail> getAll() {
        return categoryRepository.findMainCategory().stream()
                .map(CategoryMapper::mapToDetail)
                .collect(Collectors.toList());
    }


    public CategoryDto getCategoryList(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Invalid category's name request"));
    }


    public ProductListDto getCategoryProduct(Long id, ProductPage productPage) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid category's name request"));
        ProductCriteria criteria = category.getSubCategories().size() > 0
                ? new ProductCriteria(category.getSubCategories())
                : new ProductCriteria(category);
        Page<Product> page = productDao.findProductWithFilter(productPage, criteria);

        List<ProductElasticsearch> products = page.stream()
                .map(ProductMapper::mapToElastic)
                .collect(Collectors.toList());
        return ProductListDto.builder()
                .maxPage(page.getTotalPages())
                .page(page.getNumber())
                .results(page.getTotalElements())
                .products(products)
                .build();
    }


    public SubCategoryDto addSubcategory(Long id, SubCategoryDto subCategoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No category found with this id"));
        boolean check = category.getSubCategories().stream()
                .anyMatch(item -> item.getName().equals(subCategoryDto.getName()));
        if (check) {
            throw new RuntimeException("Sub category already exist");
        }
        Category subCategory = Category.builder()
                .name(subCategoryDto.getName())
                .parent(category)
                .build();

        return SubCategoryMapper.mapToDto(categoryRepository.save(subCategory));
    }

    public SubCategoryDto addCategory(SubCategoryDto subCategoryDto) {
        boolean exist = categoryRepository.existsByName(subCategoryDto.getName());
        if (exist) {
            throw new RuntimeException("Category already exist");
        }
        Category category = Category.builder()
                .name(subCategoryDto.getName())
                .build();

        return SubCategoryMapper.mapToDto(categoryRepository.save(category));
    }

    public SubCategoryDto updateCategory(SubCategoryDto subCategoryDto) {
        Category category = categoryRepository
                .findById(subCategoryDto.getId())
                .orElseThrow(() -> new RuntimeException("No category found with this id"));

        category.setName(subCategoryDto.getName());
        return SubCategoryMapper.mapToDto(categoryRepository.save(category));
    }

    public SubCategoryDto deleteCategory(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No category found with this id"));
        category.getSubCategories()
                .forEach(sub -> categoryRepository.deleteById(sub.getId()));
        categoryRepository.deleteById(id);
        return SubCategoryMapper.mapToDto(category);
    }
}
