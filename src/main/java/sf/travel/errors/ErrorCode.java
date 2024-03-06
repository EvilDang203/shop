package sf.travel.errors;
import lombok.Getter;

@Getter
public enum ErrorCode {
    EMAIL_ADDRESS_CANNOT_BE_DUPLICATED(401, "Email address cannot be duplicated"),
    USERNAME_AVAILABLE(401, "Tên đăng nhập đã tồn tại"),
    TRAVEL_NOT_FOUND(404, "Travel not found"),
    CUSTOMER_NOT_FOUND(404, "Customer not found"),
    HIGHLIGHT_NOT_FOUND(404, "Highlight not found"),
    USERINFO_NOT_FOUND(404, "New not found"),
    ORDER_NOT_FOUND(404, "Order not found"),
    PROMOTION_NOT_FOUND(404, "Promotion not found"),
    HOTEL_NOT_FOUND(404, "Hotel not found"),
    NAME_ALREADY_EXISTS(401, "tên đã tồn tại"),
    USER_NOT_FOUND(404, "người dùng không tồn tại"),
    CATEGORY_NOT_FOUND(404, "danh mục không tồn tại"),
    PRODUCT_NOT_FOUND(404, "sản phẩm không tồn tại"),
    ;
    private final int code;
    private final String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
