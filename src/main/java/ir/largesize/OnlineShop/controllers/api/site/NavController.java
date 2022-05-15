package ir.largesize.OnlineShop.controllers.api.site;


import ir.largesize.OnlineShop.entities.site.Nav;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {
    @Autowired
    private NavService service;

    @GetMapping("")
    public ServiceResponse<Nav> get() {
        try {
            List<Nav> result = service.findAllOrderByItemOrder();
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Nav> search(@PathVariable long id) {
        try {
            Nav result = service.getById(id);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Nav> add(@RequestBody Nav data) {
        try {
            Nav result = service.add(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Nav> update(@RequestBody Nav data) {
        try {
            Nav result = service.update(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
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
