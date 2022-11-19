package ir.largesize.OnlineShop.services.orders;

import ir.largesize.OnlineShop.entities.orders.Invoice;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.order.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public  long getAllCount(){
        return repository.count();
    }

    public  long countByPayedDateInNotNull(){
        return repository.countByPayedDateIsNotNull();
    }

    public List<Invoice> findByCustomer(long customerId){
        return repository.findAllByCustomer(customerId);
    }

    public List<Invoice> findByCustomer(long customerId,Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Invoice> all = repository.findAllByCustomer(customerId,page);
        return all.toList();
    }

    public Invoice getById(long id) {
        Optional<Invoice> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Invoice add(Invoice data) {
        return repository.save(data);
    }

    public Invoice update(Invoice data) throws DataNotFoundException {
        Invoice oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setPayedDate(data.getPayedDate());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Invoice oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


