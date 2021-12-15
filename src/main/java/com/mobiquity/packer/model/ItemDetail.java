package com.mobiquity.packer.model;

public class ItemDetail implements Comparable<ItemDetail>{
	private Integer index;
	private Double weight;
	private Integer value;
	
	public ItemDetail(Integer index, Double weight, Integer value) {
		this.index = index;
		this.weight = weight;
		this.value = value;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [index=" + index + ", value=" + value + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(ItemDetail o) {
		// TODO Auto-generated method stub
		return this.getWeight().compareTo(o.getWeight());
	}

}
