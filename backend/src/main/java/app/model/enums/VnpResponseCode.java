package app.model.enums;

public enum VnpResponseCode {
    SUCCESS("00", "Giao dịch thành công"),
    SUSPICIOUS("07", "Trừ tiền thành công. Giao dịch bị nghi ngờ"),
    NOT_REGISTERED("09", "Thẻ/Tài khoản chưa đăng ký InternetBanking"),
    AUTH_FAILED("10", "Xác thực không đúng quá 3 lần"),
    TIMEOUT("11", "Hết hạn chờ thanh toán"),
    ACCOUNT_LOCKED("12", "Thẻ/Tài khoản bị khóa"),
    OTP_FAILED("13", "Sai mật khẩu xác thực giao dịch (OTP)"),
    USER_CANCEL("24", "Khách hàng hủy giao dịch"),
    INSUFFICIENT_FUNDS("51", "Không đủ số dư"),
    DAILY_LIMIT_EXCEEDED("65", "Vượt quá hạn mức giao dịch trong ngày"),
    BANK_MAINTENANCE("75", "Ngân hàng thanh toán đang bảo trì"),
    TOO_MANY_ATTEMPTS("79", "Sai mật khẩu thanh toán quá số lần quy định"),
    OTHER("99", "Các lỗi khác");

    private final String code;
    private final String description;

    VnpResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static VnpResponseCode fromCode(String code) {
        for (VnpResponseCode response : values()) {
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
