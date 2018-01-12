package springboottest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import springbootstart.WorldpayTestApplication;
import springbootstart.model.Product;
import springbootstart.repository.ProductRepository;
import springbootstart.service.ProductofferServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorldpayTestApplication.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProductofferServiceImpl productService;
	
	
	@Mock
	private ProductRepository proRepository;
	
	
	
	@Test
	public void verifyAllProducts() throws Exception {
    List<Product>  mockProductList = new ArrayList<Product> ();
    mockProductList.add(new Product("apple",2,3,4,6));
    mockProductList.add(new Product("banana",2,4,4,8));	
    mockProductList.add(new Product("apple",2,3,4,6));
    mockProductList.add(new Product("banana",2,4,4,8));	
    mockProductList.add(new Product("apple",2,3,4,6));
    mockProductList.add(new Product("banana",2,4,4,8));	
		Mockito.when(proRepository.findAll()).thenReturn(mockProductList);
		mockMvc.perform(MockMvcRequestBuilders.get("/bookings/allprojects").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyProjectById() throws Exception  {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookings/findOneById/4").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("see response"+result);
		
	/*
	    "product_id": 2,
	    "product_name": "pair",
	    "pricePerSingle": 2,
	    "quantity": 3,
	    "quality": 4,
	    "totalPrice": 6
	*/
		String expected = "{ product_name: banana, pricePerSingle: 2,quantity: 3, quality: 8, totalPrice: 6 }";
	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
	
	@Test
	public void verifyUpdateProduct() throws Exception {
	Product  mockProduct = new Product ("banana",2,3,8,6);
	mockProduct.setProduct_id((long)4);
	
	ObjectMapper mapper = new ObjectMapper();
	String jsonString = mapper.writeValueAsString(mockProduct);
	Mockito.when(productService.update(mockProduct)).thenReturn(mockProduct);
		
	
		mockMvc.perform(MockMvcRequestBuilders.put("/bookings/updateProduct")
			
			.content(jsonString)
			.contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.product_name").exists())
					.andExpect(MockMvcResultMatchers.status().is(200));
					
		
	}


}