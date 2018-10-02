package com.sinergise.io.writer;

import com.sinergise.geometry.Point;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.POINT;
import static java.lang.String.format;

public class PointWKTWriter implements GeometryWKTWriter<Point> {
	@Override
	public String write(Point geometry) {
		return POINT.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(Point geometry) {
		return geometry.isEmpty() ?
				EMPTY :
				format(
						"%s %s",
						WKTCoordFormatter.format(geometry.getX()),
						WKTCoordFormatter.format(geometry.getY())
				);
	}
}
