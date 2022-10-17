package ir.largesize.OnlineShop.helper.uimodels;

import ir.largesize.OnlineShop.entities.orders.OrderItem;

public class OrderItemVM {

    private long id;
    private long productId;
    private long customerId;
    private long colorId;
    private long sizeId;
    private long count;
    private long price;

    public OrderItemVM() {
    }

    public OrderItemVM(OrderItem item) {
        setId(item.getId());
        setProductId(item.getProduct().getId());
        setCustomerId(item.getCustomer().getId());
        setColorId(item.getColor().getId());
        setSizeId(item.getColor().getId());
        setCount(item.getSize().getId());
        setPrice(item.getPrice());
        setCount(item.getCount());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
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
