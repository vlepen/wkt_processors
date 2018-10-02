package com.sinergise.io.reader.util;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryType;
import com.sinergise.io.reader.*;

import java.util.Map;

import static com.sinergise.geometry.GeometryType.*;
import static java.util.Map.entry;

public final class WKTReadersDelegate {
	private static final ThreadLocal<Map<GeometryType, GeometryWKTReader<? extends Geometry>>> READERS =
			ThreadLocal.withInitial(() -> Map.ofEntries(
					entry(POINT, new PointWKTReader()),
					entry(LINE_STRING, new LineStringWKTReader()),
					entry(POLYGON, new PolygonWKTReader()),
					entry(GEOMETRY_COLLECTION, new GeometryCollectionWKTReader()),
					entry(MULTI_POINT, new MultiPointWKTReader()),
					entry(MULTI_LINE_STRING, new MultiLineStringWKTReader()),
					entry(MULTI_POLYGON, new MultiPolygonWKTReader())
			));

	private WKTReadersDelegate() {
	}

	public static Geometry delegateToReader(GeometryType geometryType, String values) {
		GeometryWKTReader reader = READERS.get().get(geometryType);
		return reader.read(values);
	}
}
