package ir.largesize.OnlineShop.controllers.api.people;

import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.JwtTokenException;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.UserVM;
import ir.largesize.OnlineShop.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ServiceResponse<UserVM> login(@RequestBody User user) {
        User userData = service.auth(user.getUserName(), user.getPassword());
        if (userData == null)
            return new ServiceResponse<UserVM>(ResponseStatus.FAILED, "Username And Password Incorrect");

        UserVM userVm = new UserVM(userData);
        String token=jwtTokenUtil.generateToken(userVm);
        userVm.setToken(token);
        return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, userVm);

    }
    @GetMapping("/getAll")
    public ServiceResponse<UserVM> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<User> result = service.getAll(pageSize,pageNumber);
            List<UserVM> resultVm=new ArrayList<>();
            result.stream().forEach(x->resultVm.add(new UserVM(x)));
            long totalCount=service.getAllCount();
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, resultVm,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

     @GetMapping("/{id}")
    public ServiceResponse<UserVM> search(@PathVariable long id) {
        try {
            User result = service.getById(id);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }
    @GetMapping("/getUserInfo")
    public ServiceResponse<UserVM> getUserInfo(HttpServletRequest request) {
        try {
            String requestTokenHeader =request.getHeader("Authorization");
            if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("request token header dose not set");

            String token=requestTokenHeader.substring(7);
            String userName=jwtTokenUtil.getUserNameFromToken(token);

            if(userName==null)
                throw new JwtTokenException("username can not resolve");

            User result = service.getByUserName(userName);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<UserVM> add(@RequestBody User data) {
        try {

            User result = service.add(data);

            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<UserVM> update(@RequestBody User data) {
        try {
            User result = service.update(data);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
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

    @PutMapping("/changePass")
    public ServiceResponse<UserVM> changePassword(@RequestBody UserVM data) {
        try {
            User result = service.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }
}