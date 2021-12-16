package com.mobiquity.packer;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;
import com.mobiquity.packer.service.InputStream;
import com.mobiquity.packer.service.Parser;
import com.mobiquity.packer.service.Solver;

public class PackerContext {

	private Solver solver;
	
	private Parser parser;
	
	private InputStream inputStream;

	public PackerContext(Solver solver, Parser parser, InputStream inputStream) {
		super();
		this.solver = solver;
		this.parser = parser;
		this.inputStream = inputStream;
	}
	
	
	public String execute(String filePath) throws APIException {
		try {
			
			String result = this.inputStream//
					.read(filePath)// read all lines
					.map(line -> this.parser.parse(line)) // parse the line
					.map(item -> process(item)) // process the line
					.collect(Collectors.joining(System.lineSeparator()));
			return result;

		} catch (IOException e) {
			throw new APIException("Path not found", e);
		}
	}
	
	private String process(Optional<LineDetail> configuration) {
		if ( configuration.isEmpty() ) {
			return "-";
		}
		Optional<Solution> solution = this.solver.solve(configuration.get());

		if (solution.isPresent()) {
			return solution.get().getItems().stream().map(i -> i.getIndex().toString()).collect(Collectors.joining(","));
		} else {
			return "-";
		}

	}
}
