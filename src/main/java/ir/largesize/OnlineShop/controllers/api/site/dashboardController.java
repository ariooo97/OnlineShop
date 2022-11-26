package ir.largesize.OnlineShop.controllers.api.site;


import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.DashboardVM;
import ir.largesize.OnlineShop.services.orders.InvoiceService;
import ir.largesize.OnlineShop.services.people.CustomerService;
import ir.largesize.OnlineShop.services.people.UserService;
import ir.largesize.OnlineShop.services.product.ProductCategoryService;
import ir.largesize.OnlineShop.services.product.ProductService;
import ir.largesize.OnlineShop.services.site.BlogService;
import ir.largesize.OnlineShop.services.site.NavService;
import ir.largesize.OnlineShop.services.site.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class dashboardController {

    @Autowired
    private NavService navService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SliderService sliderService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;



    @GetMapping("")
    public ServiceResponse<DashboardVM> get(){
        try {
            DashboardVM result=new DashboardVM();
            result.setNavigations(navService.getAllCount());
            result.setActiveBlog(blogService.getAllCountData());
            result.setAllBlog(blogService.getAllCount());
            result.setCategories(categoryService.getAllCount());
            result.setProducts(productService.getAllCount());
            result.setExistProducts(productService.getExistCount());
            result.setEnableProducts(productService.getEnableCount());
            result.setEnableProducts(userService.getAllCount());
            result.setUsers(userService.getAllCount());
            result.setActiveUsers(userService.getEnableCount());
            result.setCustomers(customerService.getAllCount());
            result.setSliders(sliderService.getAllCount());
            result.setInvoices(invoiceService.getAllCount());
            result.setPayedInvoices(invoiceService.countByPayedDateIsNotNull());
            result.setNewOrder(invoiceService.getCountByStatus());

            return new ServiceResponse<DashboardVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<DashboardVM>(e);
        }
    }

}
