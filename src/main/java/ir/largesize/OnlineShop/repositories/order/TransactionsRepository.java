package ir.largesize.OnlineShop.repositories.order;

import ir.largesize.OnlineShop.entities.orders.OrderItem;
import ir.largesize.OnlineShop.entities.orders.Transactions;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends PagingAndSortingRepository<Transactions,Long> {
    Transactions findByAuthority(String authority);
}
