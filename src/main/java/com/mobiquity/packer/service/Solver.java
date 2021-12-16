package com.mobiquity.packer.service;

import java.util.Optional;

import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;


public interface Solver {
	Optional<Solution> solve(LineDetail configuration);
}
