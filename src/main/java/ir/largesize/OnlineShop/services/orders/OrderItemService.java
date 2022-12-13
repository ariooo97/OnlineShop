package ir.largesize.OnlineShop.services.orders;

import ir.largesize.OnlineShop.entities.orders.OrderItem;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.order.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository repository;


    public OrderItem getById(long id) {
        Optional<OrderItem> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }


    public OrderItem add(OrderItem data) {
        return repository.save(data);
    }

    public OrderItem update(OrderItem data) throws DataNotFoundException {
        OrderItem oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setCount(data.getCount());
        oldData.setPrice(data.getPrice());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        OrderItem oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}


