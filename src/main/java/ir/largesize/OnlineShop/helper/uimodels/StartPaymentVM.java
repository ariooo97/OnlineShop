package ir.largesize.OnlineShop.helper.uimodels;

public class StartPaymentVM {
    private long amount;
    private String description;
    private String location;
    private String email;
    private String mobile;

    public StartPaymentVM() {
        amount = 0;
        description = "";
        location = "#";
        mobile="";
        email="";

    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
