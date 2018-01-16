package springbootstart.controller;

import springbootstart.model.Product;
import springbootstart.model.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootstart.service.ProductofferService;
import springbootstart.repository.ProductRepository;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;



/*
    1 /getAllProducts
	2 /addNewProduct
	3 /updateProduct
	4 /deleteProduct
	5 /deleteAllProducts
	6 /updateProduct
	7 /updateAllProducts
*/

@EnableAutoConfiguration
@RestController
@CrossOrigin

@RequestMapping(value = "/bookingsv2")

public class ProductController {
	 @Autowired
	    private ProductRepository productRepository;
	 @Autowired
	    private ProductofferService productService;
	 //1 GET All products
	 @GetMapping( "/projects/list")
	 public  Collection<Product> getAll(){	      
	        return  this.productRepository.findAll();
	    }
	 //2 Add a new Product via Repository
	 @PostMapping("//product/add")
	 public Product createProduct( @RequestBody Product iPro) {
	     return productRepository.save(iPro);
	 }
	 //3 Update a Projuct via ProjectService
	 @PutMapping("//product/update")
	 public Product updateProduct( @RequestBody Product iPro){
		 return productService.update(iPro);
	 }
	 @GetMapping("/product/{projectId}/get")
	 public Product findOneById( @PathVariable Long projectId){
		 return productRepository.findOne(projectId);
	 }
	 
	//4 deleteProduct via Repository
	 @DeleteMapping("//product/delete")
	 public ResponseEntity<Response> deleteS(@Valid  @RequestBody Product iPro ) throws Exception{
		 
		 if(iPro == null || iPro.getProduct_id() <= 0){
			 throw new Exception("delete product doesn´t exist");
		 }
		 productRepository.delete(iPro.getProduct_id());
		 return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
	 }
	 // 5 delete All Products via Repository
	 @DeleteMapping("/products/list/delete")
	 public void deleteP( ){
		 productRepository.deleteAll();
	 }
	 
}
