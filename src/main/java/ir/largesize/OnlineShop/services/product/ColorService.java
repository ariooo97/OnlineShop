package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Color;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Color> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Color> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
    }

    public Color getById(long id) {
        Optional<Color> data = repository.findById(id);
        if (data.isPresent())
            return data.get();
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
        oldData.setName(data.getName());

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


