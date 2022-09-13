package ir.largesize.OnlineShop.repositories.product;


import ir.largesize.OnlineShop.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query("from Product where category.id= :categoryId")
    List<Product> findAllByCategory(long categoryId);

    @Query("select count(id) from Product where category.id= :categoryId")
    long countByCategoryId(long categoryId);

    @Query(value = "from Product where category.id= :categoryId",
    countQuery = "select count(id) from Product where category.id= :categoryId")

    Page<Product> findAllByCategory(long categoryId, Pageable pageable);

    @Query("from Product where enable=true and (title like concat('%',:search,'%') or description like concat('%',:search,'%'))")
   List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);


    List<Product> findTop3ByOrderByAddDateDesc();

    List<Product> findTop3ByOrderByVisitCountDesc();
}
