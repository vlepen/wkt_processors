package com.sinergise.io.printer;

import com.sinergise.geometry.Polygon;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;

public class PolygonWKTPrinter implements GeometryWKTPrinter<Polygon> {
	private static final ThreadLocal<LineStringWKTPrinter> LINE_STRING_PRINTER =
			withInitial(LineStringWKTPrinter::new);

	@Override
	public String print(Polygon geometry) {
		return "POLYGON " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(Polygon geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append(LINE_STRING_PRINTER.get().printShortDecorated(geometry.getOuter()));
			if (geometry.getNumHoles() > 0) {
				wktString.append(", ");
				IntStream.range(0, geometry.getNumHoles())
						.forEach(i -> {
							wktString.append(LINE_STRING_PRINTER.get().printShortDecorated(geometry.getHole(i)));
							if (i < geometry.getNumHoles() - 1) {
								wktString.append(", ");
							}
						});
			}
		}

		return wktString.toString();
	}
}
