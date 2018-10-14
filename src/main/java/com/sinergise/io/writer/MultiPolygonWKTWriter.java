package com.sinergise.io.writer;

import com.sinergise.geometry.MultiPolygon;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.MULTI_POLYGON;

public class MultiPolygonWKTWriter implements GeometryWKTWriter<MultiPolygon> {
	private static final PolygonWKTWriter POLYGON_WRITER = new PolygonWKTWriter();

	@Override
	public String write(MultiPolygon geometry) {
		return MULTI_POLYGON.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(MultiPolygon geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(POLYGON_WRITER.writeShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
