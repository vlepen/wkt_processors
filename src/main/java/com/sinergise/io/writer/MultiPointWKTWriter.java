package com.sinergise.io.writer;

import com.sinergise.geometry.MultiPoint;

import java.util.stream.IntStream;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static com.sinergise.io.geometry.WKTGeometryType.MULTI_POINT;
import static java.lang.ThreadLocal.withInitial;

public class MultiPointWKTWriter implements GeometryWKTWriter<MultiPoint> {
	private static final ThreadLocal<PointWKTWriter> POINT_WRITER = withInitial(PointWKTWriter::new);

	@Override
	public String write(MultiPoint geometry) {
		return MULTI_POINT.getWktName() + " " + writeShortDecorated(geometry);
	}

	@Override
	public String writeShort(MultiPoint geometry) {
		StringBuilder wktString = new StringBuilder();
		if (geometry.isEmpty()) {
			wktString.append(EMPTY);
		} else {
			IntStream.range(0, geometry.size())
					.forEach(i -> {
						wktString.append(POINT_WRITER.get().writeShortDecorated(geometry.get(i)));
						if (i < geometry.size() - 1) {
							wktString.append(", ");
						}
					});
		}

		return wktString.toString();
	}
}
