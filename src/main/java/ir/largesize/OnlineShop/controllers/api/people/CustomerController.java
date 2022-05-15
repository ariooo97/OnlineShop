package ir.largesize.OnlineShop.controllers.api.people;


import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.people.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;


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

    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }

}
