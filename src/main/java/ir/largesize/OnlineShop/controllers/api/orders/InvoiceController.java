package ir.largesize.OnlineShop.controllers.api.orders;


import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.orders.Invoice;
import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.enums.UserRole;
import ir.largesize.OnlineShop.helper.Exceptions.JwtTokenException;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.UserVM;
import ir.largesize.OnlineShop.services.orders.InvoiceService;
import ir.largesize.OnlineShop.services.people.CustomerService;
import ir.largesize.OnlineShop.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find")
    public ServiceResponse<Invoice> find(@RequestParam long cid,
                                         @RequestParam Integer pageSize,
                                         @RequestParam Integer pageNumber,
                                         HttpServletRequest request) {
        try {
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != cid)
                    throw new Exception("You can see only your invoice");
            }
            List<Invoice> result = service.findByCustomer(cid, pageSize, pageNumber);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }


    @GetMapping("/getInfo/{id}")
    public ServiceResponse<Invoice> search(@PathVariable long id, HttpServletRequest request) {

        try {
            Invoice result = service.getById(id);
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != result.getCustomer().getId())
                    throw new Exception("You can see only your invoice");
            }
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @GetMapping("/getNewOrder")
    public ServiceResponse<Invoice> newOrder(@RequestParam Integer pageSize,
                                             @RequestParam Integer pageNumber) {

        try {
            List<Invoice> result = service.findByStatus(pageSize, pageNumber);
            long totalCount = service.getCountByStatus();
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Invoice> getAll(@RequestParam Integer pageSize,
                                             @RequestParam Integer pageNumber) {

        try {
            List<Invoice> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Invoice> add(@RequestBody Invoice data) {
        try {
            Invoice result = service.add(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Invoice> update(@RequestBody Invoice data) {
        try {
            Invoice result = service.update(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PutMapping("/update/")
    public ServiceResponse<Invoice> updateStatus(@RequestBody long id) {
        try {
            Invoice result = service.getById(id);
            result.setInvoiceStatus(true);
            service.update(result);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }

    private UserVM getUserVMFromToken(HttpServletRequest request) throws JwtTokenException {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer"))
            throw new JwtTokenException("request token header dose not set");

        String token = requestTokenHeader.substring(7);
        String userName = jwtTokenUtil.getUserNameFromToken(token);

        if (userName == null)
            throw new JwtTokenException("username can not resolve");
        UserVM userVm = new UserVM(userService.getByUserName(userName));
        return userVm;
    }

}
