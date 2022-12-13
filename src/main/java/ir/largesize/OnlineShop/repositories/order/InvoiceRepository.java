package ir.largesize.OnlineShop.repositories.order;

import ir.largesize.OnlineShop.entities.orders.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice,Long> {
    @Query("from Invoice where customer.id= :customerId")
    List<Invoice> findAllByCustomer(long customerId);

    @Query(value = "from Invoice where customer.id= :customerId",
            countQuery = "select count(id) from Invoice where customer.id= :customerId")
    Page<Invoice> findAllByCustomer(long customerId, Pageable pageable);

    Page<Invoice> findAllByInvoiceStatusIsFalseAndPayedStatusIsTrue(Pageable pageable);

    Page<Invoice> getAllByIdIsNotNull(Pageable pageable);

    long countByPayedDateIsNotNull();

    long countByInvoiceStatusIsFalseAndPayedStatusIsTrue();





}
