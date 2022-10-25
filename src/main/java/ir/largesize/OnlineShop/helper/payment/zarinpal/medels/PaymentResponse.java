package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String authority;
    private Long code;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
