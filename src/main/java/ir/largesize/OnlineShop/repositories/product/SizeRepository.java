package ir.largesize.OnlineShop.repositories.product;

import ir.largesize.OnlineShop.entities.product.Color;
import ir.largesize.OnlineShop.entities.product.Size;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends PagingAndSortingRepository<Size,Long> {
    @Override
    List<Size>  findAll();
}
