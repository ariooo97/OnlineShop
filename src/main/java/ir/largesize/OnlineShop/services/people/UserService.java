package ir.largesize.OnlineShop.services.people;

import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository repository;

    public User auth(String userName, String password) {
        //ToDo hash password
        return repository.findAllByUserNameAndPassword(userName, password);
    }
    public User getByUserName(String userName) {
        //ToDo hash username
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
        //ToDo hash passwords
        User user = getById(id);
        if (user == null)
            throw new DataNotFoundException("User Not Found!");
        if (!user.getPassword().equals(oldPassword))
            throw new Exception("Invalid Old Password!");
        user.setPassword(newPassword);
        return repository.save(user);


    }
}


