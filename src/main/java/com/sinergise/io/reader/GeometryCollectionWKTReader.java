package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.WKTReader;

import java.util.List;
import java.util.stream.Collectors;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Arrays.stream;

public class GeometryCollectionWKTReader implements GeometryWKTReader<GeometryCollection<? extends Geometry>> {
	private static final String WKT_REGEX = ",\\s*([A-Za-z])";
	private static final ThreadLocal<WKTReader> WKT_READER = withInitial(WKTReader::new);

	@Override
	public GeometryCollection<? extends Geometry> read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new GeometryCollection<>();
		}

		String[] geometriesSplit = wktString.replaceAll(WKT_REGEX, "|$1").split("\\|");
		List<Geometry> geometries = stream(geometriesSplit)
				.map(WKT_READER.get()::read)
				.collect(Collectors.toList());

		return new GeometryCollection<>(geometries);
	}
}
