package ir.largesize.OnlineShop.services.people;

import ir.largesize.OnlineShop.entities.people.User;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.helper.utils.SecurityUtils;
import ir.largesize.OnlineShop.repositories.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public User auth(String userName, String password) {
        try {
            password = securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findAllByUserNameAndPassword(userName, password);

    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
    }

    public User getByUserName(String userName) {
       /* try {
            userName=securityUtils.encryptSHA1(userName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        return repository.findAllByUserName(userName);
    }

    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public User add(User data) throws Exception {
        if(data.getUserName().equals("") ||data.getUserName()==null )
            throw new Exception("Please enter username!");
        User oldUser = getByUserName(data.getUserName());
        if(oldUser !=null)
            throw new Exception("Duplicated username, please change username!");
        //TODO : check password strenght
        if(data.getPassword().equals("") ||data.getPassword()==null )
            throw new Exception("Please enter password!");

        data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        return repository.save(data);
    }

    public User update(User data) throws DataNotFoundException, NoSuchAlgorithmException {
        User oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setEmail(data.getEmail());
        oldData.setEnable(data.isEnable());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setRole(data.getRole());
        if (data.getPassword() != null && !data.getPassword().equals("")) {
            oldData.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        }
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
            oldPassword = securityUtils.encryptSHA1(oldPassword);
            newPassword = securityUtils.encryptSHA1(newPassword);
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


