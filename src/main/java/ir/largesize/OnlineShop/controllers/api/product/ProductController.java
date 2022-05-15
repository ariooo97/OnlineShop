package ir.largesize.OnlineShop.controllers.api.product;


import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;


    @GetMapping("")
    public ServiceResponse<Product> search(@RequestParam String keyword) {
        try {
            List<Product> result = service.search(keyword);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/find")
    public ServiceResponse<Product> find(@PathVariable long id) {
        try {
            List<Product> result = service.findAllByCategory(id);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Product> add(@RequestBody Product data) {
        try {
            Product result = service.add(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Product> update(@RequestBody Product data) {
        try {
            Product result = service.update(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
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
