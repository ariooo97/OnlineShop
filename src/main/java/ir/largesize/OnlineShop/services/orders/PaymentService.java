package ir.largesize.OnlineShop.services.orders;

import ir.largesize.OnlineShop.entities.orders.Invoice;
import ir.largesize.OnlineShop.entities.orders.OrderItem;
import ir.largesize.OnlineShop.entities.orders.Transactions;
import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.enums.PaymentType;
import ir.largesize.OnlineShop.helper.payment.zarinpal.contorollers.ZarinpalService;
import ir.largesize.OnlineShop.helper.payment.zarinpal.medels.VerifyRequest;
import ir.largesize.OnlineShop.helper.uimodels.PaymentVM;
import ir.largesize.OnlineShop.helper.uimodels.StartPaymentVM;
import ir.largesize.OnlineShop.services.people.CustomerService;
import ir.largesize.OnlineShop.services.people.UserService;
import ir.largesize.OnlineShop.services.product.ColorService;
import ir.largesize.OnlineShop.services.product.ProductService;
import ir.largesize.OnlineShop.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ZarinpalService zarinpalService;

    @Autowired
    private TransactionsService transactionsService;

    public StartPaymentVM addPayment(PaymentVM data) throws Exception {
        /*
        1. insert user
        2. insert customer
        3. insert order item
        4. insert invoice
        */
        StartPaymentVM response=new StartPaymentVM();
        List<OrderItem> orderItemList = new ArrayList<>();
        User userInfo = userService.add(data.getCustomer().getUserInfo());
        Customer customer=data.getCustomer().getCustomerInfo();
        customer.setUser(userInfo);
        Customer customerInfo = customerService.add(customer);
        data.getOrderItems().forEach(x -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setColor(colorService.getById(x.getColorId()));
            orderItem.setCount(x.getCount());
            orderItem.setCustomer(customerInfo);
            Product product = productService.getById(x.getProductId());
            orderItem.setPrice(product.getPrice());
            orderItem.setProduct(product);
            orderItem.setSize(sizeService.getById(x.getSizeId()));
            orderItemService.add(orderItem);
            orderItemList.add(orderItem);
            response.setAmount(response.getAmount() + (orderItem.getPrice()  * orderItem.getCount()));

        });
        Invoice invoice = new Invoice();
        invoice.setCustomer(customerInfo);
        invoice.setInvoicDate(new Date());
        invoice.setPayedDate(null);
        invoice.setOrderItems(orderItemList);
        invoiceService.add(invoice);
        response.setDescription(data.getOrderItems().size() + " products for " + data.getCustomer().getFullName());
        response.setMobile(customerInfo.getMobile());
        response.setEmail(customerInfo.getEmail());
        response.setCustomer(customerInfo);
        response.setInvoice(invoice);
        response.setPaymentType(data.getPaymentType());
        return response;
    }

    public String goToPayment(StartPaymentVM startPaymentVM) throws Exception {
        String result = "#";
        if(startPaymentVM.getPaymentType() == PaymentType.ZarinPal){
           result = zarinpalService.goToPayment(startPaymentVM);
        }
        transactionsService.add(startPaymentVM);
        return result;
    }
    public Transactions doVerify(Transactions transactions) throws Exception {
        Transactions result = null;
        if(transactions.getPaymentType() == PaymentType.ZarinPal){
             result= zarinpalService.goToVerify(transactions);
        }
        transactionsService.update(result);
        return result;
    }


}
