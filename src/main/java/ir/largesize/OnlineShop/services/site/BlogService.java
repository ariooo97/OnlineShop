package ir.largesize.OnlineShop.services.site;

import ir.largesize.OnlineShop.entities.site.Blog;
import ir.largesize.OnlineShop.enums.BlogStatus;
import ir.largesize.OnlineShop.helper.Exceptions.DataNotFoundException;
import ir.largesize.OnlineShop.repositories.site.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repository;
    public List<Blog> search(String keyword){
        return repository.findAllByTitleContainsOrDescriptionContains(keyword);
    }
    public Blog getById(long id){
        Optional<Blog> data = repository.findById(id);
       if(data.isPresent()) return data.get();
       return null;
    }
    public List<Blog> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate").descending());
        Page<Blog> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {

        return repository.count();
    }

    public List<Blog> getAllData(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate").descending());
        Page<Blog> all = repository.findAllByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED, new Date(),page);
        return all.toList();
    }

    public long getAllCountData() {

        return repository.countByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED, new Date());
    }
    public Blog add(Blog data) throws Exception {
        if(data.getTitle()==null || data.getTitle().equals(""))
            throw new Exception("Please fill title field");
        data.setVisitCount(0);
        //data.setPublishDate(new Date());
        return repository.save(data);
    }
    public Blog update(Blog data) throws DataNotFoundException {
        Blog oldData = getById(data.getId());
        if(oldData==null){
            throw new DataNotFoundException ("Data Whit Id: "+data.getId()+" Not Found");
        }
        oldData.setDescription(data.getDescription());
        oldData.setStatus(data.getStatus());
        oldData.setImage(data.getImage());
        oldData.setTitle(data.getTitle());
        oldData.setPublishDate(data.getPublishDate());
        return repository.save(oldData);
    }
    public boolean deleteById(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if(oldData==null){
            throw new DataNotFoundException ("Data Whit Id: "+id+" Not Found");
        }
        repository.deleteById(id);
        return true;
    }
    public Blog increaseVisitCount(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("Data Whit Id: " + id + " Not Found");
        }

        oldData.setVisitCount(oldData.getVisitCount()+1);
        return repository.save(oldData);
    }
}
