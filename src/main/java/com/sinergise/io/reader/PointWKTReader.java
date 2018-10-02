package com.sinergise.io.reader;

import com.sinergise.geometry.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;

public class PointWKTReader implements GeometryWKTReader<Point> {
	private static final String WKT_REGEX = "(?<x>-?\\d+\\.?\\d*) (?<y>-?\\d+\\.?\\d*)";

	@Override
	public Point read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new Point();
		}
		Pattern pattern = Pattern.compile(WKT_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		if (matcher.matches()) {
			return new Point(
					parseDouble(matcher.group("x")),
					parseDouble(matcher.group("y"))
			);
		}
		throw new IllegalArgumentException(format("Failed to read WKT Point string: %s", wktString));
	}
}
