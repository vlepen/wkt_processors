package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.String.format;
import static java.lang.ThreadLocal.withInitial;

public class PolygonWKTReader implements GeometryWKTReader<Polygon> {
	private static final String WKT_REGEX = "\\((?<linestring>[-\\d\\. ,]+)\\)";
	private static final ThreadLocal<LineStringWKTReader> LINE_STRING_READER =
			withInitial(LineStringWKTReader::new);

	@Override
	public Polygon read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new Polygon();
		}
		Pattern pattern = Pattern.compile(WKT_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		boolean hasParsedOuter = false;
		LineString outer = null;
		List<LineString> holes = new ArrayList<>();
		while (matcher.find()) {
			String lineString = matcher.group("linestring");
			if (!hasParsedOuter) {
				outer = LINE_STRING_READER.get().read(lineString);
				hasParsedOuter = true;
			} else if (lineString != null) {
				holes.add(LINE_STRING_READER.get().read(lineString));
			}
		}
		if (outer == null) {
			throw new IllegalArgumentException(format("Failed to read WKT Polygon string: %s", wktString));
		}
		return new Polygon(outer, holes.isEmpty() ? null : holes.toArray(new LineString[0]));
	}
}
