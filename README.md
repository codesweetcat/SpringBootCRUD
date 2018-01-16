## Products CRUD DB project

## 1 Create productcontroller can CRUD MqSQL. MqSQL table structure as following: 
      product_id, product_name, pricePerSingle, quantity, quality, totalPrice
      
      ##
      4 banana	2	3	8	6
      5 orange	2	5	4	10
      6 banana	2	3	4	6
      7 orange	2	5	4	10
					
## 2 Testing includes server layer testing and controller layer testing : ProductServiceTest.java && ProductControllerTest.java


## Dependency 

1 Maven
2 DB: mysql connector
3 Data: jpa
4 Testing:JUnit, Hamcrest, MockMvc, Mockito,EclEmma


## API Reference

	//getAllProducts --/bookings/allprojects
	//addNewProduct --/bookings/addNewProduct
	//updateProduct --/bookings/updateProduct
	//deleteProduct --/bookings/removeProduct
	//deleteAllProducts --/bookings/removeAllProduct
  //findOneById --/bookings/findOneById/{projectId}
	

## Tests

 Right click on the project
 Coverage As → JUnit Test
