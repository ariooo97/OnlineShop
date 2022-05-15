package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.ProductCategory;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    public List<ProductCategory> findAllOrderByItemOrder() {
        return repository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public ProductCategory getById(long id) {
        Optional<ProductCategory> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public ProductCategory add(ProductCategory data) {
        return repository.save(data);
    }

    public ProductCategory update(ProductCategory data) throws DataNotFoundException {
        ProductCategory oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setEnable(data.isEnable());
        oldData.setTitle(data.getTitle());
        oldData.setImage(data.getImage());
        oldData.setDescription(data.getDescription());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        ProductCategory oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


