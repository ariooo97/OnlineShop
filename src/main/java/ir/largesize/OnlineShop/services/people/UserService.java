package ir.largesize.OnlineShop.services.people;

import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.helper.utils.SecurityUtils;
import ir.largesize.OnlineShop.repositories.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public User auth(String userName, String password) {
        try {
            password=securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findAllByUserNameAndPassword(userName, password);

    }
    public User getByUserName(String userName) {
        try {
            userName=securityUtils.encryptSHA1(userName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findAllByUserName(userName);
    }

    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public User add(User data) {
        return repository.save(data);
    }

    public User update(User data) throws DataNotFoundException {
        User oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }


        oldData.setEmail(data.getEmail());
        oldData.setEnable(data.isEnable());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setPassword(data.getPassword());

        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        User oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }

    public User changePassword(long id, String oldPassword, String newPassword) throws Exception {
        try {
            oldPassword=securityUtils.encryptSHA1(oldPassword);
            newPassword=securityUtils.encryptSHA1(newPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = getById(id);
        if (user == null)
            throw new DataNotFoundException("User Not Found!");
        if (!user.getPassword().equals(oldPassword))
            throw new Exception("Invalid Old Password!");
        user.setPassword(newPassword);
        return repository.save(user);


    }
}


