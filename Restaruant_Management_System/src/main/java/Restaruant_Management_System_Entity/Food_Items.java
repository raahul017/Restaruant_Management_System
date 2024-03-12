package Restaruant_Management_System_Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food_items")
public class Food_Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", length = 1000) // Example length constraint
    private String description;

    public Food_Items() {
        // Default constructor required by JPA
    }

    public Food_Items(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food_Items [itemId=" + itemId + ", name=" + name + ", price=" + price + ", description=" + description
                + "]";
    }
}
