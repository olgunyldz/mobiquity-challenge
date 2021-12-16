package com.mobiquity.packer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mobiquity.packer.model.ItemDetail;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;
import com.mobiquity.packer.service.Solver;

public final class DefaultSolver implements Solver {

	private void findSubsets(List<List<ItemDetail>> subset, List<ItemDetail> nums, ArrayList<ItemDetail> output,
			int index, int total, int sum) {
		// Base Condition
		if (index == nums.size()) {
			subset.add(output);
			return;
		}

		// Not Including Value which is at Index
		findSubsets(subset, nums, new ArrayList<>(output), index + 1, total, sum);

		// Including Value which is at Index
		if (total + nums.get(index).getWeight() <= sum) {
			output.add(nums.get(index));
			findSubsets(subset, nums, new ArrayList<>(output), index + 1,
					total + nums.get(index).getWeight().intValue(), sum);
		}

	}

	@Override
	public Optional<Solution> solve(LineDetail lineDetail) {
		int capacity = lineDetail.getCapacity() * 100;
		final List<ItemDetail> items = lineDetail.getItems().stream()//
				.filter(item -> item.getWeight() < lineDetail.getCapacity())// filter greater values
				.map(item -> {
					item.setWeight(item.getWeight() * 100); // times 100 to make easy comparision and adding
					return item;
				}).collect(Collectors.toList());
		if ( items.isEmpty() ) {
			return Optional.empty();
		}
		List<List<ItemDetail>> subset = new ArrayList<>();
		findSubsets(subset, items, new ArrayList<>(), 0, 0, capacity); // find all the subsets values
		int max = 0;
		List<ItemDetail> temp = new ArrayList<>();
		int maxWeight = 0;
		for (int i = 0; i < subset.size(); i++) {
			int tempTotal = 0;
			int tempWeight = 0;
			for (int j = 0; j < subset.get(i).size(); j++) {
				tempTotal += subset.get(i).get(j).getValue();
				tempWeight += subset.get(i).get(j).getWeight();
			}
			if (tempTotal > max || (tempTotal == max && tempWeight < maxWeight)) {
				temp.clear();
				temp.addAll(subset.get(i));
				max = tempTotal;
				maxWeight = tempWeight;
			}
		}
		return Optional.of(new Solution(temp));
	}

}
