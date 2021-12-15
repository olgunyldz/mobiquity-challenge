package com.mobiquity.packer.model;

import java.util.List;

public class LineDetail {

	private int capacity;
	private List<ItemDetail> items;
	
	public LineDetail(int capacity, List<ItemDetail> items) {
		super();
		this.capacity = capacity;
		this.items = items;
	}

	public LineDetail() {

	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(List<ItemDetail> items) {
		this.items = items;
	}	
}
