package com.sinergise.io.printer;

import com.sinergise.geometry.Point;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.String.format;

public class PointWKTPrinter implements GeometryWKTPrinter<Point> {
	@Override
	public String print(Point geometry) {
		return "POINT " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(Point geometry) {
		return geometry.isEmpty() ?
				EMPTY :
				format(
						"%s %s",
						WKTCoordPrinter.print(geometry.getX()),
						WKTCoordPrinter.print(geometry.getY())
				);
	}
}
