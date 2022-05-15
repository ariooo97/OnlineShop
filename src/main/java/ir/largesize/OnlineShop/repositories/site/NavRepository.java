package ir.largesize.OnlineShop.repositories.site;

import ir.largesize.OnlineShop.entities.site.Nav;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavRepository extends PagingAndSortingRepository<Nav,Long> {
    List<Nav> findAllByEnableIsTrue(Sort sort);
}
