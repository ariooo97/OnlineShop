package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Color;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ColorService {
    @Autowired
    private ColorRepository repository;

    public List<Color> findAll() {
      return   repository.findAll();

    }
    public Color getById(long id) {
        Optional<Color> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Color add(Color data) {
        return repository.save(data);
    }

    public Color update(Color data) throws DataNotFoundException {
        Color oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }


        oldData.setValue(data.getValue());
        oldData.setName(oldData.getName());

        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Color oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


