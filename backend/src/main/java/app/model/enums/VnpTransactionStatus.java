package app.model.enums;

public enum VnpTransactionStatus {
    SUCCESS("00", "Giao dịch thành công"),
    PENDING("01", "Giao dịch chưa hoàn tất"),
    FAILED("02", "Giao dịch bị lỗi"),
    REVERSED("04", "Giao dịch đảo (Khách hàng đã bị trừ tiền tại Ngân hàng nhưng GD chưa thành công ở VNPAY)"),
    REFUND_PROCESSING("05", "VNPAY đang xử lý giao dịch này (GD hoàn tiền)"),
    REFUND_REQUESTED("06", "VNPAY đã gửi yêu cầu hoàn tiền sang Ngân hàng (GD hoàn tiền)"),
    SUSPECTED_FRAUD("07", "Giao dịch bị nghi ngờ gian lận"),
    REFUND_DENIED("09", "GD Hoàn trả bị từ chối");

    private final String code;
    private final String description;

    VnpTransactionStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static VnpTransactionStatus fromCode(String code) {
        for (VnpTransactionStatus response : values()) {
            if (response.code.equals(code)) return response;
        }
        throw new IllegalArgumentException("Unknown response code: " + code);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

