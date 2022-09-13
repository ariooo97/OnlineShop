package ir.largesize.OnlineShop.repositories.site;

import ir.largesize.OnlineShop.entities.site.Content;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content,Long> {
    Content findFirstByKey(String key);

    @Override
    List<Content> findAll();
}
