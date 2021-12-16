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
import com.mobiquity.packer.service.Solver;

public class Packer {

	private static volatile Packer instance;
	private PackerContext context;

	private Packer() {
		this.context = new PackerContext(SolverFactory.createSolverInstance(), ParserFactory.createParserInstance(),
				InputStreamFactory.createInputStream());
	}

	public static synchronized Packer getInstance() {
		if (instance == null) {
			instance = new Packer();
		}
		return instance;
	}



	public static String pack(String filePath) throws APIException {
		return Packer.getInstance().context.execute(filePath);
	}
}
