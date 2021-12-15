package com.mobiquity.packer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mobiquity.packer.model.ItemDetail;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;
import com.mobiquity.packer.service.Solver;

public final class DefaultSolver implements Solver {

	public Solution solve(LineDetail configuration) {
		
		List<ItemDetail> items = configuration.getItems();
		Comparator<ItemDetail> comparator = (i1, i2) -> i1.compareTo(i2);
		//if there is one item in the line, so we need to check the capacity and add to solution
		if ( items.size() == 1 ) {
			if ( items.get(0).getWeight()<=configuration.getCapacity()) {
				List<ItemDetail> detail = new ArrayList<>();
				detail.add(items.get(0));
				return new Solution(detail, items.get(0).getValue());
			}
			return new Solution(Collections.emptyList(), 0);
		}
		
		//first sort the item depends of the weight
		// with this way we can check the values easily
		items.sort(comparator);
		
		Solution max = new Solution(Collections.emptyList(), 0);
		List<ItemDetail> tempItems = new ArrayList<>();
		double tempWeight = 0.0;
		int tempTotal = 0;
		for (int i = 0; i < items.size(); i++) {
			
			// because of the sorting, other than this item weight more than capacity,
			// so we dont need to look at their values
			if (items.get(i).getWeight() > configuration.getCapacity()  ) {
				break;
			}
			// for every item add the current item in the list
			tempItems.add(items.get(i));
			tempWeight += items.get(i).getWeight();
			tempTotal += items.get(i).getValue();
			for (int j = i + 1; j < items.size(); j++) {
				// because of the sorting, other than this item weight more than capacity,
				// so we dont need to look at their values
				if (items.get(j).getWeight() > configuration.getCapacity()  ) {
					break;
				}
				tempWeight += items.get(j).getWeight();
				if (tempWeight <= configuration.getCapacity()) {
					tempItems.add(items.get(j));
					tempTotal += items.get(j).getValue();
				} else {
					break;
				}
			}
			// if the total value of the items more than previous ,or equals 
			// but the item count lesser than previous create the new solution.
			if ((tempTotal== max.getValue() && max.getItems().size() > tempItems.size())
					|| (max.getValue() < tempTotal)) {
				max = new Solution(tempItems, tempTotal);

			}
			if ( tempItems.size()  == items.size()) {
				break;
			}
			tempTotal = 0;
			tempWeight = 0.0;
			tempItems.clear();

		}

		return max;

	}
	

}
