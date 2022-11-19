package ir.largesize.OnlineShop.repositories.people;


import ir.largesize.OnlineShop.entities.people.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findFirstByUserNameAndPassword(String userName, String password);

    User findFirstByUserName(String userName);

    long countByEnableIsTrue();



}
