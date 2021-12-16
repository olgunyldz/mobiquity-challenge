package com.mobiquity.packer;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.mobiquity.exception.APIException;

public class PackerTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testPacker() throws APIException, IOException {
		File createdFile = folder.newFile("myfile.txt");
		// Write something to it.
		Path filePath = Paths.get(createdFile.toURI());
		Files.writeString(filePath, "81 : (1,53.38,45) (2,88.62,98) (3,78.48,3) (4,72.30,76) (5,30.18,9) (6,46.34,48)" + System.lineSeparator(), StandardOpenOption.APPEND);
		Files.writeString(filePath, "8 : (1,15.3,34)" + System.lineSeparator(), StandardOpenOption.APPEND);
		Files.writeString(filePath, "75 : (1,85.31,29) (2,14.55,74) (3,3.98,16) (4,26.24,55) (5,63.69,52) (6,76.25,75) (7,60.02,74) (8,93.18,35) (9,89.95,78)"+ System.lineSeparator(), StandardOpenOption.APPEND);
		Files.writeString(filePath, "56 : (1,90.72,13) (2,33.80,40) (3,43.15,10) (4,37.97,16) (5,46.81,36) (6,48.77,79) (7,81.80,45) (8,19.36,79) (9,6.76,64)"+ System.lineSeparator(), StandardOpenOption.APPEND);
		String result = Packer.pack(createdFile.getAbsolutePath());
		assertEquals("4" + System.lineSeparator() + "-" + System.lineSeparator() + "2,7" + System.lineSeparator()
				+ "8,9", result);
	}

}