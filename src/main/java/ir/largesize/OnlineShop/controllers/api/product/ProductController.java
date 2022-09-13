package ir.largesize.OnlineShop.controllers.api.product;


import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.ProductVm;
import ir.largesize.OnlineShop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("")
    public ServiceResponse<Product> search(@RequestParam String keyword) {
        try {
            List<Product> result = productService.search(keyword);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }


    @GetMapping("/{id}")
    public ServiceResponse<ProductVm> findId(@PathVariable long id) {
        try {
            Product result = productService.getById(id);
            return new ServiceResponse<ProductVm>(ResponseStatus.SUCCESS,new ProductVm(result));
        } catch (Exception e) {
            return new ServiceResponse<ProductVm>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Product> getAll(
            @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Product> result = productService.getAll(pageSize, pageNumber);
            long totalCount = productService.getAllCount();
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/getAll/{cid}")
    public ServiceResponse<Product> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @PathVariable long cid) {
        try {
            List<Product> result = productService.getAllByCategoryId(cid, pageSize, pageNumber);
            long totalCount = productService.getAllCountByCategoryId(cid);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/find")
    public ServiceResponse<Product> find(@PathVariable long id) {
        try {
            List<Product> result = productService.findAllByCategory(id);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }
    @GetMapping("/newProducts")
    public ServiceResponse<ProductVm> newProducts() {
        try {
            List<ProductVm> result = productService.findTop3ByOrderByAddDateDesc();
            return new ServiceResponse<ProductVm>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVm>(e);
        }
    }

    @GetMapping("/popularProducts")
    public ServiceResponse<ProductVm> popularProducts() {
        try {
            List<ProductVm> result = productService.findTop3ByOrderByVisitCountDesc();
            return new ServiceResponse<ProductVm>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVm>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Product> add(@RequestBody ProductVm data) {
        try {
            Product result = productService.add(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @PutMapping("/edit")
    public ServiceResponse<Product> update(@RequestBody Product data) {
        try {
            Product result = productService.update(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = productService.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }

}
