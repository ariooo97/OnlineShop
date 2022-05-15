package ir.largesize.OnlineShop.repositories.product;

import ir.largesize.OnlineShop.entities.product.Color;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends PagingAndSortingRepository<Color,Long> {
}
