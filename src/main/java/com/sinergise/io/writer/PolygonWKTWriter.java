package com.sinergise.io.writer;

import com.sinergise.geometry.Polygon;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.POLYGON;

public class PolygonWKTWriter implements GeometryWKTWriter<Polygon> {
	private static final LineStringWKTWriter LINE_STRING_WRITER = new LineStringWKTWriter();

	@Override
	public String write(Polygon geometry) {
		return POLYGON.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(Polygon geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append(LINE_STRING_WRITER.writeShortDecorated(geometry.getOuter()));
			if (geometry.getNumHoles() > 0) {
				wktString.append(", ");
				IntStream.range(0, geometry.getNumHoles())
						.forEach(i -> {
							wktString.append(LINE_STRING_WRITER.writeShortDecorated(geometry.getHole(i)));
							if (i < geometry.getNumHoles() - 1) {
								wktString.append(", ");
							}
						});
			}
		}

		return wktString.toString();
	}
}
