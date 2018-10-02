package com.sinergise.io.printer;

import com.sinergise.geometry.MultiLineString;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.MULTI_LINE_STRING;
import static java.lang.ThreadLocal.withInitial;

public class MultiLineStringWKTPrinter implements GeometryWKTPrinter<MultiLineString> {
	private static final ThreadLocal<LineStringWKTPrinter> LINE_STRING_PRINTER =
			withInitial(LineStringWKTPrinter::new);

	@Override
	public String print(MultiLineString geometry) {
		return MULTI_LINE_STRING.getWktName() + " " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(MultiLineString geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(LINE_STRING_PRINTER.get().printShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
