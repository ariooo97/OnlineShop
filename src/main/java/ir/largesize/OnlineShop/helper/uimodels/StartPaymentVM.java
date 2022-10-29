package ir.largesize.OnlineShop.helper.uimodels;

import ir.largesize.OnlineShop.entities.orders.Invoice;
import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.enums.PaymentType;

public class StartPaymentVM {
    private long amount;
    private String description;
    private String location;
    private String email;
    private String mobile;
    private String authority;
    private long status;
    private Customer customer;
    private Invoice invoice;
    private PaymentType paymentType;

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
