package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;

import static com.sinergise.io.common.WKTConstants.EMPTY;

public interface GeometryWKTWriter<T extends Geometry> {
	String write(T geometry);

	String writeShort(T geometry);

	default String writeShortDecorated(T geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append("(");
			wktString.append(writeShort(geometry));
			wktString.append(")");
		}

		return wktString.toString();
	}
}
