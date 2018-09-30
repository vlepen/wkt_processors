package com.sinergise.io.printer;

import com.sinergise.geometry.MultiPolygon;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;

public class MultiPolygonWKTPrinter implements GeometryWKTPrinter<MultiPolygon> {
	private static final ThreadLocal<PolygonWKTPrinter> POLYGON_PRINTER = withInitial(PolygonWKTPrinter::new);

	@Override
	public String print(MultiPolygon geometry) {
		return "MULTIPOLYGON " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(MultiPolygon geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(POLYGON_PRINTER.get().printShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
