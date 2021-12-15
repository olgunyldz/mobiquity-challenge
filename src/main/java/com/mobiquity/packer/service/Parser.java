package com.mobiquity.packer.service;

import java.util.Optional;

import com.mobiquity.packer.model.LineDetail;

public interface Parser {
	Optional<LineDetail> parse(String line);
}
