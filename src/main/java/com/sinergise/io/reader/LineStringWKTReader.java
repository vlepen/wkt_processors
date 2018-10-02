package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.Double.parseDouble;

public class LineStringWKTReader implements GeometryWKTReader<LineString> {
	private static final String WKT_REGEX = "(?<coord>-?\\d+\\.?\\d*)";

	@Override
	public LineString read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new LineString();
		}
		Pattern pattern = Pattern.compile(WKT_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		List<Double> coords = new ArrayList<>();
		while (matcher.find()) {
			coords.add(parseDouble(matcher.group("coord")));
		}
		return new LineString(coords.stream().mapToDouble(Double::doubleValue).toArray());
	}
}
