package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
    private String MerchantId;
    private Long Amount;
    private String CallBackURL;
    private String Description;
    private String Email;
    private String Mobile;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getCallBackURL() {
        return CallBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        CallBackURL = callBackURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
