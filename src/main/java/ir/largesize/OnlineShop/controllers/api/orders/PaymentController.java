package ir.largesize.OnlineShop.controllers.api.orders;



import ir.largesize.OnlineShop.helper.payment.zarinpal.contorollers.ZarinpalService;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.PaymentVM;
import ir.largesize.OnlineShop.helper.uimodels.StartPaymentVM;
import ir.largesize.OnlineShop.services.orders.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @Autowired
    private ZarinpalService zarinpalService;

    @PostMapping("/")
    public ServiceResponse<StartPaymentVM> addPayment(@RequestBody PaymentVM data) {
        try {
            StartPaymentVM startPaymentVM = service.addPayment(data);
            String location=zarinpalService.goToPayment(startPaymentVM);
            startPaymentVM.setLocation(location);
            return new ServiceResponse<StartPaymentVM>(ResponseStatus.SUCCESS, startPaymentVM);
        } catch (Exception e) {
            return new ServiceResponse<StartPaymentVM>(e);
        }
    }

}


