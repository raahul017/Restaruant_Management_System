package Restaruant_Management_System_Entity;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_item_id", nullable = false)
    private Food_Items foodItem;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Food_Items getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(Food_Items foodItemId) {
		this.foodItem = foodItemId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", user=" + user + ", foodItem=" + foodItem + ", orderDate=" + orderDate
				+ "]";
	}

	public Orders(long orderId, User user, Food_Items foodItem, Date orderDate) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.foodItem = foodItem;
		this.orderDate = orderDate;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
