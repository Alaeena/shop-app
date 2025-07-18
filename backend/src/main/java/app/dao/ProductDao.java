package app.dao;

import app.model.criteria.ProductCriteria;
import app.model.criteria.ProductPage;
import app.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder builder;

    @Autowired
    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findProductWithFilter(
            ProductPage productPage,
            ProductCriteria criteria
    ) {
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate predicate = getPredicate(criteria, root);
        query.where(predicate);
        setOrder(productPage, query, root);

        TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        typedQuery.setMaxResults(productPage.getPageSize());

        Pageable pageable = getPageable(productPage);
        long productCount = getProductCount(criteria);
        return new PageImpl<>(
                typedQuery.getResultList(),
                pageable,
                productCount
        );
    }


    private Predicate getPredicate(ProductCriteria criteria, Root<Product> root) {
        List<Predicate> list = new ArrayList<>();

        if (criteria.getCategories().size() == 1) {
            list.add(builder.like(
                    root.get("category").as(String.class),
                    criteria.getCategories().get(0).toString()
            ));
        } else {
            List<Predicate> categoryPredicates = new ArrayList<>();

            for (Long categoryId : criteria.getCategories()) {
                categoryPredicates.add(builder.like(
                        root.get("category").as(String.class),
                        categoryId.toString()
                ));
            }
            list.add(builder.or(categoryPredicates.toArray(new Predicate[0])));
        }

        if (Objects.nonNull(criteria.getName())) {
            list.add(builder.like(
                    root.get("name").as(String.class),
                    "%" + criteria.getName() + "%"
            ));
        }

        return builder.and(list.toArray(new Predicate[0]));
    }

    private void setOrder(
            ProductPage page,
            CriteriaQuery<Product> query,
            Root<Product> root
    ) {
        Sort.Direction direction = page.getSortDirection();
        String sortBy = page.getSortBy();

        if (direction.isAscending()) {
            query.orderBy(builder.asc(root.get(sortBy)));
        } else {
            query.orderBy(builder.desc(root.get(sortBy)));
        }
    }

    private Pageable getPageable(ProductPage productPage) {
        Sort sort = Sort.by(
                productPage.getSortDirection(),
                productPage.getSortBy()
        );
        return PageRequest.of(
                productPage.getPageNumber(),
                productPage.getPageSize(),
                sort
        );
    }

    private long getProductCount(ProductCriteria criteria) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        Predicate predicate = getPredicate(criteria, countRoot);

        countQuery.select(builder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
