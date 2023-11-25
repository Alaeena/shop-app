package app.Model.Dto;

import app.Model.ProductElasticsearch;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductListDto {
    private Integer page;
    private Integer maxPage;
    private Long results;
    private List<ProductElasticsearch> products;
}
