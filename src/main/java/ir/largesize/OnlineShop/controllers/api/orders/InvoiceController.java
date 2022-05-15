package ir.largesize.OnlineShop.controllers.api.orders;


import ir.largesize.OnlineShop.entities.orders.Invoice;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.orders.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService service;

    @GetMapping("/find")
    public ServiceResponse<Invoice> find(@RequestParam long cid) {
        try {
          List<Invoice> result =service.findByCustomer(cid);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Invoice> search(@PathVariable long id) {
        try {
            Invoice result = service.getById(id);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
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
