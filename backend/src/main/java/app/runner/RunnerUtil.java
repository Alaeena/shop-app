package app.runner;

import app.model.*;
import app.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RunnerUtil {
    public static final Map<String, AttributeValue> attributes = new HashMap<>();

    public static CategoryAttribute createAttribute(String name, List<String> values) {
        CategoryAttribute categoryAttribute = new CategoryAttribute(name);

        for (String value : values) {
            AttributeValue attributeValue = getAttributeValue(name, value);
            categoryAttribute.addValue(attributeValue);
        }
        return categoryAttribute;
    }

    private static AttributeValue getAttributeValue(String name, String value) {
        AttributeValue attributeValue;

        if (attributes.get(value) != null) {
            attributeValue = attributes.get(value);
        } else {
            attributeValue = new AttributeValue(name, value);
            attributes.put(value, attributeValue);
        }
        return attributeValue;
    }

    public static Product addAttribute(
            Product product,
            Map<String, String> values
    ) {
        for (String key : values.keySet()) {
            String value = values.get(key);

            AttributeValue attributeValue = getAttributeValue(key, value);
            product.addValue(attributeValue);
            attributeValue.addProduct(product);
        }
        return product;
    }

    public static UserEntity createUser(String email, String password, Role role) {
        return createUser(email, password, role, false, false);
    }

    public static UserEntity createUser(String email, String password, Role role, Boolean activated) {
        return createUser(email, password, role, activated, false);
    }

    public static UserEntity createUser(String email, String password, Role role, Boolean activated, Boolean tfaEnabled) {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .role(role)
                .addresses(new ArrayList<>())
                .activated(activated)
                .tfaEnabled(tfaEnabled)
                .cart(new Cart())
                .addresses(new ArrayList<>())
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .disabled(false)
                .build();
    }

}
