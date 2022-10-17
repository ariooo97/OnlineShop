package ir.largesize.OnlineShop.helper.uimodels;

import java.util.List;

public class PaymentVM {
    private CustomerVM customer;
    private List<OrderItemVM> orderItems;

    public CustomerVM getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVM customer) {
        this.customer = customer;
    }

    public List<OrderItemVM> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemVM> orderItems) {
        this.orderItems = orderItems;
    }
}
