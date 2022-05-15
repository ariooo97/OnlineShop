package ir.largesize.OnlineShop.entities.orders;

import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.entities.product.Product;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    private long count;
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
