package com.mobiquity.packer.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileInputStreamTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void read_one_line() throws IOException {
		File createdFile = folder.newFile("myfile.txt");
		Path filePath = Paths.get(createdFile.toURI());
		Files.writeString(filePath, "8 : (1,15.3,€34)", StandardOpenOption.APPEND);

		FileInputStream fileInputStream = new FileInputStream();
		Stream<String> result = fileInputStream.read(createdFile.getAbsolutePath());
		assertEquals(1, result.collect(Collectors.toList()).size());
	}

	@Test
	public void read_two_line() throws IOException {
		File createdFile = folder.newFile("myfile.txt");
		// Write something to it.
		Path filePath = Paths.get(createdFile.toURI());
		Files.writeString(filePath, "8 : (1,15.3,€34)" + System.lineSeparator(), StandardOpenOption.APPEND);
		Files.writeString(filePath, "48 : (1,15.3,€34)", StandardOpenOption.APPEND);
		FileInputStream fileInputStream = new FileInputStream();
		Stream<String> result = fileInputStream.read(createdFile.getAbsolutePath());
		assertEquals(2, result.collect(Collectors.toList()).size());
	}

}
