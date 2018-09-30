package com.sinergise.io.printer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.printer.util.WKTPrintersDelegate.delegateToPrinter;

public class GeometryCollectionWKTPrinter implements GeometryWKTPrinter<GeometryCollection<? extends Geometry>> {
	@Override
	public String print(GeometryCollection<? extends Geometry> geometry) {
		StringBuilder wktString = new StringBuilder("GEOMETRYCOLLECTION ");
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append("(");
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(delegateToPrinter(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
			wktString.append(")");
		}

		return wktString.toString();
	}

	@Override
	public String printShort(GeometryCollection<? extends Geometry> geometry) {
		return print(geometry);
	}
}
