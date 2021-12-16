package com.mobiquity.packer.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.mobiquity.packer.service.InputStream;

public final class FileInputStream implements InputStream{
	@Override
	public final Stream<String> read(String inputPath) throws IOException {		
		return Files.lines(Paths.get(inputPath), Charset.defaultCharset());
	}

}
