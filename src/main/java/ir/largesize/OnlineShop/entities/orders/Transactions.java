package ir.largesize.OnlineShop.entities.orders;


import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.enums.PaymentType;
import javax.persistence.*;
import java.util.Date;


@Entity
public class Transactions {

    @Id
    @GeneratedValue
    private long id;


    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    private Date addDate;
    private long amount;
    private  String description;
    private  String authority;
    private long status;
    private long refId;
    private String verifyStatus;
    private long transactionVerify;
    private PaymentType paymentType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public long getTransactionVerify() {
        return transactionVerify;
    }

    public void setTransactionVerify(long transactionVerify) {
        this.transactionVerify = transactionVerify;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
