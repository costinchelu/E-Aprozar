package ro.ase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class InfoUser {
	
	private Long idUser;
	private String surname;
	private String name;
	private String details;
	private String email;
	private String password;
	private String phone;
	private String status; 
	private List<Order> orders;
	private boolean enabled;
	private AuthorityType authority;
	
	
	public InfoUser() {
		orders = new LinkedList<>();
	}
	
	public InfoUser(String surname, String name, String details, String email, String password, String phone, String status, List<Order> orders, boolean enabled, AuthorityType authority) {
		this.surname = surname;
		this.name = name;
		this.details = details;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.status = status;
		this.orders = orders;
		this.enabled = enabled;
		this.authority = authority;
	}
}
