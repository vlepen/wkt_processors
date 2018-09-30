package com.sinergise.io.printer;

import com.sinergise.geometry.LineString;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.String.format;

public class LineStringWKTPrinter implements GeometryWKTPrinter<LineString> {
	@Override
	public String print(LineString geometry) {
		return "LINESTRING " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(LineString geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.getNumCoords())
					.forEach(i -> {
						wktString.append(format(
								"%s %s",
								WKTCoordPrinter.print(geometry.getX(i)),
								WKTCoordPrinter.print(geometry.getY(i)))
						);
						if (i < geometry.getNumCoords() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
