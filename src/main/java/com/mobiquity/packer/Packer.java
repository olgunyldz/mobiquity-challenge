package com.mobiquity.packer;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.factory.InputStreamFactory;
import com.mobiquity.packer.factory.ParserFactory;
import com.mobiquity.packer.factory.SolverFactory;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;
import com.mobiquity.packer.service.Parser;

public class Packer {

	public static String pack(String filePath) throws APIException {

		try {
			Parser parser = ParserFactory.createParserInstance();
			String result = InputStreamFactory.createInputStream()//
					.read(filePath)// read all lines
					.map(line -> parser.parse(line)) // parse the line
					.map(item -> process(item) ) // process the line
					.collect(Collectors.joining(System.lineSeparator()));
			System.out.println(result);
			return result;

		} catch (IOException e) {
			throw new APIException("Path not found", e);
		}
	}

	private static String process(Optional<LineDetail> configuration) {
		Solution solution = SolverFactory.createSolverInstance().solve(configuration.get());

		if (solution.getItems() != null && !solution.getItems().isEmpty()) {
			return solution.getItems().stream().map(i -> i.getIndex().toString()).collect(Collectors.joining(","));
		} else {
			return "-";
		}

	}
}
