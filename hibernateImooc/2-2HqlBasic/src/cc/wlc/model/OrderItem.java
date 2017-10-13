package cc.wlc.model;

import java.io.Serializable;

public class OrderItem implements Serializable {

	private Long id;// 主键
	private Order order;// 订单
	private Commodity commodity;// 订单商品
	private Double discount;// 折扣
	private Double actPrice;// 价格
	private Double amount;// 数量
	private Integer position;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", commodity=" + commodity + ", discount=" + discount
				+ ", actPrice=" + actPrice + ", amount=" + amount + ", position=" + position + "]";
	}

	public OrderItem(Long id, Order order, Commodity commodity, Double discount, Double actPrice, Double amount,
			Integer position) {
		super();
		this.id = id;
		this.order = order;
		this.commodity = commodity;
		this.discount = discount;
		this.actPrice = actPrice;
		this.amount = amount;
		this.position = position;
	}

	public OrderItem() {
		super();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getActPrice() {
		return actPrice;
	}

	public void setActPrice(Double actPrice) {
		this.actPrice = actPrice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
