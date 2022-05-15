package ir.largesize.OnlineShop.services.people;

import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.people.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;


    public Customer getById(long id) {
        Optional<Customer> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Customer add(Customer data) {
        return repository.save(data);
    }

    public Customer update(Customer data) throws DataNotFoundException {
        Customer oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }
        oldData.setAddress(data.getAddress());
        oldData.setEmail(data.getEmail());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setMobile(data.getMobile());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());

        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Customer oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


