package ir.largesize.OnlineShop.repositories.product;

import ir.largesize.OnlineShop.entities.product.Color;
import ir.largesize.OnlineShop.entities.product.Size;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends PagingAndSortingRepository<Size,Long> {
}
