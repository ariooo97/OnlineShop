package ir.largesize.OnlineShop.controllers.api.site;


import ir.largesize.OnlineShop.entities.site.Content;
import ir.largesize.OnlineShop.entities.site.Content;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.services.site.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    private ContentService service;

    @GetMapping("")
    public ServiceResponse<Content> find(@RequestParam String key) {
        try {
            Content result = service.findByKey(key);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }
    @GetMapping("/getAll")
    public ServiceResponse<Content> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Content> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/getAllData")
    public ServiceResponse<Content> getAllData() {
        try {
            List<Content> result = service.getAllData();
            long totalCount=service.getAllCount();
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Content> search(@PathVariable long id) {
        try {
            Content result = service.getById(id);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Content> add(@RequestBody Content data) {
        try {
            Content result = service.add(data);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Content> update(@RequestBody Content data) {
        try {
            Content result = service.update(data);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
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
