package ir.largesize.OnlineShop.controllers.web;

import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.services.product.ProductService;
import ir.largesize.OnlineShop.services.site.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@Controller
@RequestMapping("/")


public class HomeController{

    @Autowired
    private BlogService blogService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/products")
    public String products(){
        return "products";
    }


    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable Long id, Model model){
        model.addAttribute("dataId",id);
        try {
            blogService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "blogInfo";
    }

    @GetMapping("/products/{id}")
    public String productsCategory(@PathVariable Long id, Model model){
        model.addAttribute("dataId",id);
                return "productsCategory";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        model.addAttribute("dataId",id);
        try {
            productService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "product";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/basket")
    public String basket(){
        return "basket";
    }

    @GetMapping("/payment")
    public String payment(){
        return "payment";
    }

}

