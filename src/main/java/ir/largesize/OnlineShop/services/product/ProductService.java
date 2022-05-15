package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAllByCategory(long categoryId) {
        return repository.findAllByCategory(categoryId);
    }

    public List<Product> search(String keyword) {
        return repository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(keyword);
    }

    public Product getById(long id) {
        Optional<ir.largesize.OnlineShop.entities.product.Product> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Product add(ir.largesize.OnlineShop.entities.product.Product data) {
        return repository.save(data);
    }

    public Product update(ir.largesize.OnlineShop.entities.product.Product data) throws DataNotFoundException {
        ir.largesize.OnlineShop.entities.product.Product oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setEnable(data.isEnable());
        oldData.setId(data.getId());
        oldData.setTitle(data.getTitle());
        oldData.setImage(data.getImage());
        oldData.setDescription(data.getDescription());
        oldData.setPrice(data.getPrice());
        oldData.setExist(data.isExist());
        oldData.setColors(data.getColors());
        oldData.setSizes(data.getSizes());
        oldData.setFeatures(data.getFeatures());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        ir.largesize.OnlineShop.entities.product.Product oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }

    public Product increaseVisitCount(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }

        oldData.setVisitCount(oldData.getVisitCount() + 1);
        return repository.save(oldData);
    }
}


