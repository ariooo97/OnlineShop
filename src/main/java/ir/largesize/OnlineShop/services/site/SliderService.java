package ir.largesize.OnlineShop.services.site;

import ir.largesize.OnlineShop.entities.site.Slider;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.site.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Slider add(Slider data){
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
}
