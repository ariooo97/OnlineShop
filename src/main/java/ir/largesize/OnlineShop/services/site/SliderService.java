package ir.largesize.OnlineShop.services.site;



import ir.largesize.OnlineShop.entities.site.Nav;
import ir.largesize.OnlineShop.entities.site.Slider;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.site.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService {
    @Autowired
    private SliderRepository repository;
    public List<Slider> findAllByItemOrder(){
        return repository.findAllByEnableIsTrue(Sort.by("itemOrder"));
    }
    public Slider getById(long id){
        Optional<Slider> data = repository.findById(id);
       if(data.isPresent()) return data.get();
       return null;
    }

    public List<Slider> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("itemOrder"));
        Page<Slider> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
    }

    public Slider add(Slider data){
        Slider lastItem = repository.findTopByOrderByItemOrderDesc();
        if (lastItem != null && lastItem.getItemOrder() > 0)
            data.setItemOrder(lastItem.getItemOrder() + 1);
        else
            data.setItemOrder(1);
        return repository.save(data);
    }
    public Slider update(Slider data) throws DataNotFoundException {
        Slider oldData = getById(data.getId());
        if(oldData==null){
            throw new DataNotFoundException ("Data Whit Id: "+data.getId()+" Not Found");
        }
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());
        oldData.setId(data.getId());
        oldData.setImage(data.getImage());
        oldData.setTitle(data.getTitle());
        oldData.setLink(data.getLink());
        return repository.save(oldData);
    }
    public boolean deleteById(long id) throws DataNotFoundException {
        Slider oldData = getById(id);
        if(oldData==null){
            throw new DataNotFoundException ("Data Whit Id: "+id+" Not Found");
        }
        repository.deleteById(id);
        return true;
    }
    public Slider changeOrder(long id, int direction) throws Exception {
        Slider item = getById(id);
        if (item == null)
            throw new Exception("item not found!");
        switch (direction) {
            case 1:
                //up
                if (item.getItemOrder() <= 1)
                    return item;
                Slider siblingItem = repository.findTopByItemOrder(item.getItemOrder() - 1);
                if (siblingItem == null)
                    item.setItemOrder(item.getItemOrder() - 1);
                else {
                    item.setItemOrder(siblingItem.getItemOrder());
                    siblingItem.setItemOrder(item.getItemOrder() + 1);
                    repository.save(siblingItem);
                }
                break;
            case 0:
                //down
                Slider siblingItem2 = repository.findTopByItemOrder(item.getItemOrder() + 1);
                if (siblingItem2 == null) {
                    Slider lastOrderItem = repository.findTopByOrderByItemOrderDesc();
                    if (item.getItemOrder() < lastOrderItem.getItemOrder())
                        item.setItemOrder(item.getItemOrder() + 1);
                } else {
                    item.setItemOrder(siblingItem2.getItemOrder());
                    siblingItem2.setItemOrder(item.getItemOrder() - 1);
                    repository.save(siblingItem2);
                }
                break;
        }
        repository.save(item);
        return item;
    }
}
