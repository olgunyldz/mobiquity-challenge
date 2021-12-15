package com.mobiquity.packer.factory;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.service.Parser;
import com.mobiquity.packer.service.impl.DefaultParser;

public final class ParserFactory {
	private ParserFactory() throws APIException {
		throw new APIException("Cannot create instance");
	}
	public static Parser createParserInstance() {
		return new DefaultParser();
	}
}
