package ir.largesize.OnlineShop.controllers.web;

import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.enums.UserRole;
import ir.largesize.OnlineShop.helper.uimodels.UserVM;
import ir.largesize.OnlineShop.services.people.CustomerService;
import ir.largesize.OnlineShop.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;


@Controller
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String index(HttpServletRequest request, Model model) {
        Optional<Cookie> userTokenCookieOptional = Arrays.stream(request.getCookies()).filter(x -> x.getName().toLowerCase().equals("userToken".toLowerCase())).findFirst();
        if (!userTokenCookieOptional.isPresent())
            return "login";
        Cookie userTokenCookie = userTokenCookieOptional.get();
        if (userTokenCookie == null)
            return "login";
        String userCookie = userTokenCookie.getValue();
        String username = jwtTokenUtil.getUserNameFromToken(userCookie);
        UserVM user =new UserVM(userService.getByUserName(username));
        if(user.getRole() != UserRole.ADMIN) {
            Customer customer = customerService.getByUserId(user.getId());
            user.setCustomerId(customer.getId());
        }
        model.addAttribute("user", user);
        return "panel";
    }
}
