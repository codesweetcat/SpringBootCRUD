package springbootstart.service;

import springbootstart.model.Product;

public interface ProductofferService {
	
	public	Product findOne(Long id);
	public	Product update (Product iproduct);
	public	Product saveProduct(Product iproduct);
	public void removeProject(Product iproduct);
}
