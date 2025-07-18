package app.model.criteria;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPage {
    private int pageNumber = 0;
    private int pageSize = 5;
    private Sort.Direction sortDirection = ASC;

    public OrderPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
