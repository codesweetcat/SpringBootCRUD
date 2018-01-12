package springboottest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import springbootstart.WorldpayTestApplication;
import springbootstart.model.Product;
import springbootstart.repository.ProductRepository;
import springbootstart.service.ProductofferServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorldpayTestApplication.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ProductServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ProductRepository proRepository;
	
	@InjectMocks
	private ProductofferServiceImpl productService;
	

	@Test
	public void verifyProjectById() throws Exception  {
		
		Product  mockProduct = new Product ("pair",2,3,4,6);
		
		Mockito.when(proRepository.findOne(Mockito.anyLong())).thenReturn(mockProduct);
		
		Product result = productService.findOne((long) 4);
		assertEquals("pair", result.getProduct_name());
    }
	
	
	@Test
	public void saveProduct(){
		Product  mockProduct = new Product ("pair",2,3,4,6);
		Mockito.when(proRepository.save(mockProduct)).thenReturn(mockProduct);
		Product result = productService.saveProduct(mockProduct);	
		assertEquals("pair", result.getProduct_name());
		assertEquals(0, Double.compare(2.0, result.getPricePerSingle()));
	}
	
	
	@Test
	public void removeProduct(){
		Product  mockProduct = new Product ("banana",2,3,4,6);
		mockProduct.setProduct_id((long) 4);
		productService.removeProject(mockProduct);
		verify(proRepository,times(1)).delete(mockProduct);
		
	}
}