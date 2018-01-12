package springbootstart.model;

import java.io.Serializable;

import javax.persistence.*;


/*
 * Product model: 
 * product_id;
 * product_name;
 * pricePerSingle;
 * quantity;
 * quality;
 * totalPrice
 * */


@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="product_id")
	   private  Long product_id;

	 @Column(name="product_name")
	    private String product_name;

	    
	 @Column(name="pricePerSingle")
	    private  double pricePerSingle;
	    
	 @Column(name="quantity")
	    private int quantity;
	    
	 @Column(name = "quality")
	 private int quality;
	 
	 @Column(name="totalPrice")
	    private  double totalPrice;
	 
	 public Product() {};

	 public Product( String product_name, double pricePerSingle, int quantity, int quality,
			double totalPrice) {
		super();
		
		this.product_name = product_name;
		this.pricePerSingle = pricePerSingle;
		this.quantity = quantity;
		this.quality = quality;
		this.totalPrice = totalPrice;
	};

	public Long getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public double getPricePerSingle() {
		return pricePerSingle;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getQuality() {
		return quality;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setPricePerSingle(double pricePerSingle) {
		this.pricePerSingle = pricePerSingle;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	 
}
