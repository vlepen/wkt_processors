package com.sinergise.io.printer;

import com.sinergise.geometry.MultiPoint;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.MULTI_POINT;
import static java.lang.ThreadLocal.withInitial;

public class MultiPointWKTPrinter implements GeometryWKTPrinter<MultiPoint> {
	private static final ThreadLocal<PointWKTPrinter> POINT_PRINTER = withInitial(PointWKTPrinter::new);

	@Override
	public String print(MultiPoint geometry) {
		return MULTI_POINT.getWktName() + " " + printShortDecorated(geometry);
	}

	@Override
	public String printShort(MultiPoint geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(POINT_PRINTER.get().printShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
