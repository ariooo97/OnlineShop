package ir.largesize.OnlineShop.repositories.site;

import ir.largesize.OnlineShop.entities.site.Content;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content,Long> {
    Content findFirstByKey(String key);
}
