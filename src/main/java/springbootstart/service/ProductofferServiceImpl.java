package springbootstart.service;

import springbootstart.model.Product;
import springbootstart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class ProductofferServiceImpl implements ProductofferService {
	
	  @Autowired
	    private ProductRepository PIR;

	  @Override
	    public Product findOne(Long id) {
		  Product productby_id = PIR.findOne(id);
	        return productby_id;
	    }
	
	  @Override
	    public Product update(Product iproduct) {
		  //find a project 
		  Product updatedProject = findOne(iproduct.getProduct_id());
	        if (updatedProject == null) {
	            return null;
	        }
	     //5 properties
	        updatedProject.setProduct_name(iproduct.getProduct_name());
	        updatedProject.setPricePerSingle(iproduct.getPricePerSingle());
	        updatedProject.setQuality(iproduct.getQuality());
	        updatedProject.setQuality(iproduct.getQuality());
	        updatedProject.setTotalPrice(iproduct.getTotalPrice());
	        
	        Product updatedProduct = PIR.save(updatedProject);
	        return updatedProduct;
	    }
	  
	  @Override
		public Product saveProduct(Product iproduct) {
			return PIR.save(iproduct);
		}
	  
	  @Override
	  public void removeProject(Product iproduct){
		  PIR.delete(iproduct); 
	  }
	  
	  

}
