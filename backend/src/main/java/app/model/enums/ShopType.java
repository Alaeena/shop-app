package app.model.enums;

public enum ShopType {
    FOOD_AND_CONSUMER("100000", "Thực Phẩm - Tiêu Dùng"),
    PHONES_TABLETS("110000", "Điện thoại - Máy tính bảng"),
    HOME_APPLIANCES("120000", "Điện gia dụng"),
    COMPUTERS_OFFICE("130000", "Máy tính - Thiết bị văn phòng"),
    ELECTRONICS_AUDIO("140000", "Điện tử - Âm thanh"),
    BOOKS_MAGAZINES("150000", "Sách/Báo/Tạp chí"),
    SPORTS_OUTDOORS("160000", "Thể thao, dã ngoại"),
    HOTELS_TRAVEL("170000", "Khách sạn & Du lịch"),
    CUISINE("180000", "Ẩm thực"),
    ENTERTAINMENT_EDUCATION("190000", "Giải trí & Đào tạo"),
    FASHION("200000", "Thời trang"),
    HEALTH_BEAUTY("210000", "Sức khỏe - Làm đẹp"),
    MOM_BABY("220000", "Mẹ & Bé"),
    KITCHENWARE("230000", "Vật dụng nhà bếp"),
    VEHICLES("240000", "Xe cộ - phương tiện"),
    BILL_PAYMENT("250000", "Thanh toán hóa đơn"),
    AIR_TICKETS("250007", "Vé máy bay"),
    CARD_PURCHASE("260000", "Mua mã thẻ"),
    PHARMACY_MEDICAL("270000", "Nhà thuốc - Dịch vụ y tế");

    private final String code;
    private final String label;

    ShopType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
