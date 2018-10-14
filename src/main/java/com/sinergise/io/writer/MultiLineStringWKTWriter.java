package com.sinergise.io.writer;

import com.sinergise.geometry.MultiLineString;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.MULTI_LINE_STRING;

public class MultiLineStringWKTWriter implements GeometryWKTWriter<MultiLineString> {
	private static final LineStringWKTWriter LINE_STRING_WRITER = new LineStringWKTWriter();

	@Override
	public String write(MultiLineString geometry) {
		return MULTI_LINE_STRING.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(MultiLineString geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(LINE_STRING_WRITER.writeShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
