package springboottest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import springbootstart.controller.ProductController;
import springbootstart.model.Product;
import springbootstart.repository.ProductRepository;
import springbootstart.service.ProductofferService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTestV2 {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository proRepository;
	@MockBean
	private ProductofferService productServiceI;
	@MockBean
	private ProductController productController;
	
	@Test 
	public void verifyAllProducts() throws Exception {
		
		Product  mockProduct = new Product ("banana",2,3,4,6);
		mockProduct.setProduct_id((long) 7);
		
		Product  mockProduct2 = new Product ("pair",3,3,4,9);
		mockProduct.setProduct_id((long) 5);
		List<Product> allProducts = Collections.singletonList(mockProduct);
		//allProducts.add(mockProduct2);
		Mockito.when(productController.getAll()).thenReturn(allProducts);
		
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/bookingsv2/projects/list").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1))).andDo(print());
				 
	}
	@Test
	public void verifyProjectById() throws Exception  {
		
		Product  mockProduct = new Product ("pair",2,3,4,6);
		
		Mockito.when(productController.findOneById(Mockito.anyLong())).thenReturn(mockProduct);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/bookingsv2/product/4/get")
				.accept(MediaType.APPLICATION_JSON))
		        .andReturn();
		System.out.println("see response"+result.getResponse().getContentAsString());
		String expected = "{ product_name: pair, pricePerSingle: 2,quantity: 3, quality: 4, totalPrice: 6 }";
		 JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
    }
}
