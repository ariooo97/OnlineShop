package ir.largesize.OnlineShop.helper.payment.zarinpal.medels;

public class VerifyResponse {

    private String RefId;
    private long Status;

    public String getRefId() {
        return RefId;
    }

    public void setRefId(String refId) {
        RefId = refId;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(long status) {
        Status = status;
    }
}
