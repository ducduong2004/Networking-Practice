package denamngoai;

import java.io.Serializable;

public class Product implements Serializable {
public int ProductID;
public String Name;
public int count ;
public double Price;
public Product() {
	
}
public int getProductID() {
	return ProductID;
}
public void setProductID(int productID) {
	ProductID = productID;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public double getPrice() {
	return Price;
}
public void setPrice(double price) {
	Price = price;
}

public Product(int productID, String name, int count, double price) {
	super();
	ProductID = productID;
	Name = name;
	this.count = count;
	Price = price;
}
@Override
public String toString() {
	return "Product [ProductID=" + ProductID + "\t Name=" + Name + "\t count=" + count + "\t Price=" + Price + "]";
}

}
