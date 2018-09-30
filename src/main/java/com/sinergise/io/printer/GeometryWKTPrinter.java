package com.sinergise.io.printer;

import com.sinergise.geometry.Geometry;

import static com.sinergise.io.common.WKTConstants.EMPTY;

public interface GeometryWKTPrinter<T extends Geometry> {
	String print(T geometry);

	String printShort(T geometry);

	default String printShortDecorated(T geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append("(");
			wktString.append(printShort(geometry));
			wktString.append(")");
		}

		return wktString.toString();
	}
}
