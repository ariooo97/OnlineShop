package ir.largesize.OnlineShop.services.product;

import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.entities.product.ProductCategory;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.product.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;

    public List<ProductCategory> findAllOrderByItemOrder() {
        return repository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public ProductCategory getById(long id) {
        Optional<ProductCategory> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public List<ProductCategory> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<ProductCategory> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
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

    public boolean deleteById(long id) throws Exception {
        ProductCategory oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
      List<Product> productList=productService.findAllByCategory(id);
        if(productList.size()>0)
            throw new Exception("Please delete products in this category at first");
        repository.deleteById(id);
        return true;
    }
}


