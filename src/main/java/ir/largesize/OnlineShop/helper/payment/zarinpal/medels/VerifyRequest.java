package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

public class VerifyRequest {
    private String merchant_id;
    private String authority;
    private long amount;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
