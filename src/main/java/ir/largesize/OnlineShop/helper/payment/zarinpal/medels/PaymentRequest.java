package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
    private String merchant_id;
    private Long amount;
    private String callback_url;
    private String description;
    private String email;
    private String mobile;

    public String getMerchantId() {
        return merchant_id;
    }

    public void setMerchantId(String merchantId) {
        merchant_id = merchantId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
