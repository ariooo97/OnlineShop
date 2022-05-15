package ir.largesize.OnlineShop.services.site;

import ir.largesize.OnlineShop.entities.site.Content;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.site.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {
    @Autowired
    private ContentRepository repository;

    public Content findByKey(String key){
        return repository.findFirstByKey(key);
    }

    public Content getById(long id) {
        Optional<Content> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Content add(Content data) {
        return repository.save(data);
    }

    public Content update(Content data) throws DataNotFoundException {
        Content oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + data.getId() + " Not Found");
        }

        oldData.setValue(data.getValue());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Content oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }
        repository.deleteById(id);
        return true;
    }
}
