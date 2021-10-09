package ro.ase.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * lista produse in cart
 */
public class ChoiceCartList {
	
	private List<Timestamp> orderedDate;
	
	public ChoiceCartList(){
		orderedDate = new LinkedList<>();
	}
	
	public void setOrderedDate(List<Timestamp> orderedDate) {
		this.orderedDate = orderedDate;
	}
	
	public List<Timestamp> getOrderedDate() {
		return orderedDate;
	}
}
