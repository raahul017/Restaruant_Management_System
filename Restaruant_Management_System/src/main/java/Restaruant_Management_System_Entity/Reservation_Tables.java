package Restaruant_Management_System_Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation_tables")
public class Reservation_Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "approved")
    private boolean approved;

    public Reservation_Tables() {
        // Default constructor required by JPA
    }

    public Reservation_Tables(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Reservation_Tables [tableId=" + tableId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", approved=" + approved + "]";
    }

	public void setSeats(int seats) {
		// TODO Auto-generated method stub
		
	}

	public void setDate(String date) {
		// TODO Auto-generated method stub
		
	}

	public void setTime(String time) {
		// TODO Auto-generated method stub
		
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
