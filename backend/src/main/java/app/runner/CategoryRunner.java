package app.runner;

import app.model.postgres.Category;
import app.model.postgres.CategoryAttribute;
import app.model.postgres.Product;
import app.repository.postgres.AttributeValueRepository;
import app.repository.postgres.CategoryRepository;
import app.repository.postgres.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.runner.RunnerUtil.addAttribute;
import static app.runner.RunnerUtil.createAttribute;

@Component
@RequiredArgsConstructor
public class CategoryRunner {
    private static final List<Product> products = new ArrayList<>();
    private static final List<Category> categories = new ArrayList<>();
    private static final List<Category> subcategories = new ArrayList<>();
    private final AttributeValueRepository attributeValueRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    CategoryAttribute colorAttribute2 = createAttribute("Màu sắc", List.of("Bạc", "Đen", "Trắng", "Đỏ", "Nâu", "Xám", "Cam"));

    private void createGrocery() {
        Category groceryCategory = new Category("Tạp hóa");
        Product GroceryItem1 = new Product(
                "Bánh Que Ticky Socola Sữa Dâu Tây Thái Lan |Hộp 20G/ 6Que, Đồ Ăn Vặt Cao Cấp",
                4850,
                "Thông tin sản phẩm: Bánh Que Ticky Socola Sữa Thái Lan\n" +
                        "- Hướng dẫn sử dụng:  Ăn trực tiếp \n" +
                        "- Hướng dẫn bảo quản: Nhiệt độ thường, để ở nơi khô ráo, thoáng mát. Tránh ánh nắng trực tiếp\n" +
                        "- Trọng lượng: 20g\n" +
                        "- Hạn sử dụng: 12 tháng kể từ ngày sản xuất\n"
        );
        GroceryItem1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7uh0josgm9y32.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7uh18qtu01y5b.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7uh0bsy001f0f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7uh0jnyhuer89.webp"
        ));
        Product GroceryItem2 = new Product(
                "Oishi Snack Đậu Xanh Nước Cốt Dừa Túi Zip 70g, Tôm Sốt Mật Ong, Tôm Cay Nồng",
                11500,
                "Sản phẩm bánh Snack Oishi\n" +
                        "\n" +
                        "Thành phần : Bột mì, Dầu ăn (với chất chống oxy hóa (E320 & E321)), Bột gạo, Bột bắp, Đường, Đậu xanh (6%), Sữa gầy, Muối i-ốt, Hương sữa dừa giống tự nhiên, Chất nhũ hóa (E471), Màu thực phẩm tự nhiên (E164), Màu thực phẩm tồng hợp (E133)"
        );
        GroceryItem2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7i0x0rpox6k6d.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7i0x0rzpx4s7b.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7i0x0rzoilvd5.webp"
        ));
        Product GroceryItem3 = new Product(
                "Dừa Viên Ăn Kiêng, bánh ăn kiêng hỗ trợ giảm cân (100gr / 200gr / 300gr / 500gr) - Tạp Hóa Mum Mum",
                44200,
                "THÔNG TIN CHI TIẾT:\n" +
                        "- Thương hiệu: MUM MUM \n" +
                        "- Thành phần chính: Dùa tươi, lòng trắng trứng \n" +
                        "- Trọng lượng: 100gr , 200gr , 300gr , 500gr\n" +
                        "- HSD: 6 tháng\n" +
                        "- Xuất xứ: Việt Nam"
        );
        GroceryItem3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m86bj71ud37258.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7s4onxbq1td1a.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7s4onxbq1dvbb.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7s4onxbsuy993.webp"
        ));
        Product GroceryItem4 = new Product(
                "Thạch Dừa Minh Châu 1KG TÚI LỚN - Thạch Dừa Ăn Liền Tuổi Thơ Đặc Sản Bến Tre - Thạch Dừa Ruvask",
                15000,
                "Thạch Dừa Minh Châu – gợi nhớ những ngày tuổi thơ trong lành với TÚI LỚN 1KG \n" +
                        "Được làm từ nước dừa tươi lên men tự nhiên, thạch dừa mang hương vị dai giòn, thanh mát và thơm lừng, đưa bạn trở về những ký ức giản dị bên ly chè thạch mát lạnh ngày xưa.    \n" +
                        "Thành phần: Thạch dừa, nước, đường cát – 100% tự nhiên, an toàn  \n" +
                        "Hạn sử dụng: 1 năm kể từ ngày sản xuất  \n" +
                        "Xuất xứ: Minh Châu, Bến Tre – thương hiệu đặc sản chính gốc\n" +
                        "Hương vị tự nhiên, thanh mát, không chất bảo quản – gợi nhớ tuổi thơ trong từng miếng thạch.  \n" +
                        "Dai giòn, dễ kết hợp với chè, trà sữa hay trái cây.  \n" +
                        "Giàu chất xơ, bổ dưỡng và tốt cho sức khỏe."
        );
        GroceryItem4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7dgj8cym2mw14.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7dgj8cynh7c32.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7dgj8cym2k6b7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7dgj8cyknzqa8.webp"
        ));
        Product GroceryItem5 = new Product(
                "Sách 118 nguyên tố hóa học",
                55000,
                "TIẾP CẬN VỚI HOÁ HỌC SỚM ĐỂ CON TỰ TIN BƯỚC VÀO THCS\n" +
                        "Ba mẹ lo lắng khi con bước vào cấp 2 sẽ phải đối mặt với bộ môn hoá học khó nhằn. Cuốn sách 118 nguyên tố hóa học thú vị này sẽ đem đến cho con góc nhìn và cách tiếp cận mới, khiến con yêu thích việc học Hóa.\n" +
                        "Những lợi ích vàng của bộ sách:\n" +
                        "-Tập trung vào kiến thức trọng tâm của 118 nguyên tố trong bảng tuần hoàn.\n" +
                        "-Những nguyên tố hoá học phức tạp, khó nhớ được giải thích dễ hiểu.\n" +
                        "-Hơn 300 truyện tranh minh hoạ hình sinh động, bắt mắt giúp con dễ dàng tiếp thu và ghi nhớ."
        );
        GroceryItem5.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rd5t-m6parmrsz47gbe.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rd4s-m6parmzao3z57b.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rd4y-m6parn54fmksbc.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rd6y-m6parnd66o4b57.webp"
        ));
        Product GroceryItem6 = new Product(
                "50 gói [ tặng 1 ] LƯƠNG KHÔ HẢI CHÂU MIX 3 vị [đậu xanh, ca cao, tổng hợp ]combo đồ ăn vặt giá rẻ, tiện lợi, dinh dưỡng",
                117000,
                "50 gói [ tặng 1 ] LƯƠNG KHÔ HẢI CHÂU MIX 3 vị [đậu xanh, ca cao, tổng hợp ]combo đồ ăn vặt giá rẻ, tiện lợi, dinh dưỡng\n" +
                        "hạn dùng 12 tháng"
        );
        GroceryItem6.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9bn15wq7v5z9f.webp"
        ));
        Product GroceryItem7 = new Product(
                "Set nguyên liệu tự làm túi rút thêu hoa nhiều mẫu thiết kế độc quyền Tiệm tạp hoá Nhà May",
                65000,
                "Set nguyên liệu tự làm túi rút thêu hoa  – Tự tay thêu và khâu túi rút handmade cho người chưa bao giờ cầm kim.\n" +
                        "Bạn từng nghĩ khâu túi là chuyện khó? Không hề nha! Với bộ nguyên liệu khâu túi rút của May, bạn hoàn toàn có thể tự tay làm nên một chiếc túi xinh xắn, tiện dụng mà không cần phải là người “tay nghề cao”."
        );
        GroceryItem7.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m80dfsdt9sn8a6.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m80dfsdtb77o58.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m80dfsdtcls45a.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m80dfsdt8e2sce.webp"
        ));
        Product GroceryItem8 = new Product(
                "ô mai mix vị 408g",
                28000,
                "\uD83C\uDD98\uD83C\uDD98Omai ăn là nghiện đây ăn không nghiện em trả lại xiền\n" +
                        "\uD83C\uDF3BÔmai mận dòn,\n" +
                        "\uD83C\uDF3Bomai cherry dòn\n" +
                        "\uD83C\uDF3Bômai đào dòn ,\n" +
                        "\uD83C\uDF3Bomai Việt quất dòn\n"
        );
        GroceryItem8.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-491ok2dt5dkvea.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-v3ycmqjt5dkvae.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-ajuw9f9v5dkv3c.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-he2t9f9v5dkvd0.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-zh0hao9v5dkv5a.webp"
        ));
        Product GroceryItem9 = new Product(
                "Sách Tiểu Thuyết Cực Hay : Rừng Na Uy + Điều Kỳ Diệu Của Tiệm Tạp Hóa Namiya (tùy chọn) (Có lẻ)",
                62000,
                "Khách nhận hàng quay video bóc hàng giúp shop, bên shop chỉ hỗ trợ khiếu nại khi có video bóc hàng quay bill dán rõ ràng ạ   \n" +
                        " Chúng tớ rất vui mừng và hân hoan thông báo đến các bạn về sự ra mắt của Cửa Hàng Mới Của Chúng Tớ, nơi mà chúng tớ cam kết mang đến cho bạn những cuốn sách tuyệt vời nhất. Để đánh dấu bước khởi đầu này, chúng tớ xin gửi tặng đến các bạn mã sale trực tiếp, giúp bạn tiết kiệm hơn khi mua.   \n"
        );
        GroceryItem9.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-7reo1-m8bqnh5bs78n5d.webp"
        ));
        Product GroceryItem10 = new Product(
                "Bán sỉ Rong Biển Vụn Rắc Cơm,rong biển khổng lồ, rong biển sư tử vụn rắc cơm 128g hot nhất",
                37810,
                "1 bịch Rong Biển sư tử vụn rắc cơm gồm có 10 gói nhỏ khi ăn rất thơm ngon tiện lợi. các rất thích có thể ăn trực tiếp hay ăn cùng cơm .Dùng cho các bé không ăn rau xanh và bổ xung rất nhiều kháng chất có lợi cho sức khỏe. đang có mặt tại shop với giá tốt nhất thị trường\n" +
                        "#rongbien#rongbienvunraccom#rongbiensutuvun#snack#doanvat#combo#banhkeo#rongbientroncom"
        );
        GroceryItem10.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6trer1cudkofe.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6trer58nad472.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6trer08ulh413.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6trer3aq4pk5e.webp"
        ));
        List<Product> myProducts = List.of(GroceryItem1, GroceryItem2, GroceryItem3, GroceryItem4, GroceryItem5, GroceryItem6, GroceryItem7, GroceryItem8, GroceryItem9, GroceryItem10);
        groceryCategory.addProduct(myProducts);
        products.addAll(myProducts);
        categories.add(groceryCategory);
    }

    private void createBeauty() {
        Category beautyCategory = new Category("Làm đẹp");
        Product BeautyProduct1 = new Product(
                "Tẩy Tế Bào Chết Nha Đam Làm Sạch Sâu Bụi Bẩn Da Mặt, Tẩy tbc mặt giúp loại bỏ bã nhờn dưỡng mềm mịn",
                35000,
                "Thông tin sản phẩm:\n" +
                        "- Tên sản phẩm: Gel tẩy tế bào chết mặt nha đam\n" +
                        "- Khối lượng: 100g/tuýp\n" +
                        "- Hạn sử dụng: 3 năm\n" +
                        "- Xuất xứ: nội địa trung\n" +
                        "- Thành phần: nha đam"
        );
        BeautyProduct1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6j5nxzyg1whb6.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6j5o676hf1j03.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6j5o2r5g3tz49.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m6j5nxzyhggxb2.webp"
        ));
        Product BeautyProduct2 = new Product(
                "Tinh chất dưỡng sáng da 40ml - serum trắng da giúp loại bỏ mụn, làm trắng da và thu nhỏ lỗ chân lông",
                8119000,
                "\uD83D\uDCA5\uD83D\uDCA5 Thành phần chính:\n" +
                        "Niacinamide\n" +
                        "Ethyl Ascorbic Acid\n" +
                        "Sodium Hyaluronate\n" +
                        "Panthenol\n" +
                        "Chiết xuất rong biển Osmundaceae"
        );
        BeautyProduct2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9fsrh1tty8773.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8fu3b0on5hjc9.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8fu3b0ekqyvee.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8fu3b0ekqy648.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8fu47zobcxw57.webp"
        ));
        Product BeautyProduct3 = new Product(
                "Kem Dưỡng Da Tay, Da Chân Và Móng Vaseline Giúp Dưỡng Ẩm Sâu, Làm Mềm Da C2Y 24h Deep Moisture 60ml",
                390000,
                "Giúp dưỡng ẩm, làm mềm da tay và\uFEFF móng tay trong 24h\n" +
                        "- Em dưỡng da tay và móng Vaseline Deep Moisture Hand &\u200E Nail Cream chứa Keratin là protein cấu\uFEFF trúc dạng sợi cấu tạo nên móng tay và tóc giúp dưỡng ẩm sâu\u202A cho da tay\u202A bạn không còn\u200B bị khô ráp.\n" +
                        "- Ngoài ra còn dưỡng móng bạn\u200E chắc khoẻ hơn gấp\u200E 10 lần chỉ\u200E sau 2 tuần sử\u202A dụng, phù\u200B hợp cho những\u200E ai thường xuyên làm móng dẫn tới móng\u200B yếu, dễ gãy \n" +
                        "- Công nghệ dưỡng ẩm 3 bước độc quyền của\u200E Vaseline giúp tăng độ ẩm da tay lên gấp\u200E 6\u202A lần. Da tay mềm\u200B mại, ẩm mịn tức thì, không\u200B gây nhờn dính.\n" +
                        "- Chất kem dạng\uFEFF cream đặc nhưng thấm nhanh như lotion.\u202A Sau khi bôi 1 lớp mỏng\u200E sẽ ngay lập tức thấy\u200E da tay\uFEFF mềm hơn, không còn khô ráp, sần sùi\n" +
                        "- Hương\u202A thanh lịch và tinh tế với hương đầu là hương cam, quýt; hương giữa: hoa\u202A hồng, nhài, phong lan và\u200B kết thúc bằng xạ hương, hổ phách, tay vừa mềm mại vừa thơm thơm ai cũng thích."
        );
        BeautyProduct3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8atjq8yxw935a.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8atjq84z43280.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8avtp4oon72fa.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8atjq8yxw8e9c.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8avtp4oon7r02.webp"
        ));
        Product BeautyProduct4 = new Product(
                "cọ silicon mềm hai đầu quét mặt nạ chăm sóc da cho nữ (màu trắng + hồng)",
                119000,
                "Mềm mại, bền, độ dài vừa phải, nhanh khô, vừa vặn, tiện lợi\n" +
                        "Thiết kế cân nhắc: Đầu hình con dao của bàn chải mặt nạ silicon này phù hợp với thiết kế tiện dụng và có thể hoàn toàn phù hợp với các chi tiết khác trên khuôn mặt như mặt và mép mũi, để bạn có thể hoàn thiện làn da tốt hơn Công việc chăm sóc.\n" +
                        "Chất liệu bền: Tay cầm và đầu cọ của cọ mặt nạ này được làm bằng silicon cao cấp, có hình thức đẹp, độ dài vừa phải và cầm nắm thoải mái. Nó sẽ không bị nứt ngay cả sau khi sử dụng lâu dài.\n" +
                        "Ứng dụng rộng rãi: Bàn chải mặt nạ silicon mềm này thích hợp để làm sạch và trộn mặt nạ đất sét, mặt nạ bùn, thuốc mỡ, kem mặt, tinh chất dưỡng da mặt và mặt nạ tự làm.\n"
        );
        BeautyProduct4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/cn-11134211-7r98o-lnmkawyapwgref.webp",
                "https://down-vn.img.susercontent.com/file/cn-11134211-7r98o-lnmkawyarb17d3.webp",
                "https://down-vn.img.susercontent.com/file/cn-11134211-7r98o-lnmkawyaspln20.webp",
                "https://down-vn.img.susercontent.com/file/cn-11134211-7r98o-lnmkawyau46360.webp"
        ));
        Product BeautyProduct5 = new Product(
                "Gương tròn kèm lược tiện lợi hoạ tiết cute T8 T5",
                159000,
                "Có hàng sẵn tại kho -  Sỉ tận gốc -  Không qua trung gian!"
        );
        BeautyProduct5.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mqkeu8ch4v1c.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6ig6d77d7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6ig7rrna6.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6if31kv8e.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6if4g5bad.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6if5upr9d.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6if79a7c7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m1mri6ifcvjz14.webp"
        ));
        Product BeautyProduct6 = new Product(
                "Pad Rửa Mặt Silicone TMR Neon Pink Làm Sạch Da Loại Bỏ Bụi Bẩn như Miếng rửa mặt sephora",
                14900,
                "- Thiết kế nhỏ gọn mini vừa tay, giúp dễ dàng rửa mặt, làm sạch sâu lỗ chân lông, lấy sạch bụi bẩn, bã nhờn dư thừa\n" +
                        "- Mặt gai silicone mềm giúp lấy sạch lớp da chết sần sùi trên bề mặt da, nhẹ nhàng không gây ra kích ứng\n" +
                        "- Mang lại khả năng làm sạch hiệu quả mà không gây khô da, vẫn giữ lại độ ẩm dịu nhẹ cho da\n" +
                        "- Sử dụng chuyên dụng với sữa/ gel rửa mặt tạo bọt, hiệu quả và đơn giản dễ thực hiện chăm da tại nhà"
        );
        BeautyProduct6.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8yapm6jao3r84.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8yapnuquk5j0a.webp"
        ));
        Product BeautyProduct7 = new Product(
                "Lược hello kitty rỗng diy sườn lược dễ thương tạo kiểu anime lông tơ túi khí lược ướt và khô kép sinh viên T2",
                18100,
                "Tính năng:\n" +
                        "1. Thiết kế tinh tế: Lược rỗng Sanrio sử dụng thiết kế rỗng tinh tế, không chỉ có tác dụng thẩm mỹ mạnh mẽ mà còn tăng cảm giác thoải mái và cầm nắm khi cầm.\n" +
                        "2. Chất liệu cao cấp: Chiếc lược này được làm từ chất liệu nhựa cao cấp, độ bền cao và chống mài mòn, không dễ biến dạng và tuổi thọ lâu dài.\n" +
                        "3. Lược tinh tế: lược thiết kế tốt, có thể chải tóc dễ dàng mà còn có thể xoa bóp vừa phải, cải thiện lưu thông máu trên da đầu.\n" +
                        "4. Di động và nhẹ: Lược rỗng Sanrio sử dụng thiết kế nhẹ, dễ dàng mang theo, dù bạn đang đi đường, đi làm hay ở nhà đều có thể sử dụng được.\n" +
                        "5. Món quà hoàn hảo: Chiếc lược này là một lựa chọn quà tặng hoàn hảo cho bạn bè hoặc gia đình của bạn do thiết kế độc đáo và tinh tế của nó."
        );
        BeautyProduct7.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m2cilfnjig1gb7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m2cilfnk4x4k4b.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m2cilfnk23zodf.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m2cilfnk3ik458.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134211-7ras8-m2cilfnk6bp032.webp"
        ));
        Product BeautyProduct8 = new Product(
                "[Bộ trang điểm] Bộ Sản Phẩm Kem Nền trang điểm MSMEESHU Gồm 4 Món Đa Năng Tiện Dụng",
                65000,
                "Dành cho: mọi người \n" +
                        " Loại da thích hợp: Mọi loại da\n" +
                        " Tác dụng: Trang điểm \n" +
                        " Đặc điểm sản phẩm: Thông thường\n" +
                        " Màu sắc: hai màu\n" +
                        " Màu sắc: Màu tự nhiên\n" +
                        " Xuất xứ: Trung Quốc \n" +
                        " Phân loại màu sắc: 01#， 02#"
        );
        BeautyProduct8.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-22120-5jopu0zsbkkv33.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22120-grgnea1sbkkv58.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22120-5kq5mxzsbkkv0f.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22120-wb80uuzsbkkvc0.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22120-9c9crtzsbkkv08.webp"
        ));
        Product BeautyProduct9 = new Product(
                "Bộ sản phẩm dưỡng da -căng bóng chăm sóc da mặt EXGYAN Bộ 6 món",
                132000,
                "SET TRẮNG DA, NÂNG CƠ, CHỐNG LÃO HÓA EXGYAN, FULL SIZE\uD83E\uDD29\uD83E\uDD29GIÁ HÃNG ĐANG SALE SỐC\n" +
                        "Hộp sang xịn, hàng nội địa Trung chính\n" +
                        "\uD83D\uDC95Sét gồm 5 món\n" +
                        "Sữa rửa mặt trắng da 100ml\n" +
                        "Tonner 120ml\n" +
                        "Kem dưỡng trắng nâng cơ"
        );
        BeautyProduct9.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/de0cf2cdb96fcfec829388d3b3441e5f.webp"
        ));
        Product BeautyProduct10 = new Product(
                "Kem dưỡng da tay Maycreate mềm mịn nội địa Trung chính hãng 30g",
                13000,
                "Hàng nội địa Trung của Maycreate\n" +
                        "Kem tay dưỡng ẩm MayCreate hay M'ayCreate giúp dưỡng ẩm, mềm da\n" +
                        "- Giúp ngăn ngừa khô da, nứt da, lẻ, hanh, xước móng rô,...\n" +
                        "- Lưu lại trên da tay hương thơm chiết xuất từ tự nhiên dịu nhẹ, dễ chịu. \n" +
                        "- Có nhiều hương thơm nên shop lấy ngẫu nhiên k theo yêu cầu nha\n" +
                        "- Dung tích 30g\n" +
                        "Cùng với Maycreate, kem tay của hãng Sadoer, Seomou cũng ra các mẫu kem tay với bao bì bắt mắt, nếu các bạn đã sử dụng xịt thơm của maycreate trước đó chắc hẳn sẽ quen với mẫu kem tay này. Chủ đề bao bì mẫu kem tay mới của hãng Sadoer, Seomou mang đậm màu sắc chủ đề của mẫu xịt thơm Maycreate mà hãng này đã làm trước đó. Mẫu kem tay xinh xẻo, trẻ trung, hương vị thơm mát thực sự hấp dẫn với người dùng.\n"
        );
        BeautyProduct10.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-ly5ifmm671jkd0.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lqbqvvgclt1388.webp",
                "https://down-vn.img.susercontent.com/file/eb7ab0d36be2f965347870b9992082fb.webp"
        ));
        List<Product> myProducts = List.of(BeautyProduct1, BeautyProduct2, BeautyProduct3, BeautyProduct4, BeautyProduct5, BeautyProduct6, BeautyProduct7, BeautyProduct8, BeautyProduct9, BeautyProduct10);
        beautyCategory.addProduct(myProducts);
        products.addAll(myProducts);
        categories.add(beautyCategory);
    }

    private void createFashion() {
        CategoryAttribute colorAttribute = createAttribute("Màu sắc", List.of("Bạc", "Đen", "Trắng", "Xanh dương", "Vàng", "Đỏ", "Nâu", "Xám", "Xanh lá", "Kem", "Hồng", "Tím", "Cam"));
        CategoryAttribute materialAttribute = createAttribute("Chất liệu", List.of("Cotton", "Thô", "Lụa", "Nỉ", "Jeans", "Ren", "Kate"));
        CategoryAttribute sizeAttribute = createAttribute("Kích cỡ", List.of("L", "M", "XL", "XXL", "S"));
        CategoryAttribute bodyAttribute = createAttribute("Kiểu dáng", List.of("Regular fit", "Slim fit", "Ngoại cỡ"));
        CategoryAttribute shoesSizeAttribute = createAttribute("Kích cỡ", List.of("40", "39", "41", "42", "43", "44", "38"));

        Category menClothFashion = new Category("Đồ nam");
        menClothFashion.addAttribute(colorAttribute);
        menClothFashion.addAttribute(materialAttribute);
        menClothFashion.addAttribute(sizeAttribute);
        menClothFashion.addAttribute(bodyAttribute);

        Category menShoesFashion = new Category("Giày - Dép nam");
        menShoesFashion.addAttribute(colorAttribute);
        menShoesFashion.addAttribute(shoesSizeAttribute);

        Category menBagFashion = new Category("Túi thời trang nam");
        menBagFashion.addAttribute(colorAttribute);
        menBagFashion.addAttribute(materialAttribute);

        Category menFashion = new Category("Thời trang nam");

        Category womenClothFashion = new Category("Đồ nữ");
        womenClothFashion.addAttribute(colorAttribute);
        womenClothFashion.addAttribute(materialAttribute);
        womenClothFashion.addAttribute(sizeAttribute);
        womenClothFashion.addAttribute(bodyAttribute);

        Category womenAccessoryFashion = new Category("Trang sức nữ");
        womenAccessoryFashion.addAttribute(colorAttribute);
        womenAccessoryFashion.addAttribute(shoesSizeAttribute);

        Category womenShoesFashion = new Category("Giày - Dép nữ");
        womenShoesFashion.addAttribute(colorAttribute);
        womenShoesFashion.addAttribute(shoesSizeAttribute);

        Category womenBagFashion = new Category("Túi thời trang nữ");
        womenBagFashion.addAttribute(colorAttribute);
        womenBagFashion.addAttribute(materialAttribute);

        Category womenFashion = new Category("Thời trang nữ");

        List<Category> menSubcategories = List.of(menShoesFashion, menBagFashion, menShoesFashion);
        List<Category> womenSubcategories = List.of(womenClothFashion, womenAccessoryFashion, womenShoesFashion, womenBagFashion);

        menFashion.addSubCategory(menSubcategories);
        womenFashion.addSubCategory(womenSubcategories);

        categories.addAll(List.of(menFashion, womenFashion));
        subcategories.addAll(menSubcategories);
        subcategories.addAll(womenSubcategories);
    }

    private void createPhone() {
        CategoryAttribute brandAttribute = createAttribute("Thương hiệu", List.of("SAMSUNG", "OPPO", "Xiaomi", "Masstel", "Nokia", "Apple", "Real me"));
        CategoryAttribute romAttribute = createAttribute("ROM", List.of("32GB", "64GB", "128GB", "256GB"));
        CategoryAttribute ramAttribute = createAttribute("RAM", List.of("4GB", "8GB", "2GB", "16GB"));
        CategoryAttribute frontAttribute = createAttribute("Camera trước", List.of("Dưới 8MP", "Từ 5MP đến 8MP", "Từ 8MP đến 12MP"));
        CategoryAttribute backAttribute = createAttribute("Camera sau", List.of("Dưới 8MP", "Từ 11MP đến 13MP", "Từ 8MP đến 10MP", "Trên 16MP"));

        //TABLET CATEGORY
        Category tabletCategory = new Category("Máy tính bảng");
        tabletCategory.addAttribute(colorAttribute2);
        tabletCategory.addAttribute(brandAttribute);
        tabletCategory.addAttribute(romAttribute);
        tabletCategory.addAttribute(ramAttribute);
        tabletCategory.addAttribute(frontAttribute);
        tabletCategory.addAttribute(backAttribute);

        //PHONE CATEGORY
        Category phoneCategory = new Category("Điện thoại");
        phoneCategory.addAttribute(brandAttribute);
        phoneCategory.addAttribute(colorAttribute2);
        phoneCategory.addAttribute(romAttribute);
        phoneCategory.addAttribute(ramAttribute);

        //MOBILE CATEGORY
        Category mobileCategory = new Category("Thiết bị đeo thông minh");
        mobileCategory.addAttribute(createAttribute("Danh mục", List.of("Đồng hồ thông minh", "Thiết bị thực tế ảo", "Thiết bị định vị GPS")));

        // ================================== // PRODUCT DETAIL // ================================== //
        Product Phone1 = new Product(
                "Điện thoại Apple iPhone 16 Pro Max 256GB",
                31890000,
                "Thông tin bảo hành:\n" +
                        "Bảo hành: 12 tháng kể từ ngày kích hoạt sản phẩm.\n" +
                        "Kích hoạt bảo hành tại: https://checkcoverage.apple.com/vn/en/\n" +
                        "Hướng dẫn kiểm tra địa điểm bảo hành gần nhất:\n" +
                        "Bước 1: Truy cập vào đường link https://getsupport.apple.com/?caller=grl&locale=en_VN \n" +
                        "Bước 2: Chọn sản phẩm.\n" +
                        "Bước 3: Điền Apple ID, nhập mật khẩu.\n" +
                        "Sau khi hoàn tất, hệ thống sẽ gợi ý những trung tâm bảo hành gần nhất.\n"
        );
        Phone1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdvs-m01bhb1twkvn33.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdxf-m01bu9wypdi590.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdxw-m01bhrwnbdhc39.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdy7-m01bios168qi41.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdxy-m01birjx5h5t31.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdyh-m01bjmk5p6m2d9.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdwj-m01bjo01o2cm06.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rdxd-m01bk67yxe0134.webp"
        ));
        Product Phone2 = new Product(
                "Điện thoại di động Xiaomi Redmi Note 14 4G/ 5GiPhone X",
                4270000,
                "Xiaomi Redmi Note 14 6GB/128GB không chỉ đơn thuần là một chiếc điện thoại thông minh, mà còn là giải pháp toàn diện cho mọi nhu cầu công nghệ của bạn. Được trang bị những tính năng nổi bật như bộ vi xử lý MediaTek Helio G99-Ultra, màn hình AMOLED FHD+, hệ thống camera AI 108MP, viên pin với dung lượng 5500mAh cùng thiết kế tinh tế, sản phẩm này hứa hẹn mang lại trải nghiệm vượt mong đợi trong phân khúc giá cả phải chăng. Nếu bạn đang tìm kiếm một chiếc smartphone có hiệu năng mạnh mẽ, camera chất lượng cao và thời lượng pin ấn tượng, Redmi Note 14 chắc chắn là lựa chọn không thể bỏ qua."
        );
        Phone2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m5tpwe8qyn2vf4.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m52dyauozx2u5c.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m52dyauozxav71.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m52dyaup2q7q78.webp"
        ));
        Product Phone3 = new Product(
                "Điện Thoại Xiaomi Redmi Note 14 6/128GB - 8/128GB - 8/256GB Quốc Tế ",
                4115000,
                "Xiaomi khởi động năm mới tại Việt Nam bằng dòng Redmi Note Series , trong đó Redmi Note 14 bản tiêu chuẩn nổi bật với chip Helio G99-Ultra mạnh mẽ, lớp kính chịu lực bền bỉ và viên pin 5500 mAh. Sản phẩm hứa hẹn đáp ứng tốt nhu cầu giải trí và chiến game trong phân khúc giá tầm trung."
        );
        Phone3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8346cg7be5q5f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m736ufo3sim016.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m736uh3zol7c90.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m736uj9yihq038.webp"
        ));
        Product Phone4 = new Product(
                "Điện thoại Samsung Galaxy A56 5G 8GB | 128GB, Màn hình 6.7\" - Chip Exynos 1580 - 50MP - 5000mAh",
                8499000,
                "✅ DI ĐỘNG VIỆT cung cấp các sản phẩm điện thoại và phụ kiện chính hãng - đầy đủ bảo hành\n" +
                        "✅ Hỗ trợ khắc phục lỗi, sự cố từ Online và hệ thống cửa hàng\n" +
                        "✅ SẢN PHẨM ĐÃ KÍCH HOẠT BẢO HÀNH ĐIỆN TỬ, LÀ MÁY MỚI, NGUYÊN SEAL 100%, VÀ VẪN ĐẢM BẢO CHÍNH SÁCH BẢO HÀNH CHÍNH HÃNG"
        );
        Phone4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7g64vrdrkae25.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7g64vrdq5pya7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7g64vrdor880a.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7g64vrdrkd412.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8bl6xull3t3f6.webp"
        ));
        Product Phone5 = new Product(
                "Điện thoại thông minh POCO X7 Pro 5G (12+256GB) | Màn hình 6.67\" 120Hz AMOLED | 8400-Ultra | Camera 50MP",
                10690000,
                "HIỆU NĂNG:\n" +
                        "- Vi xử lý Dimensity 8400-Ultra\n" +
                        "- TSMC 4nm manufacturing process\n" +
                        "- CPU: 8 nhân, thiết kế chỉ nhân hiệu năng cao, lên đến 3.25 GHz\n" +
                        "- GPU: Mali-G720\n" +
                        "Bộ nhớ LPDDR5X + UFS 4.0\n" +
                        "- 8GB+256GB / 12GB+256GB / 12GB+512GB¹\n" +
                        "- Hệ thống làm mát LiquidCool Technology 4.0 với POCO 3D IceLoop\n" +
                        "- Tối ưu hóa WildBoost 3.0"
        );
        Phone5.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m4srh2mt4ou006.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8vtov1gzn2f1f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8vtotzqiuzy09.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8vtouak30xz1f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8vtotymkhhjc0.webp"
        ));
        Product Phone6 = new Product(
                "Điện thoại Xiaomi Redmi Note 14 6GB | 128GB, Màn hình 6.67\" 120Hz - MediaTek Helio G99-Ultra - 5500 mAh",
                42690000,
                "1. Đặc điểm nổi bật của Xiaomi Redmi Note 14 128GB\n" +
                        "- Thiết kế sang trọng, tinh tế với bốn góc cạnh bo tròn cho cảm giác cầm nắm mềm mại.\n" +
                        "- Trang bị tấm nền AMOLED, màn hình 6.67 inch với độ phân giải Full HD+ cho hình ảnh sắc nét và sống động.\n" +
                        "- Sở hữu MediaTek Helio G99-Ultra giúp giải quyết mọi tác vụ một cách mượt mà.\n" +
                        "- Camera chính có độ phân giải 50MP cho phép chụp ảnh với độ chi tiết cực cao.\n" +
                        "- Được trang bị viên pin 5500mAh cho phép trải nghiệm cả ngày dài."
        );
        Phone6.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8bljp8vbcku7e.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m4y05jjoskoo6f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m4y05jjoprjs7e.webp"
        ));
        Product Phone7 = new Product(
                "Điện thoại Apple iPhone 15 128GB",
                16990000,
                "Thông số kỹ thuật:\n" +
                        "- 6.1″\n" +
                        "- Màn hình Super Retina XDR\n" +
                        "- Nút chuyển đổi Chuông/Im Lặng\n" +
                        "- Dynamic Island\n" +
                        "- Chip A16 Bionic với GPU 5 lõi\n" +
                        "- SOS Khẩn Cấp \n" +
                        "- Phát Hiện Va Chạm\n" +
                        "- Pin: Thời gian xem video lên đến 26 giờ\n" +
                        "- USB‑C: Hỗ trợ USB 2"
        );
        Phone7.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-llm05p5nkerg1c.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-llm05p5nltbw4f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-llm05p5nomgs9c.webp"
        ));

        Product Tablet1 = new Product(
                "Máy Tính Bảng Xiaomi Redmi Pad SE 6GB 128GB Chính Hãng BH 18 Tháng",
                4490000,
                "ĐẶC ĐIỂM NỔI BẬT\n" +
                        "- Trải nghiệm giải trí vượt trội với màn hình 11 inch, độ sáng 400nits và tần số quét 90Hz, mang đến hình ảnh sắc nét và sống động trong từng khung hình.\n" +
                        "- Sức mạnh sáng tạo không giới hạn với chip Snapdragon® 680, đảm bảo hiệu năng ổn định và xử lý mượt mà mọi tác vụ trong suốt cả ngày.\n" +
                        "-  Thiết kế tinh tế và thu hút với mặt lưng nhôm nguyên khối mỏng chỉ 7.36 mm, tạo cảm giác sang trọng và cầm nắm thoải mái."
        );
        Tablet1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9olij186kp08f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9kaa6yiuyai3e.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m34gz3nbr9uj2e.webp"
        ));
        Product Tablet2 = new Product(
                "-CLUBLU- Bảng Vẽ Điện Tử Laptop Bảng Vẽ Với Bút Mức Độ Áp Lực Hỗ Trợ OSU Vẽ Dạy Trực Tuyến AndroidPC",
                400000,
                "Mô tả sản phẩm:\n" +
                        "Màu sắc: Đen\n" +
                        "Giao diện: USB\n" +
                        "Chất liệu: ABS\n" +
                        "Sản phẩm áp dụng: Máy tính và điện thoại Android\n" +
                        "Kích thước: 252 × 160 × 5 mm\n" +
                        "Thương hiệu: CLUBLU\n" +
                        "Thành lập: United States\n" +
                        "Nơi đăng ký nhãn hiệu: United States\n" +
                        "Thiết kế sản phẩm: United States\n" +
                        "Nhà máy : China\n" +
                        "nơi giao hàng：TP.HCM\n"
        );
        Tablet2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rdxf-m13lsy5jrq1332.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rdwz-m13mt44shc3r29.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-7rdyc-m13mt5foox6c79.webp"
        ));
        Product Tablet3 = new Product(
                "NIXXOS Bao Da Có Bàn Phím Không Dây Cho iPad Trackpad 10.2'' iPad 7th/8th/9th Gen 10.9'' Air 4/ Air 5 2021/2020 Pro 11",
                366120,
                "Đặc điểm kỹ thuật\n" +
                        "\uD83D\uDCA8Phiên bản Bluetooth: Bluetooth 5.1 tiêu chuẩn\n" +
                        "\uD83D\uDCA8Phạm vi kết nối: 10 mét\n" +
                        "\uD83D\uDCA8Thời gian chờ: 100 ngày\n" +
                        "\uD83D\uDCA8Thời gian làm việc: Liên tục 90 giờ\n" +
                        "\uD83D\uDCA8Thời gian sạc: 2 giờ\n" +
                        "\uD83D\uDCA8Loại pin: Pin liti tích hợp\n" +
                        "\uD83D\uDCA8Dung lượng pin: 480 mAh\n" +
                        "\uD83D\uDCA8Tuổi thọ pin: 3 năm\n" +
                        "\uD83D\uDCA8Tuổi thọ phím: 3 triệu lần\n"
        );
        Tablet3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/bf8835ce8996970b300d6dc353c20e46.webp",
                "https://down-vn.img.susercontent.com/file/9ac79dd0ffaaabbb41d5b0b2ae8ecd3a.webp"
        ));
        Product Tablet4 = new Product(
                "Ốp Máy Tính Bảng Có Khay Đựng Bút Cho ipad pro M4 11 13 2024 Gen 10 9 8 Mini Air 7 6 5 M2 M3 2025",
                20900000,
                "Mô tả sản phẩm:\n" +
                        "• Mới 100\n" +
                        "• Bìa này được làm bằng vật liệu chất lượng cao\n" +
                        "• Đánh thức giấc ngủ tự động (iPad cần được đặt thành \"màn hình mở khóa\"), mở nắp để hoạt động trên máy tính bảng, lắp nắp vào trạng thái ngủ, giúp tiết kiệm điện năng và siêu mỏng.\n" +
                        "• Vỏ có chức năng tản nhiệt, có thể bảo vệ máy tính bảng của bạn đồng thời cân bằng nhiệt độ của máy tính bảng.\n" +
                        "• Vỏ áp dụng công nghệ cắt chính xác với các lỗ chính xác. Độ dày của vỏ chỉ vượt quá phần nhô ra của máy ảnh. Bảo vệ nút tốt hơn và không cản trở việc sử dụng chức năng nút bình thường."
        );
        Tablet4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/8edf355b4be25eae8f7153ad64a47f66.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-i9dakck01sjva8.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-sfee3ll01sjv8d.webp",
                "https://down-vn.img.susercontent.com/file/fdc14a5eb787104ff17f30c6a0d94323.webp",
                "https://down-vn.img.susercontent.com/file/be62f6a8bb541345690bd4f14e092eec.webp",
                "https://down-vn.img.susercontent.com/file/14acc27f1321ce5407f334c2f819c83d.webp",
                "https://down-vn.img.susercontent.com/file/ef1aaf0d860a3751909ce3e9b434f39b.webp"
        ));
        Product Tablet5 = new Product(
                "PVN7671 Bảng viết vẽ điện tử máy tính bảng ipad thông minh cho bé cao cấp 8.5 inch size lớn",
                209000,
                "✅ Kích thước: \n" +
                        "8.5 inch ( 22cm x 15cm)\n" +
                        "10 inch (25cm x 17cm)\n" +
                        "12 inch (28cm x 19cm)\n" +
                        "16 inch (35cm x 28cm)"
        );
        Tablet5.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134201-7ras8-m1fj0q64qpxb10.webp",
                "https://down-vn.img.susercontent.com/file/a69f65aa40bbec00ee83a2313d4b9321.webp",
                "https://down-vn.img.susercontent.com/file/4f050bd7937806a23d00a473030087fd.webp"
        ));
        Product Tablet6 = new Product(
                "Bao da Samsung Galaxy Tab A9 Plus A9+ A9Plus 11 inch SM-X210 X215 X216 2023 nam châm đóng mở tự động",
                35000,
                "Mặt trước chất liệu nhựa tổng hợp cao cấp\n" +
                        "Mặt sau TPU dẻo tháo lắp dễ dàng\n" +
                        "Bảo vệ an toàn cho thiết bị, ngăn chặn trầy xước hay nứt vỡ\n" +
                        "Bật / tắt thiết bị khi đóng / mở bao da tiện lợi"
        );
        Tablet6.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-ln2ddh8tipy073.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lovza6phg21a03.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lovza6phhglq26.webp"
        ));
        Product Tablet7 = new Product(
                "Máy tính bảng vẽ đồ họa XPPen Deco Fun với lực nhấn 8192 cấp độ dành cho trò chơi & bản vẽ & OSU",
                419000,
                "Dòng Deco Fun có ba kích cỡ và bốn màu: đen cổ điển, xanh không gian, xanh táo và đỏ carmine, để đáp ứng sở thích cá nhân của bạn. Chọn màu yêu thích của bạn và tận hưởng Deco Fun!"
        );
        Tablet7.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-gupvm5m8ebkv6d.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-ys5c6i98ebkvbd.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-haqancm9ebkvdc.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-bk1q4m68ebkv0a.webp",
                "https://down-vn.img.susercontent.com/file/sg-11134201-22110-tmaj7t88ebkv54.webp"
        ));

        Product Smart1 = new Product(
                "Thời Trang Unisex Silicon watchband Đồng Hồ Đeo Tay led Kỹ Thuật Số Thể Thao Đồng Hồ Nam Nữ Đồng Hồ Điện Tử",
                297000,
                "Ban nhạc\n" +
                        "Dây đeo cổ tay: Silicone\n" +
                        "Ban nhạc màu: đen, trắng, hồng\n" +
                        "Loại móc cài: Khóa\n" +
                        "Kích thước và trọng lượng\n" +
                        "Chiều dài đồng hồ: khoảng 25cm\n" +
                        "Đường kính quay số: khoảng 3,5cm\n" +
                        "Chiều rộng ban nhạc: khoảng 2,2cm\n" +
                        "Độ dày quay số: khoảng 8mm\n" +
                        "Trọng lượng đồng hồ: Khoảng 27g"
        );
        Smart1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/sg-11134301-7rfia-m9fduj3owq8029.webp"
        ));
        Product Smart2 = new Product(
                "Vòng Đeo Tay Thông Minh HUAWEI Band 10 Khung Hợp Kim Nhôm Tinh Tế | Phân Tích Giấc Ngủ Chuyên Nghiệp",
                890000,
                "Kích thước \n" +
                        "43.45 × 24.86 × 8.99 mm\n" +
                        "*8.99 mm là độ dày được đo ở vị trí mỏng nhất (không bao gồm vùng cảm biến).\n" +
                        "Kích thước cổ tay\n" +
                        "Trắng, Tím, Hồng\n" +
                        "120 - 190 mm\n" +
                        "Đen nhám, Xanh lá, Xanh dương, Đen\n" +
                        "130 - 210 mm\n" +
                        "Màn hình\n" +
                        "Kích thước\n" +
                        "Màn hình màu AMOLED 1.47 inch"
        );
        Smart2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m85613upezcyd8.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m85617tvlglg9e.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m7lqg0l9cjht5c.webp"
        ));
        Product Smart3 = new Product(
                "Đồng hồ thông minh thể thao ZL02D đo nhịp tim huyết áp giám sát sức khỏe cá tính cho nam, nữ",
                325000,
                "Sản phẩm có pin chờ từ 7-9 ngày\n" +
                        "Sản phẩm có phân loại thêm cả dây kim loại, cả nhà xem hình ảnh rõ ràng trước khi chọn phân loại. Khi mua dây kim loại sẽ được tặng kèm dây silicon nhé\n" +
                        "Mô tả:\n" +
                        "Một trợ thủ đắc lực cho việc tập luyện: Máy đếm bước đi, quãng đường / lượng calo đốt cháy, chế độ thể thao.\n" +
                        "Ngôn ngữ: tiếng Anh, tiếng Trung, tiếng Tây Ban Nha, tiếng Bồ Đào Nha, tiếng Pháp, tiếng Nhật, tiếng Nga,"
        );
        Smart3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/6fd51665537d070e64f2bda5653c55ef.webp",
                "https://down-vn.img.susercontent.com/file/961ee89e44026f72f98ab1ace94fda21.webp",
                "https://down-vn.img.susercontent.com/file/73598743a4d800ac26435d3237bd6471.webp",
                "https://down-vn.img.susercontent.com/file/c99772ed4dad99654d3106549c76e9cb.webp"
        ));
        Product Smart4 = new Product(
                "Đồng Hồ Thông Minh Watch 10 Series 10 Nam Nữ Nghe Gọi Thay Ảnh Cá Nhân Dynamic Kháng Nước",
                755000,
                "**THÔNG SỐ KỸ THUẬT:\n" +
                        "- Chip và RAM: Chip JL7012F6, RAM 256MB\n" +
                        "- Kiểu dáng: Đồng hồ sức khỏe thể thao\n" +
                        "- Kích thước: 42mm cho nữ ( Nam tay nhỏ ) và 46mm cho nam\n" +
                        "- Màn hình: 1.75 Inch (42mm) và 2.02 Inch HD (46mm) ( 485*525 Pixel )\n" +
                        "- Dây đeo có thể thay thế: Size 42mm sử dụng dây đeo Size 38/40/41mm - Size 46mm sử dụng dây đeo Size 42/44/45/49mm\n" +
                        "- Phương pháp sạc: Sạc không dây\n" +
                        "- Màu sắc: Đen, Trắng, Kem\n" +
                        "- Pin và dung lượng: Li-ion 320mAh ( sử dụng từ 1 đến 3 ngày )\n" +
                        "- Chống nước: IP67 ( Không: tắm, bơi ngâm hoặc tiếp xúc nước nóng )\n" +
                        "- Ngôn ngữ: Tiếng Việt, Anh...\n" +
                        "**BỘ SẢN PHẨM BAO GÔM: 1 mặt đồng hồ + 1 bộ dây đeo + 1 cáp sạc không dây + 1 hộp đựng\n" +
                        "( kèm thứ cảm ơn và quà tặng nếu có )"
        );
        Smart4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-ma05dbqo0gha71.webp"
        ));

        List<Product> phones = List.of(Phone1, Phone2, Phone3, Phone4, Phone5, Phone6, Phone7);
        List<Product> tablets = List.of(Tablet1, Tablet2, Tablet3, Tablet4, Tablet5, Tablet6, Tablet7);
        List<Product> smarts = List.of(Smart1, Smart2, Smart3, Smart4);
        List<Category> mySubcategories = List.of(phoneCategory, tabletCategory, mobileCategory);

        Category PhoneTablet = new Category("Điện thoại & Phụ kiện");
        PhoneTablet.addSubCategory(mySubcategories);

        //Link product to category
        phoneCategory.addProduct(phones);
        tabletCategory.addProduct(tablets);
        mobileCategory.addProduct(smarts);

        //Create Products
        products.addAll(phones);
        products.addAll(tablets);
        products.addAll(smarts);

        categories.add(PhoneTablet);
        subcategories.addAll(mySubcategories);
    }

    private void createLaptop() {
        CategoryAttribute computerBrand = createAttribute("Card màn hình", List.of("Intel UHD Graphic", "NVIDIA Geforce", "Intel HD Graphic", "Intel® UHD Graphics 620", "NVIDIA Geforce GTX"));
        CategoryAttribute computerCpu = createAttribute("CPU", List.of("Intel Core i3", "Intel Core i5", "Intel Core i7", "AMD"));
        CategoryAttribute computerCard = createAttribute("Card màn hình", List.of("Intel UHD Graphic", "NVIDIA Geforce", "Intel HD Graphic", "Intel® UHD Graphics 620", "NVIDIA Geforce GTX"));
        CategoryAttribute computerRam = createAttribute("RAM", List.of("4GB", "8GB", "2GB", "16GB", "32GB", "64GB"));
        CategoryAttribute computerSubPart = createAttribute("Danh mục", List.of("Quạt và tản nhiệt", "CPU - Bộ vi xử lý", "Mainboard - Bo mạch chủ", "VGA - Card màn hình", "Nguồn máy tính", "Ram máy tính"));

        //COMPUTER CATEGORY
        Category computerCategory = new Category("Máy Tính");
        computerCategory.addAttribute(colorAttribute2);
        computerCategory.addAttribute(computerRam);
        computerCategory.addAttribute(computerCard);
        computerCategory.addAttribute(computerBrand);
        computerCategory.addAttribute(computerCpu);

        //COMPUTER PART CATEGORY
        Category computerPartCategory = new Category("Linh kiện máy vi tính");
        computerPartCategory.addAttribute(colorAttribute2);
        computerPartCategory.addAttribute(computerSubPart);
        computerPartCategory.addAttribute(computerBrand);

        //LAPTOP CATEGORY
        Category laptopCategory = new Category("Laptop");
        laptopCategory.addAttribute(colorAttribute2);
        laptopCategory.addAttribute(computerRam);
        laptopCategory.addAttribute(computerCard);
        laptopCategory.addAttribute(computerCpu);
        laptopCategory.addAttribute(computerBrand);


        // ================================== // PRODUCT DETAIL // ================================== //
        Product Laptop1 = new Product(
                "Laptop AOC NoteBook giá rẻ 15.6 inch, Intel N5095, Ram 16GB, SSD 512GB, Win 11 - Bảo hành 2 năm",
                6490000,
                "Loại sản phẩm: Notebook\n" +
                        "Kích thước: 357.4 × 288.6 × 17.8mm\n" +
                        "Chất liệu: Bạc - Nhựa\n" +
                        "Màn hình: 15.6\" (16:9), độ phân giải 1920 × 1080 FHD\n" +
                        "Pin: Lithium-ion polymer, 7.6V / 4400mAh\n"
        );
        Laptop1.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtx04ich3b3f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtwzl34wa653.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtwyvua25qb4.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtwzqmwrxqd7.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtwztosb5z3f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m9jtwztesps7e1.webp"
        ));
        Product Laptop2 = new Product(
                "Laptop ASUS Laptop Chơi Game Intel Core i7 8086K Windows 11 RAM 16GB SSD 256GB / 512GB",
                7299000,
                "Thời hạn bảo hành 3 năm, dịch vụ hậu mãi được đảm bảo. Đặt hàng yên tâm\n" +
                        "Máy tính xách tay này có hệ thống Windows 11. Bạn có thể tải xuống bất kỳ phần mềm nào bạn cần từ trình duyệt của mình. Hệ thống đã khởi động thành công. Bạn có thể sử dụng sản phẩm ngay khi nhận được. Hệ thống này cũng bao gồm Excel và Word.Nếu sản phẩm bạn nhận được có vấn đề, vui lòng liên hệ với dịch vụ khách hàng ngay lập tức để được hỗ trợ trước khi đăng ký."
        );
        Laptop2.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m975z261t10u54.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m975z261t11j8c.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m975z261ufla71.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m975z26201v20f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m975z261uflz07.webp"
        ));
        Product Laptop3 = new Product(
                "Laptop Dell Vostro 14-3446 I5-4120U/8GB/256GB/14inch HD/VGA GT820M 2GB",
                2790000,
                "CẤU HÌNH:\n" +
                        "-CPU: Intel Core I5-4210U 4x1.7Ghz upto 2.7Ghz\n" +
                        "-RAM: 4GB/8GB DDR3L 1600Mhz (1 khe ram)\n" +
                        "-Màn hình: 14inch HD (1366x768)\n" +
                        "-VGA: NVIDIA GeForce 820M 2GB\n" +
                        "-Cổng kết nối: LAN, VGA, USB 3.0, 2x USB 2.0\n" +
                        "-Webcam: HD"
        );
        Laptop3.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m8qe0wnk3txg01.webp"
        ));
        Product Laptop4 = new Product(
                "Lenovo 14 inch 2025 Máy tính mới Intel Core i5 generasi ke-8, USB 3.0 Windows 11，8G+128GB",
                5129000,
                "Mô tả sản phẩm\n" +
                        "● Trọng lượng: khoảng 1,3kg\n" +
                        "● Kích thước màn hình: 14 inch\n" +
                        "● Vật liệu màn hình: IPS\n" +
                        "● Độ phân giải màn hình: 1920 * 1080\n" +
                        "● Mô hình CPU: Intel core i5\n" +
                        "●RAM:8GB\n" +
                        "●SSD：128GB\n" +
                        "● Hỗ trợ Bluetooth\n" +
                        "● Hệ điều hành: Windows 11"
        );
        Laptop4.addThumbnail(List.of(
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m73bwspumzq034.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m73bwspuoeag2f.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m73bwspupsuw0a.webp",
                "https://down-vn.img.susercontent.com/file/vn-11134207-7ra0g-m73bwspur7ege6.webp"
        ));
        Product lenovoX1 = new Product(
                "Laptop Lenovo X1 Cacbon Core i5 / 16Gb / SSD nvme 256Gb Hàng chính hãng",
                5119000
        );
        lenovoX1.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/cf/11/26/4cf197a9f7f3fb0fa8b19b4256007c06.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/14/fe/30/aac3e4f11c51dd25aaa6a170306cb525.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/47/65/3c/7f7a81b4e0965dfb571760efbc52bacd.jpg.webp"
        ));
        Product gigabyteG5 = new Product(
                "Laptop Gigabyte G5 i5 10500H/16GB/512GB/6GB RTX3060/15.6\"F/144Hz/Win11/(KC-5S11130SB)/Đen - Hàng chính hãng",
                22900000
        );
        gigabyteG5.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/26/2d/3c/022b0c13d0db6314a02694862d5ecc5f.png.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/5b/7d/bc/9e6ffc39ee385d1bb11a4a0f4ac5959b.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/bb/36/4d/c7142f7ce38d0d8693c983ebabe2ddd7.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/c8/7e/51/c9be82d906056c33113f2447ef49e24b.jpg.webp"
        ));
        Product acer5 = new Product(
                "Laptop Acer Nitro 5 AN515-58-52SP i5-12500H/8GB/512GB/Win11 (NH.QFHSV.001) - Hàng chính hãng",
                22900000
        );
        acer5.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/cf/98/24/6b65dc67b33f0d89197f96c9ed1f7c3b.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/ee/89/09/76ce7f8099de15976adeb5933c979fed.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/dc/cf/05/faa3662d46c65714baf69202ee879f1d.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/38/7a/8b/2706a7601d0a91eaffbd5f6d11465f46.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/02/d4/5a/37629f1ec4a67941daff0651745ac4fa.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/2c/7f/49/84c6c785306c5376b49204163edabe01.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/1b/d5/e5/2c85aa570e0b0fa6ee0a39a8a4f52f99.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/28/fb/c2/90b4b0258a136db6f8cddcf1088033f4.jpg.webp"
        ));
        Product lenovoIdea = new Product(
                "Máy tính xách tay Lenovo IdeaPad Gaming 3 R5-5600H | 8GB | 512GB | RTX 3050 - Hàng chính hãng",
                18490000
        );
        lenovoIdea.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/cf/11/26/4cf197a9f7f3fb0fa8b19b4256007c06.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/14/fe/30/aac3e4f11c51dd25aaa6a170306cb525.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/47/65/3c/7f7a81b4e0965dfb571760efbc52bacd.jpg.webp"
        ));
        Product acerAspire7 = new Product(
                "Máy Tính Xách Tay Laptop Acer Gaming Aspire 7 A715-42G-R4XX (R5 5500U/8GB RAM/256GB SSD/15.6 inch FHD/GTX1650 4G/Win11/Đen) - Hàng Chính Hãng",
                18490000
        );
        acerAspire7.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/4b/f1/d7/becb5398f0be90119a855804ad2a05ce.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/72/c5/1c/338929580747d9dca15ce4e376f8ca59.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/4b/f1/d7/becb5398f0be90119a855804ad2a05ce.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/bf/d0/da/718fab5322e2a3afd23d56ff395d00f8.jpg.webp"
        ));
        Product msiGaming63 = new Product(
                "Laptop MSI Gaming GF63 Thin 11SC-664VN (I5-11400H Gen 11th | 8GB DDR4 | SSD 512GB PCle | VGA GTX 1650 4GB GDDR6 | 15.6 FHD IPS 144Hz | Win11| Black) - Hàng Chính Hãng",
                15580000
        );
        msiGaming63.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/5a/da/b4/a69997c5426be8645dad029f763aaad1.JPG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/96/10/2d/98c1433cb18ae110120bdb5597a5e703.JPG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/05/df/3b/77e553682bb35af28f14b6b9785c0bb4.JPG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/70/14/1d/aa62e9d6e5d25ac34d7e656bbbc50cd6.JPG.webp"
        ));
        Product laptopHP = new Product(
                "Laptop HP 240 G8 6L1A1PA (i3-1115G4 | 8GB | 256GB | Intel UHD Graphics | 14' FHD | Win 11) Hàng chính hãng",
                13290000
        );
        laptopHP.addThumbnail(List.of("https://salt.tikicdn.com/cache/368x368/ts/product/78/a6/c6/204fd34e691b1edfb087739177833baf.png.webp"));
        Product asusTUF = new Product(
                "Laptop ASUS TUF Gaming A15 FA506ICB-HN355W (R5-4600H | 8GB | 512GB | GeForce RTX 3050 4GB | 15.6′ FHD 144Hz | Win 11) - Hàng Chính Hãng",
                18490000
        );
        asusTUF.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/c8/04/f7/c06db4481dab1f37787ae55186e74c70.PNG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/4b/4c/c1/a381024d9c93190a2e253dee96a9092c.PNG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/ec/0a/92/65f0d025509c8ceb73a1946881fd2b5d.PNG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/0b/19/5e/2ee7b535ae7da25a24b081a0ee42db25.PNG.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/14/a1/bc/e231952173de8b0ac1870058e1d009ce.PNG.webp"
        ));
        Product asusDash = new Product(
                "Laptop Asus TUF Dash F15 (FX517ZC-HN077W) (Core i5-12450H/8GB/512GB/RTX 3050 4GB/15.6-inch FHD/Win 11/Black)-Hàng chính hãng",
                21490000
        );
        asusDash.addThumbnail(List.of(
                "https://salt.tikicdn.com/cache/368x368/ts/product/98/be/bf/b4f8a30947df5071eb5ad4b21ca6d140.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/4e/9f/44/32715e8c88ee0766040c24e8d70afa08.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/71/a7/d4/5c56fcb4ec4cd7002c94335bd683bbb3.jpg.webp",
                "https://salt.tikicdn.com/cache/368x368/ts/product/68/e3/d3/66608671a19568117596af8bc5985783.jpg.webp"
        ));

        addAttribute(lenovoX1, Map.of("RAM", "16GB", "Thương hiệu", "Lenovo", "Card màn hình", "Intel® UHD Graphics 620", "CPU", "Intel Core i5"));
        addAttribute(gigabyteG5, Map.of("RAM", "16GB", "Thương hiệu", "Gigabyte", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(acer5, Map.of("RAM", "16GB", "Thương hiệu", "Acer", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(lenovoIdea, Map.of("RAM", "16GB", "Thương hiệu", "Lenovo", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(acerAspire7, Map.of("RAM", "8GB", "Thương hiệu", "Acer", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(msiGaming63, Map.of("RAM", "16GB", "Thương hiệu", "MSI", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(laptopHP, Map.of("RAM", "16GB", "Thương hiệu", "HP", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(asusTUF, Map.of("RAM", "8GB", "Thương hiệu", "Asus", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));
        addAttribute(asusDash, Map.of("RAM", "8GB", "Thương hiệu", "Asus", "Card màn hình", "NVIDIA Geforce GTX", "CPU", "Intel Core i5"));

        List<Product> computerProducts = List.of(acerAspire7, msiGaming63, laptopHP, asusTUF, asusDash);
        List<Product> laptopProducts = List.of(Laptop1, Laptop2, Laptop3, Laptop4, lenovoIdea, lenovoX1, gigabyteG5, acer5);
        List<Category> mySubcategories = List.of(laptopCategory, computerPartCategory, computerCategory);

        Category LaptopPC = new Category("Máy tính & Laptop");
        LaptopPC.addSubCategory(mySubcategories);
        laptopCategory.addProduct(laptopProducts);
        computerCategory.addProduct(computerProducts);

        products.addAll(computerProducts);
        products.addAll(laptopProducts);
        categories.add(LaptopPC);
        subcategories.addAll(mySubcategories);
    }

    public List<Product> generateCategory() {
        createGrocery();
        createBeauty();
        createFashion();
        createPhone();
        createLaptop();

        categoryRepository.saveAll(categories);
        categoryRepository.saveAll(subcategories);
        attributeValueRepository.saveAll(RunnerUtil.attributes.values());
        productRepository.saveAll(products);
        return products;
    }

}
