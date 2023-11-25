package app.Controller;

import app.HttpDto.CategoryDetail;
import app.Model.Criteria.ProductPage;
import app.Model.Dto.CategoryDto;
import app.Model.Dto.ProductListDto;
import app.Model.Dto.SubCategoryDto;
import app.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Map<String, List<SubCategoryDto>>> getAllCategory() {
        return ResponseEntity.accepted().body(
                Map.of("data", categoryService.findAll())
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<CategoryDetail>>> getAllCategoryDetail() {
        return ResponseEntity.accepted().body(
                Map.of("data", categoryService.getAll())
        );
    }

    @PostMapping
    public ResponseEntity<SubCategoryDto> addCategory(
            @RequestBody SubCategoryDto subCategoryDto
    ) {
        return ResponseEntity.accepted().body(
                categoryService.addCategory(subCategoryDto)
        );
    }

    @PutMapping
    public ResponseEntity<SubCategoryDto> updateCategory(
            @RequestBody SubCategoryDto subCategoryDto
    ) {
        return ResponseEntity.accepted().body(
                categoryService.updateCategory(subCategoryDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubCategoryDto> deleteCategory(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.accepted().body(
                categoryService.deleteCategory(id)
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<SubCategoryDto> addSubcategory(
            @PathVariable(name = "id") Long id,
            @RequestBody SubCategoryDto subCategoryDto
    ) {
        return ResponseEntity.accepted().body(
                categoryService.addSubcategory(id, subCategoryDto)
        );
    }

    @GetMapping("/{categoryId}/list")
    public ResponseEntity<Map<String, CategoryDto>> getCategoryList(
            @PathVariable("categoryId") Long id
    ) {
        return ResponseEntity.accepted().body(
                Map.of("data", categoryService.getCategoryList(id))
        );
    }

    @GetMapping("/{categoryId}/product")
    public ResponseEntity<Map<String, ProductListDto>> getCategoryDetail(
            @PathVariable("categoryId") Long id,
            @ModelAttribute ProductPage productPage
    ) {
        return ResponseEntity.accepted().body(Map.of(
                "data",
                categoryService.getCategoryProduct(id, productPage))
        );
    }

}
