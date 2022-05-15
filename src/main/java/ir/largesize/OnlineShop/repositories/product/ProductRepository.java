package ir.largesize.OnlineShop.repositories.product;


import ir.largesize.OnlineShop.entities.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query("from Product where category.id= :categoryId")
    List<Product> findAllByCategory(long categoryId);

    @Query("from Product where enable=true and (title like concat('%',:search,'%') or description like concat('%',:search,'%'))")
   List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);
}
