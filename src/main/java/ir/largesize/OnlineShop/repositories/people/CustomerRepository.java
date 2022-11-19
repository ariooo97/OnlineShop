package ir.largesize.OnlineShop.repositories.people;

import ir.largesize.OnlineShop.entities.people.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    @Query("from Customer where user.id=:userId")
    Customer findByUserId(long userId);

}
