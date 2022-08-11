package ir.largesize.OnlineShop.controllers.api.people;

import ir.largesize.OnlineShop.config.JwtTokenUtil;
import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.JwtTokenException;
import ir.largesize.OnlineShop.helper.ui.ResponseStatus;
import ir.largesize.OnlineShop.helper.ui.ServiceResponse;
import ir.largesize.OnlineShop.helper.uimodels.UserVm;
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
    public ServiceResponse<UserVm> login(@RequestBody User user) {
        User userData = service.auth(user.getUserName(), user.getPassword());
        if (userData == null)
            return new ServiceResponse<UserVm>(ResponseStatus.FAILED, "Username And Password Incorrect");

        UserVm userVm = new UserVm(userData);
        String token=jwtTokenUtil.generateToken(userVm);
        userVm.setToken(token);
        return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, userVm);

    }
    @GetMapping("/getAll")
    public ServiceResponse<UserVm> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<User> result = service.getAll(pageSize,pageNumber);
            List<UserVm> resultVm=new ArrayList<>();
            result.stream().forEach(x->resultVm.add(new UserVm(x)));
            long totalCount=service.getAllCount();
            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, resultVm,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
        }
    }

     @GetMapping("/{id}")
    public ServiceResponse<UserVm> search(@PathVariable long id) {
        try {
            User result = service.getById(id);
            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, new UserVm(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
        }
    }
    @GetMapping("/getUserInfo")
    public ServiceResponse<UserVm> getUserInfo(HttpServletRequest request) {
        try {
            String requestTokenHeader =request.getHeader("Authorization");
            if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("request token header dose not set");

            String token=requestTokenHeader.substring(7);
            String userName=jwtTokenUtil.getUserNameFromToken(token);

            if(userName==null)
                throw new JwtTokenException("username can not resolve");

            User result = service.getByUserName(userName);
            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, new UserVm(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<UserVm> add(@RequestBody User data) {
        try {

            User result = service.add(data);

            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, new UserVm(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<UserVm> update(@RequestBody User data) {
        try {
            User result = service.update(data);
            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, new UserVm(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
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
    public ServiceResponse<UserVm> changePassword(@RequestBody UserVm data) {
        try {
            User result = service.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ServiceResponse<UserVm>(ResponseStatus.SUCCESS, new UserVm(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVm>(e);
        }
    }
}