package com.sinergise.io.writer.util;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryType;
import com.sinergise.io.writer.*;

import java.util.Map;

import static com.sinergise.geometry.GeometryType.*;
import static java.util.Map.entry;

public final class WKTWritersDelegate {
	private static final ThreadLocal<Map<GeometryType, GeometryWKTWriter<? extends Geometry>>> WRITERS =
			ThreadLocal.withInitial(() -> Map.ofEntries(
					entry(POINT, new PointWKTWriter()),
					entry(LINE_STRING, new LineStringWKTWriter()),
					entry(POLYGON, new PolygonWKTWriter()),
					entry(GEOMETRY_COLLECTION, new GeometryCollectionWKTWriter()),
					entry(MULTI_POINT, new MultiPointWKTWriter()),
					entry(MULTI_LINE_STRING, new MultiLineStringWKTWriter()),
					entry(MULTI_POLYGON, new MultiPolygonWKTWriter())
			));

	private WKTWritersDelegate() {
	}

	public static String delegateToWriter(Geometry geometry) {
		GeometryWKTWriter writer = WRITERS.get().get(GeometryType.findByClass(geometry.getClass()));
		return writer.write(geometry);
	}
}
