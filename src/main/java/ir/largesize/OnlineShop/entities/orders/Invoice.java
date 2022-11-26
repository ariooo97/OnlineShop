package ir.largesize.OnlineShop.entities.orders;

import ir.largesize.OnlineShop.entities.people.Customer;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private long id;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "order_items")
    private List<OrderItem> orderItems;

    private boolean invoiceStatus;
    private Date invoiceDate;
    private Date payedDate;
    private String invoiceDateStr;
    private String payedDateStr;



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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public boolean isPayed() {
        return getPayedDate() != null;
    }

    public boolean isInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(boolean invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceDateStr() {
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(invoiceDate);
    }

    public String getPayedDateStr() {
        if(!isPayed())
            return "";
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(payedDate);
    }

}
