package ir.largesize.OnlineShop.repositories.people;

import ir.largesize.OnlineShop.entities.people.Customer;
import ir.largesize.OnlineShop.entities.people.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}
