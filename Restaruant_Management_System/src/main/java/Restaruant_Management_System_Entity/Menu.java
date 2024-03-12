package Restaruant_Management_System_Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Food_Items foodItem;

    public Menu() {
        // Default constructor required by JPA
    }

    public Menu(Food_Items foodItem) {
        this.foodItem = foodItem;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Food_Items getFoodItem() {
        return foodItem;
    }

    public Menu(Long menuId, Food_Items foodItem) {
		super();
		this.menuId = menuId;
		this.foodItem = foodItem;
	}

	public void setFoodItem(Food_Items foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", foodItem=" + foodItem + "]";
    }
}
