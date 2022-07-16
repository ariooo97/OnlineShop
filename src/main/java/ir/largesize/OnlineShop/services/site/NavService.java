package ir.largesize.OnlineShop.services.site;

import ir.largesize.OnlineShop.entities.site.Nav;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.site.NavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavService {
    @Autowired
    private NavRepository repository;

    public List<Nav> findAllOrderByItemOrder() {
        return repository.findAllByEnableIsTrue(Sort.by("itemOrder"));
    }

    public List<Nav> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page= PageRequest.of(pageNumber,pageSize,Sort.by("itemOrder"));
        Page<Nav> all = repository.findAll(page);
        return all.toList();
    }

    public Nav getById(long id) {
        Optional<Nav> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Nav add(Nav data) {
        return repository.save(data);
    }

    public Nav update(Nav data) throws DataNotFoundException {
        Nav oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setEnable(data.isEnable());
        oldData.setId(data.getId());
        oldData.setTitle(data.getTitle());
        oldData.setLink(data.getLink());
        oldData.setItemOrder(data.getItemOrder());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Nav oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}
