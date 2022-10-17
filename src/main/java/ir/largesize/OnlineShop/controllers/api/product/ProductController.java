package ir.largesize.OnlineShop.controllers.api.product;


import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.ProductVM;
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



    @GetMapping("/info/{id}")
    public ServiceResponse<ProductVM> getById(@PathVariable long id) {
        try {
            Product result = productService.getById(id);
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS,new ProductVM(result));
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceResponse<Product> findById(@PathVariable long id) {
        try {
            Product result = productService.getById(id);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS,result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
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
    public ServiceResponse<ProductVM> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @PathVariable long cid) {
        try {
            List<ProductVM> result = productService.getAllByCategoryId(cid, pageSize, pageNumber);
            long totalCount = productService.getAllCountByCategoryId(cid);
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
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
    public ServiceResponse<ProductVM> newProducts() {
        try {
            List<ProductVM> result = productService.findTop3ByOrderByAddDateDesc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @GetMapping("/popularProducts")
    public ServiceResponse<ProductVM> popularProducts() {
        try {
            List<ProductVM> result = productService.findTop3ByOrderByVisitCountDesc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @GetMapping("/cheapestProducts")
    public ServiceResponse<ProductVM> cheapestProducts() {
        try {
            List<ProductVM> result = productService.findTop6ByOrderByPriceAsc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @GetMapping("/expensiveProducts")
    public ServiceResponse<ProductVM> expensiveProducts() {
        try {
            List<ProductVM> result = productService.findTop6ByOrderByPriceDesc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Product> add(@RequestBody ProductVM data) {
        try {
            Product result = productService.add(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @PutMapping("/edit")
    public ServiceResponse<Product> update(@RequestBody ProductVM data) {
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
