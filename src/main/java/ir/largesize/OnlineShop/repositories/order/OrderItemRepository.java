package ir.largesize.OnlineShop.repositories.order;

import ir.largesize.OnlineShop.entities.orders.OrderItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Long> {
}
