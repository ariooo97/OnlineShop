package ir.largesize.OnlineShop.controllers.api.product;



import ir.largesize.OnlineShop.entities.product.Size;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {
    @Autowired
    private SizeService service;

    @GetMapping("")
    public ServiceResponse<Size> search(@PathVariable long id) {
        try {
            Size result =service.getById(id);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceResponse<Size> find(@PathVariable long id) {
        try {
            Size result = service.getById(id);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }
    @GetMapping("/")
    public ServiceResponse<Size> getAll() {
        try {
            List<Size> result = service.findAll();
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }
    @GetMapping("/getAll")
    public ServiceResponse<Size> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Size> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Size> add(@RequestBody Size data) {
        try {
            Size result = service.add(data);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Size> update(@RequestBody Size data) {
        try {
            Size result = service.update(data);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
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
