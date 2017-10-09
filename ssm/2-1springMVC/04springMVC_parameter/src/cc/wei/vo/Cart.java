package cc.wei.vo;

import java.util.List;

/*
 * 购物车
 */
public class Cart {
	private List<Goods> list;
	public List<Goods> getList() {
		return list;
	}
	public void setList(List<Goods> list) {
		this.list = list;
	}
}
