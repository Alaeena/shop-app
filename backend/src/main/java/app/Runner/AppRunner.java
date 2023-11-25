package app.Runner;

import app.Model.*;
import app.Model.Enum.AddressType;
import app.Model.Mapper.ProductMapper;
import app.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static app.Model.Enum.CheckoutType.ONLINE;
import static app.Model.Enum.CheckoutType.UPFRONT;
import static app.Model.Enum.OrderState.*;
import static app.Model.Enum.Role.ADMIN;
import static app.Model.Enum.Role.USER;
import static app.Runner.RunnerUtil.createUser;

@Profile({"dev","docker"})
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CategoryRunner categoryRunner;

    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;

    private final ProductElasticsearchRepository elasticsearchRepository;


    private void generateComment(List<Map<String, String>> list, List<Product> productList) {
        Random random = new Random();
        List<Comment> comments = new ArrayList<>();
        List<String> data = List.of(
                "This is some awesome thinking!",
                "What terrific math skills you’re showing!",
                "You are an amazing writer!",
                "Wow! You have improved so much!",
                "Nice idea!",
                "You are showing excellent understanding!",
                "This is clear, concise, and complete!",
                "What a powerful argument!",
                "I knew you could do it!",
                "Wonderful ideas!",
                "It was a pleasure to grade this!",
                "Keep up the incredible work!",
                "My goodness, how impressive!",
                "You’re showing inventive ideas!",
                "You’ve shown so much growth!",
                "Interesting thoughts!",
                "I love your neat work!",
                "Doesn’t it feel good to do such great work?",
                "First-rate work!",
                "This is fascinating information!",
                "You inspire me!",
                "This is right on target!",
                "What an astounding observation!",
                "This is very well thought out!",
                "I can tell you’ve been practicing!",
                "You’ve come a long way!",
                "I can tell you’ve been paying attention!",
                "Reading this made my day!",
                "This is very perceptive!",
                "What an accomplishment!"
        );
        for (Map<String, String> value : list) {
            UserEntity userEntity = createUser(value.get("email"), "123", USER);
            userEntity.setFirstName(value.get("firstName"));
            userEntity.setLastName(value.get("lastName"));
            userRepository.save(userEntity);

            for (int i = 0; i <= 100; i++) {
                Product product = productList.get(random.nextInt(productList.size()));
                String text = data.get(random.nextInt(data.size()));

                Comment comment = new Comment(text);
                comment.setUser(userEntity);
                comment.setProduct(product);
                comments.add(comment);
            }

        }
        commentRepository.saveAll(comments);
    }

    private void createElasticProduct(List<Product> list) {
        List<ProductElasticsearch> elasticsearchList = list.stream()
                .map(ProductMapper::mapToElastic)
                .toList();
        elasticsearchRepository.saveAll(elasticsearchList);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Map<String, String>> list = List.of(
                Map.of("email", "DaoNgocChinh@example.com", "firstName", "Ngoc", "lastName", "Chinh"),
                Map.of("email", "NguyenVanHai@merriam-webster.com", "firstName", "Van", "lastName", "Hai"),
                Map.of("email", "NguyenHuyBach@cmu.edu", "firstName", "Huy", "lastName", "Bach"),
                Map.of("email", "TranVietHoang@wired.com", "firstName", "Viet", "lastName", "Hoang"),
                Map.of("email", "TranPhuongAnh@usda.gov", "firstName", "Phuong", "lastName", "Anh"),
                Map.of("email", "DucAnh@feedburner.com", "firstName", "Duc", "lastName", "Anh"),
                Map.of("email", "PhuocDepTrai@dailymail.co.uk", "firstName", "Phuoc", "lastName", "DepTrai"),
                Map.of("email", "VietDung@feedburner.com", "firstName", "Viet", "lastName", "Dung"),
                Map.of("email", "QuocHung@dailymail.co.uk", "firstName", "Quoc", "lastName", "Hung"));

        List<Product> productList = categoryRunner.generateCategory();
        UserEntity admin = createUser("admin", "123", ADMIN, true);
        UserEntity user = createUser("ducnbk7a1@gmail.com", "123", USER, false);
        UserEntity user2 = createUser("phuocdeptraivl@gmail.com", "123", USER, false);
        UserEntity user3 = createUser("helloWorld@gmail.com", "123", USER, false);
        UserEntity user4 = createUser("SeekNDstroy750@gmail.com", "123", USER);

        Shop defaultShop = new Shop("Laptop shop", "https://down-bs-vn.img.susercontent.com/b209fadc529ed6b57b5c00a335f66ad8_tn");
        Shop shop = new Shop("User shop", "https://down-bs-vn.img.susercontent.com/019ed89d540c28d61fbd0e29761ebd4a_tn");
        Shop shop2 = new Shop("Phuoc shop", "https://down-bs-vn.img.susercontent.com/019ed89d540c28d61fbd0e29761ebd4a_tn");
        Shop shop3 = new Shop("Hello world shop", "https://down-bs-vn.img.susercontent.com/019ed89d540c28d61fbd0e29761ebd4a_tn");

        user4.setShop(defaultShop);
        user.setShop(shop);
        user2.setShop(shop2);
        user3.setShop(shop3);

        userRepository.saveAll(List.of(user4, user, user2, user3, admin));

        shop.addProduct(productList.subList(0, 8));
        shop2.addProduct(productList.subList(8, 16));
        shop3.addProduct(productList.subList(16, 20));
        defaultShop.addProduct(productList.subList(20, productList.size()));
        shopRepository.saveAll(List.of(shop, shop2, shop3, defaultShop));

        Address address1 = Address.builder()
                .address("89 Le Thi Hong Gam")
                .addressType(AddressType.HOME)
                .phone("0905505778")
                .name("Trinh Van Manh")
                .user(user)
                .build();
        Address address2 = Address.builder()
                .address("89 Le Thi Hong Gam")
                .addressType(AddressType.HOME)
                .phone("0945505778")
                .name("Nguyen Thi Hong Thuy")
                .user(user)
                .build();

        // ORDER DETAIL
        List<OrderItem> list1 = productList.subList(0, 3).stream()
                .map(item -> new OrderItem(item, 1))
                .collect(Collectors.toList());
        List<OrderItem> list2 = productList.subList(productList.size() - 5, productList.size()).stream()
                .map(item -> new OrderItem(item, 1))
                .collect(Collectors.toList());
        List<OrderItem> list3 = productList.subList(productList.size() - 2, productList.size()).stream()
                .map(item -> new OrderItem(item, 1))
                .collect(Collectors.toList());
        Order order1 = new Order(UPFRONT, list1, address1);
        Order order2 = new Order(ONLINE, list2, address2);
        Order order3 = new Order(ONLINE, list3, address1);

        order1.setState(RECEIVED);
        order2.setState(DELIVERING);
        order3.setState(CONFIRMED);

        order1.setUser(user);
        order2.setUser(user);
        order3.setUser(user);

        addressRepository.saveAll(List.of(address1, address2));
        orderRepository.saveAll(List.of(order1, order2, order3));
        orderItemRepository.saveAll(list1);
        orderItemRepository.saveAll(list2);
        orderItemRepository.saveAll(list3);

        generateComment(list, productList);
        createElasticProduct(productList);
    }

}
