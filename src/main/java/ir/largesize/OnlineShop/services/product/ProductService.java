package ir.largesize.OnlineShop.services.product;
import ir.largesize.OnlineShop.entities.product.Product;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.helper.uimodels.ProductVm;
import ir.largesize.OnlineShop.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private ProductCategoryService productCategoryService;

    public List<Product> findAllByCategory(long categoryId) {
        return repository.findAllByCategory(categoryId);
    }


    public List<Product> search(String keyword) {
        return repository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(keyword);
    }



    public List<Product> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
    }

    public List<Product> getAllByCategoryId(long categoryId, Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAllByCategory(categoryId, page);
        return all.toList();
    }

    public long getAllCountByCategoryId(long categoryId) {

        return repository.countByCategoryId(categoryId);
    }

    public Product getById(long id) {
        Optional<Product> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<ProductVm> findTop3ByOrderByAddDateDesc(){
    List<ProductVm> vmList=new ArrayList<>();
        List<Product> top6 = repository.findTop3ByOrderByAddDateDesc();
        top6.forEach(x-> vmList.add(new ProductVm(x)));
    return vmList;
    }

    public List<ProductVm> findTop3ByOrderByVisitCountDesc(){
        List<ProductVm> vmList=new ArrayList<>();
        List<Product> top6 = repository.findTop3ByOrderByVisitCountDesc();
        top6.forEach(x-> vmList.add(new ProductVm(x)));
        return vmList;
    }

    public Product add(ProductVm vm) {
        Product data = vm.convert();
        if (vm.getFeatures() != null)
            vm.getFeatures().forEach(x -> data.addFeature(featureService.getById(x)));
        if (vm.getColors() != null)
            vm.getColors().forEach(x -> data.addColor(colorService.getById(x)));
        if (vm.getSizes() != null)
            vm.getSizes().forEach(x -> data.addSize(sizeService.getById(x)));
        data.setCategory(productCategoryService.getById(vm.getCategoryId()));
        data.setAddDate(new Date());
        return repository.save(data);
    }


    public Product update(Product data) throws DataNotFoundException {
        Product oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setEnable(data.isEnable());
        oldData.setId(data.getId());
        oldData.setTitle(data.getTitle());
        oldData.setImage(data.getImage());
        oldData.setDescription(data.getDescription());
        oldData.setPrice(data.getPrice());
        oldData.setExists(data.isExists());
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
        oldData.getFeatures().forEach(x -> {
            try {
                featureService.deleteById(x.getId());
            } catch (DataNotFoundException e) {
                e.printStackTrace();
            }
        });
        List<Long> deletingFeatures=new ArrayList<>();
        repository.deleteById(id);

        oldData.getFeatures().forEach(x ->deletingFeatures.add(x.getId()));
        deletingFeatures.forEach(x -> {
            try {
                featureService.deleteById(x);
            } catch (DataNotFoundException e) {
                e.printStackTrace();
            }
        });
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


