package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Size;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class SizeService {
    @Autowired
    private SizeRepository repository;


    public Size getById(long id) {
        Optional<Size> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Size add(Size data) {
        return repository.save(data);
    }

    public Size update(Size data) throws DataNotFoundException {
        Size oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }


        oldData.setDescription(data.getDescription());
        oldData.setTitle(data.getTitle());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Size oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


