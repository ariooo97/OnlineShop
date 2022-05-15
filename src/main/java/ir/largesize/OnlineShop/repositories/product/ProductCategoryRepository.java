package ir.largesize.OnlineShop.repositories.product;


import ir.largesize.OnlineShop.entities.product.ProductCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory,Long> {
  List<ProductCategory> findAllByEnableIsTrue(Sort sort);
}
