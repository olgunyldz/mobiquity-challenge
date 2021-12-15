package com.mobiquity.packer.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mobiquity.packer.model.ItemDetail;
import com.mobiquity.packer.model.LineDetail;
import com.mobiquity.packer.service.Parser;

public final class DefaultParser implements Parser {

	public final Optional<LineDetail> parse(String line) {

		final String[] lineParts = line.split(" : ");
		//if there is no two part mens, capacity and items should exists in a line
		if (lineParts.length == 2) {
			final Integer weight = Integer.parseInt(lineParts[0]);
			// get all the parts
			final String[] itemsParts = lineParts[1].split("\\s+");

			//if there is not part exists in the line
			if (itemsParts.length > 0) {

				List<ItemDetail> itemsList = Arrays.asList(itemsParts).stream()//
						.map(itemString -> parseItemDetail(itemString))// parse an item
						.filter(Optional::isPresent )//if item exists
						.map(Optional::get) // get its value
						.collect(Collectors.toList());
				if (!itemsList.isEmpty()) {
					return Optional.of(new LineDetail(weight, itemsList));
				}

			}

		}

		return Optional.empty();
	}

	private Optional<ItemDetail> parseItemDetail(String itemString) {
		String[] itemParts = itemString.replace("(", "").replace(")", "").split(",");
		if (itemParts.length == 3) {

			return Optional.of(new ItemDetail(Integer.parseInt(itemParts[0]), Double.parseDouble(itemParts[1]),
					Integer.parseInt(itemParts[2].replace("€", ""))));

		}
		return Optional.empty();

	}
}
