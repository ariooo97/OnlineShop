package ir.largesize.OnlineShop.controllers.api.product;


import ir.largesize.OnlineShop.entities.product.Feature;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.product.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    private FeatureService service;

    @GetMapping("")
    public ServiceResponse<Feature> search(@PathVariable long id) {
        try {
            Feature result =service.getById(id);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
        }
    }


    @PostMapping("/add")
    public ServiceResponse<Feature> add(@RequestBody Feature data) {
        try {
            Feature result = service.add(data);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Feature> update(@RequestBody Feature data) {
        try {
            Feature result = service.update(data);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
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
