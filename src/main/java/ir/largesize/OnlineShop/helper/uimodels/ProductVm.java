package ir.largesize.OnlineShop.helper.uimodels;

import ir.largesize.OnlineShop.entities.product.Product;


import java.util.ArrayList;
import java.util.List;

public class ProductVm {
    private long id;
    private String title;
    private String image;
    private String description;
    private long price;
    private boolean enable;
    private boolean exists;
    private long categoryId;
    private List<Long> colors;
    private List<Long> sizes;
    private List<Long> features;

    public ProductVm() {
    }

    public List<Long> getColors() {
        if(colors==null)
            colors=new ArrayList<>();
        return colors;
    }

    public void setColors(List<Long> colors) {

        this.colors = colors;
    }

    public List<Long> getSizes() {
        if(sizes==null)
            sizes=new ArrayList<>();
        return sizes;
    }

    public void setSizes(List<Long> sizes) {
        this.sizes = sizes;
    }

    public List<Long> getFeatures() {
        if(features==null)
            features=new ArrayList<>();
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Product convert() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(getPrice());
        product.setImage(getImage());
        product.setDescription(getDescription());
        product.setExists(isExists());
        product.setEnable(isEnable());
        return product;
    }
}
