package app.Runner;

import app.Model.Category;
import app.Model.CategoryAttribute;
import app.Model.Product;
import app.Repository.AttributeValueRepository;
import app.Repository.CategoryRepository;
import app.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.Runner.RunnerUtil.addAttribute;
import static app.Runner.RunnerUtil.createAttribute;

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
        Product DaalMassor = new Product(
                "Daal Masoor 500 grams",
                2119000
        );
        DaalMassor.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/21/1.png",
                "https://i.dummyjson.com/data/products/21/2.jpg",
                "https://i.dummyjson.com/data/products/21/3.jpg"
        ));
        Product Macaroni = new Product(
                "Elbow Macaroni - 400 gm",
                1119000
        );
        Macaroni.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/22/1.jpg",
                "https://i.dummyjson.com/data/products/22/2.jpg",
                "https://i.dummyjson.com/data/products/22/3.jpg"
        ));
        Product FoodFlavour = new Product(
                "Orange Essence Food Flavou",
                1119000
        );
        FoodFlavour.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/23/1.jpg",
                "https://i.dummyjson.com/data/products/23/2.jpg",
                "https://i.dummyjson.com/data/products/23/3.jpg",
                "https://i.dummyjson.com/data/products/23/4.jpg",
                "https://i.dummyjson.com/data/products/23/thumbnail.jpg"
        ));
        Product FruitNut = new Product(
                "cereals muesli fruit nuts",
                5119000
        );
        FruitNut.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/24/1.jpg",
                "https://i.dummyjson.com/data/products/24/2.jpg",
                "https://i.dummyjson.com/data/products/24/3.jpg",
                "https://i.dummyjson.com/data/products/24/4.jpg",
                "https://i.dummyjson.com/data/products/24/thumbnail.jpg"
        ));
        Product GublabPowder = new Product(
                "Gulab Powder 50 Gram",
                5119000
        );
        GublabPowder.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/25/1.png",
                "https://i.dummyjson.com/data/products/25/2.jpg",
                "https://i.dummyjson.com/data/products/25/3.png",
                "https://i.dummyjson.com/data/products/25/4.jpg",
                "https://i.dummyjson.com/data/products/25/thumbnail.jpg"
        ));
        Product PlantHanger = new Product(
                "Plant Hanger For Home",
                5119000
        );
        PlantHanger.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/26/1.jpg",
                "https://i.dummyjson.com/data/products/26/2.jpg",
                "https://i.dummyjson.com/data/products/26/3.jpg",
                "https://i.dummyjson.com/data/products/26/4.jpg",
                "https://i.dummyjson.com/data/products/26/5.jpg",
                "https://i.dummyjson.com/data/products/26/thumbnail.jpg"
        ));
        Product WoodenBird = new Product(
                "Flying Wooden Bird",
                5119000
        );
        WoodenBird.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/27/1.jpg",
                "https://i.dummyjson.com/data/products/27/2.jpg",
                "https://i.dummyjson.com/data/products/27/3.jpg",
                "https://i.dummyjson.com/data/products/27/4.jpg",
                "https://i.dummyjson.com/data/products/27/thumbnail.webp"
        ));
        Product ArtLamp = new Product(
                "3D Embellishment Art Lamp",
                5119000
        );
        ArtLamp.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/27/1.jpg",
                "https://i.dummyjson.com/data/products/27/2.jpg",
                "https://i.dummyjson.com/data/products/27/3.jpg",
                "https://i.dummyjson.com/data/products/27/4.jpg",
                "https://i.dummyjson.com/data/products/27/thumbnail.webp"
        ));
        Product HandCraft = new Product(
                "Handcraft Chinese style",
                5119000
        );
        HandCraft.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/29/1.jpg",
                "https://i.dummyjson.com/data/products/29/2.jpg",
                "https://i.dummyjson.com/data/products/29/3.webp",
                "https://i.dummyjson.com/data/products/29/4.webp",
                "https://i.dummyjson.com/data/products/29/thumbnail.webp"
        ));
        Product KeyHolder = new Product(
                "Key Holde",
                5119000
        );
        KeyHolder.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/30/1.jpg",
                "https://i.dummyjson.com/data/products/30/2.jpg",
                "https://i.dummyjson.com/data/products/30/3.jpg",
                "https://i.dummyjson.com/data/products/30/thumbnail.jpg"
        ));
        List<Product> myProducts = List.of(KeyHolder, HandCraft, ArtLamp, WoodenBird, PlantHanger, GublabPowder, FruitNut, FoodFlavour, Macaroni, DaalMassor);
        groceryCategory.addProduct(myProducts);
        products.addAll(myProducts);
        categories.add(groceryCategory);
    }

    private void createBeauty() {
        Category beautyCategory = new Category("Làm đẹp");
        Product PerfumeOil = new Product(
                "perfume Oil",
                5119000
        );
        PerfumeOil.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/11/1.jpg",
                "https://i.dummyjson.com/data/products/11/2.jpg",
                "https://i.dummyjson.com/data/products/11/3.jpg",
                "https://i.dummyjson.com/data/products/11/thumbnail.jpg"
        ));
        Product BrownPerfume = new Product(
                "Brown Perfume",
                8119000
        );
        BrownPerfume.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/12/1.jpg",
                "https://i.dummyjson.com/data/products/12/2.jpg",
                "https://i.dummyjson.com/data/products/12/3.png",
                "https://i.dummyjson.com/data/products/12/4.jpg",
                "https://i.dummyjson.com/data/products/12/thumbnail.jpg"
        ));
        Product XpressioPerfume = new Product(
                "Fog Scent Xpressio Perfume",
                4119000
        );
        XpressioPerfume.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/13/1.jpg",
                "https://i.dummyjson.com/data/products/13/2.png",
                "https://i.dummyjson.com/data/products/13/3.jpg",
                "https://i.dummyjson.com/data/products/13/4.jpg",
                "https://i.dummyjson.com/data/products/13/thumbnail.webp"
        ));
        Product ConcentratedPerfume = new Product(
                "Non-Alcoholic Concentrated Perfume Oil",
                12119000
        );
        ConcentratedPerfume.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/14/1.jpg",
                "https://i.dummyjson.com/data/products/14/2.jpg",
                "https://i.dummyjson.com/data/products/14/3.jpg",
                "https://i.dummyjson.com/data/products/14/thumbnail.jpg"
        ));
        Product PerfumeSpray = new Product(
                "Eau De Perfume Spray",
                15119000
        );
        PerfumeSpray.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/15/1.jpg",
                "https://i.dummyjson.com/data/products/15/2.jpg",
                "https://i.dummyjson.com/data/products/15/3.jpg",
                "https://i.dummyjson.com/data/products/15/4.jpg",
                "https://i.dummyjson.com/data/products/15/thumbnail.jpg"
        ));
        Product AcidSpray = new Product(
                "Hyaluronic Acid Serum",
                15119000
        );
        AcidSpray.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/16/1.png",
                "https://i.dummyjson.com/data/products/16/2.webp",
                "https://i.dummyjson.com/data/products/16/3.jpg",
                "https://i.dummyjson.com/data/products/16/4.jpg",
                "https://i.dummyjson.com/data/products/16/thumbnail.jpg"
        ));
        Product TreeOil = new Product(
                "Tree oil 30ml",
                15119000
        );
        TreeOil.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/17/1.jpg",
                "https://i.dummyjson.com/data/products/17/2.jpg",
                "https://i.dummyjson.com/data/products/17/3.jpg",
                "https://i.dummyjson.com/data/products/17/thumbnail.jpg"
        ));
        Product OilTreeMoisture = new Product(
                "Oil Free Moisturizer 100ml",
                15119000
        );
        OilTreeMoisture.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/18/1.jpg",
                "https://i.dummyjson.com/data/products/18/2.jpg",
                "https://i.dummyjson.com/data/products/18/3.jpg",
                "https://i.dummyjson.com/data/products/18/4.jpg",
                "https://i.dummyjson.com/data/products/18/thumbnail.jpg"
        ));
        Product BeautySerum = new Product(
                "Skin Beauty Serum",
                25119000
        );
        BeautySerum.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/18/1.jpg",
                "https://i.dummyjson.com/data/products/18/2.jpg",
                "https://i.dummyjson.com/data/products/18/3.jpg",
                "https://i.dummyjson.com/data/products/18/4.jpg",
                "https://i.dummyjson.com/data/products/18/thumbnail.jpg"
        ));
        Product TreatmentCream = new Product(
                "Freckle Treatment Cream- 15gm",
                25119000
        );
        TreatmentCream.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/18/1.jpg",
                "https://i.dummyjson.com/data/products/18/2.jpg",
                "https://i.dummyjson.com/data/products/18/3.jpg",
                "https://i.dummyjson.com/data/products/18/4.jpg",
                "https://i.dummyjson.com/data/products/18/thumbnail.jpg"
        ));
        List<Product> myProducts = List.of(TreeOil, AcidSpray, PerfumeSpray, ConcentratedPerfume, XpressioPerfume, BrownPerfume, PerfumeOil, OilTreeMoisture, BeautySerum, TreatmentCream);
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

        Category womenShoesFashion = new Category("Giày - Dép nữ");
        womenShoesFashion.addAttribute(colorAttribute);
        womenShoesFashion.addAttribute(shoesSizeAttribute);

        Category womenBagFashion = new Category("Túi thời trang nữ");
        womenBagFashion.addAttribute(colorAttribute);
        womenBagFashion.addAttribute(materialAttribute);

        Category womenFashion = new Category("Thời trang nữ");

        List<Category> menSubcategories = List.of(menShoesFashion, menBagFashion, menShoesFashion);
        List<Category> womenSubcategories = List.of(womenClothFashion, womenShoesFashion, womenBagFashion);

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
        Product Iphone9 = new Product(
                "iPhone 9",
                10119000
        );
        Iphone9.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/1/1.jpg",
                "https://i.dummyjson.com/data/products/1/2.jpg",
                "https://i.dummyjson.com/data/products/1/3.jpg",
                "https://i.dummyjson.com/data/products/1/4.jpg",
                "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
        ));
        Product IphoneX = new Product(
                "iPhone X",
                14119000
        );
        IphoneX.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/2/1.jpg",
                "https://i.dummyjson.com/data/products/2/2.jpg",
                "https://i.dummyjson.com/data/products/2/3.jpg",
                "https://i.dummyjson.com/data/products/2/thumbnail.jpg"
        ));
        Product SamsungU9 = new Product(
                "Samsung Universe 9",
                15119000
        );
        SamsungU9.addThumbnail(List.of("https://i.dummyjson.com/data/products/3/1.jpg"));
        Product OppoF19 = new Product(
                "Oppo F19",
                11119000
        );
        OppoF19.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/4/1.jpg",
                "https://i.dummyjson.com/data/products/4/2.jpg",
                "https://i.dummyjson.com/data/products/4/3.jpg",
                "https://i.dummyjson.com/data/products/4/4.jpg",
                "https://i.dummyjson.com/data/products/4/thumbnail.jpg"
        ));
        Product HuaweiP30 = new Product(
                "Huawei P30",
                20900000
        );
        HuaweiP30.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/5/1.jpg",
                "https://i.dummyjson.com/data/products/5/2.jpg",
                "https://i.dummyjson.com/data/products/5/3.jpg"
        ));
        List<Product> myProducts = List.of(HuaweiP30, OppoF19, SamsungU9, IphoneX, Iphone9);
        List<Category> mySubcategories = List.of(phoneCategory, tabletCategory, mobileCategory);

        Category PhoneTablet = new Category("Điện thoại & Phụ kiện");
        PhoneTablet.addSubCategory(mySubcategories);
        phoneCategory.addProduct(myProducts);

        products.addAll(myProducts);
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
        Product InfinixINBOOK = new Product(
                "Infinix INBOOK",
                24900000
        );
        InfinixINBOOK.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/9/1.jpg",
                "https://i.dummyjson.com/data/products/9/2.png",
                "https://i.dummyjson.com/data/products/9/3.png",
                "https://i.dummyjson.com/data/products/9/4.jpg",
                "https://i.dummyjson.com/data/products/9/thumbnail.jpg"
        ));
        Product Surface4 = new Product(
                "Microsoft Surface Laptop 4",
                25900000
        );
        Surface4.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/8/1.jpg",
                "https://i.dummyjson.com/data/products/8/2.jpg",
                "https://i.dummyjson.com/data/products/8/3.jpg",
                "https://i.dummyjson.com/data/products/8/4.jpg",
                "https://i.dummyjson.com/data/products/8/thumbnail.jpg"
        ));
        Product GalaxyBook = new Product(
                "Samsung Galaxy Book",
                30900000
        );
        GalaxyBook.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/7/1.jpg",
                "https://i.dummyjson.com/data/products/7/2.jpg",
                "https://i.dummyjson.com/data/products/7/3.jpg",
                "https://i.dummyjson.com/data/products/7/thumbnail.jpg"
        ));
        Product MacBookPro = new Product(
                "MacBook Pro",
                40900000
        );
        MacBookPro.addThumbnail(List.of(
                "https://i.dummyjson.com/data/products/6/1.png",
                "https://i.dummyjson.com/data/products/6/2.jpg",
                "https://i.dummyjson.com/data/products/6/3.png",
                "https://i.dummyjson.com/data/products/6/4.jpg"
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
        List<Product> laptopProducts = List.of(InfinixINBOOK, Surface4, GalaxyBook, MacBookPro, lenovoIdea, lenovoX1, gigabyteG5, acer5);
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
