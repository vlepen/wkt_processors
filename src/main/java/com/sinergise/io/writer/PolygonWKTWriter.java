package com.sinergise.io.writer;

import com.sinergise.geometry.Polygon;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.POLYGON;
import static java.lang.ThreadLocal.withInitial;

public class PolygonWKTWriter implements GeometryWKTWriter<Polygon> {
	private static final ThreadLocal<LineStringWKTWriter> LINE_STRING_WRITER =
			withInitial(LineStringWKTWriter::new);

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
			wktString.append(LINE_STRING_WRITER.get().writeShortDecorated(geometry.getOuter()));
			if (geometry.getNumHoles() > 0) {
				wktString.append(", ");
				IntStream.range(0, geometry.getNumHoles())
						.forEach(i -> {
							wktString.append(LINE_STRING_WRITER.get().writeShortDecorated(geometry.getHole(i)));
							if (i < geometry.getNumHoles() - 1) {
								wktString.append(", ");
							}
						});
			}
		}

		return wktString.toString();
	}
}
