package De_2018_2019_Giai_De;

import java.util.List;

public interface IProductDAO {
	boolean add(Product product);
	boolean remove(String idsp);
	boolean edit(Product product);
	List<Product> view(String productName);
}
