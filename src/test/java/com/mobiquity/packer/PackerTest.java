package com.mobiquity.packer;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.mobiquity.exception.APIException;

public class PackerTest {

	@Test
	public void testPacker() throws APIException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("input.txt").getFile());
		String result = Packer.pack(file.getAbsolutePath());
		assertEquals("4" + System.lineSeparator() + "-" + System.lineSeparator() + "2,7" + System.lineSeparator()
				+ "8,9", result);
	}

}