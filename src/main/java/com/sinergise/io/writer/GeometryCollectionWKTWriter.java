package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.GEOMETRY_COLLECTION;
import static com.sinergise.io.writer.util.WKTWritersDelegate.delegateToWriter;

public class GeometryCollectionWKTWriter implements GeometryWKTWriter<GeometryCollection<? extends Geometry>> {
	@Override
	public String write(GeometryCollection<? extends Geometry> geometry) {
		StringBuilder wktString = new StringBuilder(GEOMETRY_COLLECTION.getWktName() + " ");
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			wktString.append("(");
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(delegateToWriter(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
			wktString.append(")");
		}

		return wktString.toString();
	}

	@Override
	public String writeShort(GeometryCollection<? extends Geometry> geometry) {
		return write(geometry);
	}
}
