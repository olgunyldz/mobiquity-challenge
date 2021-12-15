package com.mobiquity.packer.service;

import java.io.IOException;
import java.util.stream.Stream;

public interface InputStream {

	Stream<String> read(String path) throws IOException;
}
