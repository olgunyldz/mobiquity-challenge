package com.mobiquity.packer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.mobiquity.packer.model.ItemDetail;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;

public class DefaultSolverTest {

	@Test
	public void empty_list_should_return_empty() {
		DefaultSolver defaultSolver = new DefaultSolver();
		List<ItemDetail> items = new ArrayList<>();
		LineDetail configuration = new LineDetail(81, items);
		Optional<Solution> result = defaultSolver.solve(configuration);
		assertTrue(result.isEmpty());
	}

	@Test
	public void _should_return_if_one_item_exists() {
		DefaultSolver defaultSolver = new DefaultSolver();
		List<ItemDetail> items = new ArrayList<>();
		ItemDetail itemdetail = new ItemDetail(1, 10.0, 5);
		items.add(itemdetail);
		LineDetail configuration = new LineDetail(81, items);
		Solution result = defaultSolver.solve(configuration).get();
		assertEquals(5, result.getValue());
		assertEquals(1, result.getItems().size());
	}

	@Test
	public void _should_return_if_one_item_exists_but_greater_than_capacity() {
		DefaultSolver defaultSolver = new DefaultSolver();
		List<ItemDetail> items = new ArrayList<>();
		ItemDetail itemdetail = new ItemDetail(1, 10.0, 5);
		items.add(itemdetail);
		LineDetail configuration = new LineDetail(5, items);
		Optional<Solution> result = defaultSolver.solve(configuration);
		assertTrue(result.isEmpty());
	}

	@Test
	public void should_return_if_multiple_item_exists_three_of_them_shouldreturn() {
		DefaultSolver defaultSolver = new DefaultSolver();
		List<ItemDetail> items = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			ItemDetail itemdetail = new ItemDetail(i, 2.0 + i, 5 + i);
			items.add(itemdetail);
		}

		LineDetail configuration = new LineDetail(12, items);
		Solution result = defaultSolver.solve(configuration).get();
		assertEquals(21, result.getValue());
		assertEquals(3, result.getItems().size());
	}

	@Test
	public void should_return_lesser_items_if_multiple_item_same_price() {
		DefaultSolver defaultSolver = new DefaultSolver();
		List<ItemDetail> items = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			ItemDetail itemdetail = new ItemDetail(i, 2.0 + i, 5 + i);
			items.add(itemdetail);
		}

		ItemDetail itemdetail = new ItemDetail(5, 12.0, 21);
		items.add(itemdetail);

		LineDetail configuration = new LineDetail(12, items);
		Solution result = defaultSolver.solve(configuration).get();
		assertEquals(21, result.getValue());
		assertEquals(3, result.getItems().size());
	}

	@Test
	public void one_line_Test() {
		DefaultParser parser = new DefaultParser();
		Optional<LineDetail> item = parser.parse(
				"75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)");
		DefaultSolver defaultSolver = new DefaultSolver();
		Solution result = defaultSolver.solve(item.get()).get();
		assertEquals("2,7" + System.lineSeparator(), result.getItems().stream().map(ItemDetail::getIndex).map(String::valueOf)
				.collect(Collectors.joining(",", "", System.lineSeparator())));
	}
}
