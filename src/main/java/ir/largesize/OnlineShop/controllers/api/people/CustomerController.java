package ir.largesize.OnlineShop.controllers.api.people;


import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.enums.UserRole;
import ir.largesize.OnlineShop.helper.Exceptions.JwtTokenException;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.CustomerVM;
import ir.largesize.OnlineShop.helper.uimodels.UserVM;
import ir.largesize.OnlineShop.services.people.CustomerService;
import ir.largesize.OnlineShop.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;


    @GetMapping("/getAll")
    public ServiceResponse<Customer> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Customer> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }


    @GetMapping("/{id}")
    public ServiceResponse<Customer> search(@PathVariable long id) {
        try {
            Customer result = service.getById(id);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Customer> add(@RequestBody Customer data) {
        try {
            Customer result = service.add(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Customer> update(@RequestBody Customer data) {
        try {
            Customer result = service.update(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @PutMapping("/updateInfo")
    public ServiceResponse<Customer> update(@RequestBody CustomerVM data, HttpServletRequest request) {
        try {
            UserVM userVM = getUserVMFromTken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = service.getByUserId(userVM.getId());
                if (customer.getId() != data.getId())
                    throw new Exception("You can only update your information");
            }
            Customer result = service.update(data.getCustomerInfo());
            data.setId(userVM.getId());
            userService.update(data.getUserInfo());
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
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

    private UserVM getUserVMFromTken(HttpServletRequest request) throws JwtTokenException {
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
