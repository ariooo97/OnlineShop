package ir.largesize.OnlineShop.repositories.site;

import ir.largesize.OnlineShop.entities.site.Slider;
import ir.largesize.OnlineShop.entities.site.Slider;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends PagingAndSortingRepository<Slider,Long> {

    List<Slider> findAllByEnableIsTrue(Sort sort);
    Slider findTopByOrderByItemOrderDesc();
    Slider findTopByItemOrder(int itemOrder);

}
