package ir.largesize.OnlineShop.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@Controller
@RequestMapping("/")


public class HomeController{

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

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}

