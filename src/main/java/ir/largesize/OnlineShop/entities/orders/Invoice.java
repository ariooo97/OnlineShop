package ir.largesize.OnlineShop.entities.orders;

import ir.largesize.OnlineShop.entities.people.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private long id;


    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name="order_items")
    private List<OrderItem> orderItems;

    private Date invoicDate;
    private Date payedDate;

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

    public Date getInvoicDate() {
        return invoicDate;
    }

    public void setInvoicDate(Date invoicDate) {
        this.invoicDate = invoicDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }
}
