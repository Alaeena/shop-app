package app.Dao;


import app.Model.Criteria.OrderPage;
import app.Model.Enum.OrderState;
import app.Model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static app.Model.Enum.OrderState.ALL;

@Repository
public class OrderDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder builder;

    @Autowired
    public OrderDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    public List<Order> findProductWithFilter(
            OrderPage page,
            OrderState orderState,
            Long userId
    ) {
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);

        Predicate predicate = getPredicate(orderState, userId, root);
        query.where(predicate);

        TypedQuery<Order> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(
            OrderState orderState,
            Long userId,
            Root<Order> root
    ) {
        List<Predicate> list = new ArrayList<>();
        if (orderState != ALL) {
            list.add(builder.like(
                    root.get("state").as(String.class),
                    "%" + orderState + "%"
            ));
        }
        list.add(builder.like(
                root.get("user").as(String.class),
                "%" + userId + "%"
        ));

        return builder.and(list.toArray(new Predicate[0]));
    }
}
