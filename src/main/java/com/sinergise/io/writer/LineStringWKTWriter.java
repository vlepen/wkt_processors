package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.LINE_STRING;
import static java.lang.String.format;

public class LineStringWKTWriter implements GeometryWKTWriter<LineString> {
	@Override
	public String write(LineString geometry) {
		return LINE_STRING.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(LineString geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.getNumCoords())
					.forEach(i -> {
						wktString.append(format(
								"%s %s",
								WKTCoordFormatter.format(geometry.getX(i)),
								WKTCoordFormatter.format(geometry.getY(i)))
						);
						if (i < geometry.getNumCoords() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
