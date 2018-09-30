package com.sinergise.io.printer.util;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryType;
import com.sinergise.io.printer.*;

import java.util.Map;

import static com.sinergise.geometry.GeometryType.*;
import static java.util.Map.entry;

public final class WKTPrintersDelegate {
	private static final ThreadLocal<Map<GeometryType, GeometryWKTPrinter<? extends Geometry>>> PRINTERS =
			ThreadLocal.withInitial(() -> Map.ofEntries(
					entry(POINT, new PointWKTPrinter()),
					entry(LINE_STRING, new LineStringWKTPrinter()),
					entry(POLYGON, new PolygonWKTPrinter()),
					entry(GEOMETRY_COLLECTION, new GeometryCollectionWKTPrinter()),
					entry(MULTI_POINT, new MultiPointWKTPrinter()),
					entry(MULTI_LINE_STRING, new MultiLineStringWKTPrinter()),
					entry(MULTI_POLYGON, new MultiPolygonWKTPrinter())
			));

	private WKTPrintersDelegate() {
	}

	public static String delegateToPrinter(Geometry geometry) {
		GeometryWKTPrinter printer = PRINTERS.get().get(GeometryType.findByClass(geometry.getClass()));
		return printer.print(geometry);
	}
}
