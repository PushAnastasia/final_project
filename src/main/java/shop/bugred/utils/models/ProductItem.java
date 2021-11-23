package shop.bugred.utils.models;

import java.util.Objects;

public class ProductItem {

    private int id;
    private String name;
    private String section;
    private String description;
    private String color;
    private String size;
    private float price;
    private String params;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public ProductItem() {
    }

    public ProductItem(String name, String section, String description, String color, String size, float price, String params) {
        this.name = name;
        this.section = section;
        this.description = description;
        this.color = color;
        this.size = size;
        this.price = price;
        this.params = params;
    }

    public ProductItem(String name, String section, String description) {
        this.name = name;
        this.section = section;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return id == that.id &&
                Float.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(section, that.section) &&
                Objects.equals(description, that.description) &&
                Objects.equals(color, that.color) &&
                Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, section, description, color, size, price, params);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", params='" + params + '\'' +
                '}';
    }
}
