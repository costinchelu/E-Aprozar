package ro.ase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	
	private Long idProduct;
	private String department;
	private String name;
	private double price;
	private String currency;
	private String availability;
	private String description;
	
	@XmlElementWrapper(name = "specifications")
	@XmlElement(name = "specification")
	private List<String> specifications;
	
	public Product() {
		specifications = new LinkedList<>();
	}
	
	public Product(String department, String name, double price, String currency, String availability, String description, List<String> specifications) {
		this.department = department;
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.availability = availability;
		this.description = description;
		this.specifications = specifications;
	}
}
