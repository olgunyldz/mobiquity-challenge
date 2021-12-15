package com.mobiquity.packer.factory;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.service.InputStream;
import com.mobiquity.packer.service.impl.FileInputStream;

public final class InputStreamFactory {

	private InputStreamFactory() throws APIException {
		throw new APIException("Cannot create instance");
	}
	public static InputStream createInputStream() {
		return new FileInputStream();
	}
}
