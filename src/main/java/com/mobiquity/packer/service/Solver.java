package com.mobiquity.packer.service;

import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.model.Solution;


public interface Solver {
	Solution solve(LineDetail configuration);
}
