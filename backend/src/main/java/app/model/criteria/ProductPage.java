package app.model.criteria;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Getter
@Setter
public class ProductPage {
    private int pageNumber = 0;
    private int pageSize = 5;
    private Direction sortDirection = ASC;
    private String sortBy = "name";
}
