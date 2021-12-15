package com.mobiquity.packer.factory;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.service.Solver;
import com.mobiquity.packer.service.impl.DefaultSolver;

public final class SolverFactory {
	private SolverFactory() throws APIException {
		throw new APIException("Cannot create instance");
	}
	public static Solver createSolverInstance() {
		return new DefaultSolver();
	}
}
