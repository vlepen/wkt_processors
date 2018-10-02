package com.sinergise.io.reader;

import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Arrays.stream;

public class MultiPolygonWKTReader implements GeometryWKTReader<MultiPolygon> {
	private static final String WKT_REGEX = "\\)\\),\\s\\(\\(";
	private static final ThreadLocal<PolygonWKTReader> POLYGON_READER = withInitial(PolygonWKTReader::new);

	@Override
	public MultiPolygon read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new MultiPolygon();
		}

		String[] splitPolygons = wktString.split(WKT_REGEX);

		return new MultiPolygon(stream(splitPolygons)
				.map(polygonString -> POLYGON_READER.get().read(adjustBracketsInPolygonString(polygonString)))
				.toArray(Polygon[]::new));
	}

	private String adjustBracketsInPolygonString(String polygonString) {
		polygonString = polygonString.replace("((", "(").replace("))", ")");
		if (!polygonString.startsWith("(")) {
			polygonString = "(" + polygonString;
		}
		if (!polygonString.endsWith(")")) {
			polygonString += ")";
		}
		return polygonString;
	}
}
