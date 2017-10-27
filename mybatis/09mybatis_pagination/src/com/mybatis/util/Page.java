package com.mybatis.util;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 5599632798780L;

	// pagesize ，每一页显示记录数
	private int showCount = 3;
	// 总页数
	private int totalPage;
	// 总记录数
	private int totalResult;
	// 当前页
	private int currentPage;
	// 当前显示到的 ID, 在 mysql limit 中就是第一个参数.
	private int currentResult;
	// 排序字段
	private String sortField;
	// 排序方式
	private String order;

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentResult() {
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
