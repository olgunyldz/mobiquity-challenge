package com.mobiquity.packer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.mobiquity.packer.model.LineDetail;

public class DefaultParserTest {

	@Test
	public void if_empty_line() {
		DefaultParser defaultParser = new DefaultParser();
		
		String line = "";
		Optional<LineDetail> result = defaultParser.parse(line );
		assertFalse(result.isPresent());
	}
	
	@Test
	public void one_item_should_be_parsed_second_empty() {
		DefaultParser defaultParser = new DefaultParser();
		String line = "8 : (1,15.3,€34) (2,€33)";
		Optional<LineDetail> result = defaultParser.parse(line );
		assertTrue(result.isPresent());
		assertEquals(8,  result.get().getCapacity());
		assertEquals(1,  result.get().getItems().size());
		assertEquals(1, result.get().getItems().get(0).getIndex().intValue());
		assertEquals(Double.parseDouble("15.3"), 
				result.get().getItems().get(0).getWeight().doubleValue(),0.0);
		assertEquals(34, result.get().getItems().get(0).getValue().intValue());
	}
	
	@Test
	public void one_item_should_be_parsed() {
		DefaultParser defaultParser = new DefaultParser();
		String line = "8 : (1,15.3,€34)";
		Optional<LineDetail> result = defaultParser.parse(line );
		assertTrue(result.isPresent());
		assertEquals(8,  result.get().getCapacity());
		assertEquals(1,  result.get().getItems().size());
		assertEquals(1, result.get().getItems().get(0).getIndex().intValue());
		assertEquals(Double.parseDouble("15.3"), 
				result.get().getItems().get(0).getWeight().doubleValue(),0.0);
		assertEquals(34, result.get().getItems().get(0).getValue().intValue());
	}
	
	@Test
	public void two_items_should_be_parsed() {
		DefaultParser defaultParser = new DefaultParser();
		
		String line = "8 : (1,15.3,€34) (2,14.3,€33)";
		Optional<LineDetail> result = defaultParser.parse(line );
		assertTrue(result.isPresent());
		assertEquals(8,  result.get().getCapacity());
		assertEquals(2,  result.get().getItems().size());
		assertEquals(1, result.get().getItems().get(0).getIndex().intValue());
		assertEquals(Double.parseDouble("15.3"), 
				result.get().getItems().get(0).getWeight().doubleValue(),0.0);
		assertEquals(34, result.get().getItems().get(0).getValue().intValue());
		
		assertEquals(2, result.get().getItems().get(1).getIndex().intValue());
		assertEquals(Double.parseDouble("14.3"), 
				result.get().getItems().get(1).getWeight().doubleValue(),0.0);
		assertEquals(33, result.get().getItems().get(1).getValue().intValue());
	}

}
