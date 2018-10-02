package com.sinergise.io;

import com.sinergise.geometry.Geometry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.*;
import static com.sinergise.io.reader.util.WKTReadersDelegate.delegateToPrinter;
import static java.lang.String.format;

public class WKTReader {
	private static final String READER_EMPTY_GROUP_NAME = "empty";
	private static final String READER_TYPE_GROUP_NAME = "type";
	private static final String READER_VALUE_GROUP_NAME = "value";
	private static final String GEOMETRY_TYPE_REGEX =
			"^(?<" + READER_TYPE_GROUP_NAME + ">" +
					POINT.getWktName() + "|" +
					LINE_STRING.getWktName() + "|" +
					POLYGON.getWktName() + "|" +
					GEOMETRY_COLLECTION.getWktName() + "|" +
					MULTI_POINT.getWktName() + "|" +
					MULTI_LINE_STRING.getWktName() + "|" +
					MULTI_POLYGON.getWktName() +
					")" +
					"\\s((?<" + READER_EMPTY_GROUP_NAME + ">" + EMPTY + ")|\\((?<" + READER_VALUE_GROUP_NAME + ">.*)\\s*\\)$)";

	/**
	 * Transforms the input WKT-formatted String into Geometry object
	 */
	public Geometry read(String wktString) {
		Pattern pattern = Pattern.compile(GEOMETRY_TYPE_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		if (matcher.matches()) {
			return delegateToPrinter(
					findByWKTName(matcher.group(READER_TYPE_GROUP_NAME)).getGeometryType(),
					matcher.group(READER_EMPTY_GROUP_NAME) != null ?
							matcher.group(READER_EMPTY_GROUP_NAME) :
							matcher.group(READER_VALUE_GROUP_NAME)
			);
		}
		throw new IllegalArgumentException(format("Failed to read WKT string: %s", wktString));
	}
}
