package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Feature;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class FeatureService {
    @Autowired
    private FeatureRepository repository;


    public Feature getById(long id) {
        Optional<Feature> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Feature add(Feature data) {
        return repository.save(data);
    }

    public Feature update(Feature data) throws DataNotFoundException {
        Feature oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }


        oldData.setValue(data.getValue());
        oldData.setKey(oldData.getKey());

        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Feature oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


