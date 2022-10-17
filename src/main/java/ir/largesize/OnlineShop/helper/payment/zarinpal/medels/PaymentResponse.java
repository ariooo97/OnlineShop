package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String Authority;
    private long status;

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
