package com.mobiquity.packer.model;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public List<ItemDetail> items;
	public int value;

	public Solution(List<ItemDetail> items, int value) {
		this.items = new ArrayList<>(items);
		this.value = value;
	}
	public Solution(List<ItemDetail> items) {
		this.items = new ArrayList<>(items);
		this.value = this.items.stream().map(item -> item.getValue()).reduce(0, (a, b) -> a + b);
	}
	public List<ItemDetail> getItems() {
		return items;
	}

	public void setItems(List<ItemDetail> items) {
		this.items = items;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Solution [items=" + items + ", value=" + value + "]";
	}

}