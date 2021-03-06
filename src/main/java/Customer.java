import java.util.*;

class Customer {
	
	private String _name;
	private Vector _rentals = new Vector();
	
	public Customer (String name){ 
		_name = name;
	};
	
	public void addRental (Rental arg) { 
		_rentals.addElement(arg);
	}

	public String getName () {
		return _name; 
	}

	public String statement () { 
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n"; 
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			//show figures for this rental
			result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(each.getCharge()) + "\n";
		}
		//add footer lines result += "Amount
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	public String htmlStatement() {
		Enumeration rentals = _rentals.elements();
		String result = "<h1>Rental Record for " + getName() + "</h1>";
    result += "<table>";
  	while (rentals.hasMoreElements()) {
  		Rental each = (Rental) rentals.nextElement();
     //show figures for this rental
     	result += "<tr><td>" + each.getMovie().getTitle() + "</td><td>" + String.valueOf(each.getCharge()) + "</td></tr>";
   	}
   	//add footer lines result += "Amount
   	result += "<tr><td colspan='2'>Amount owed is " + String.valueOf(getTotalCharge()) + "</td></tr>";
   	result += "<tr><td colspan='2'>You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points</td></tr>";
   	result += "</table>";
   	return result;
	}

	private double getTotalCharge() { 
		return _rentals.stream().mapToDouble(r -> ((Rental)r).getCharge()).sum();
	}

	private int getTotalFrequentRenterPoints() { 
		return _rentals.stream().mapToInt(r -> ((Rental)r).getFrequentRenterPoints()).sum();
	}

}
