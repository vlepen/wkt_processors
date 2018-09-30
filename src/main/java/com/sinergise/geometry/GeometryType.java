package com.sinergise.geometry;

import static java.util.Arrays.stream;

public enum GeometryType {
	POINT(Point.class),
	LINE_STRING(LineString.class),
	POLYGON(Polygon.class),
	GEOMETRY_COLLECTION(GeometryCollection.class),
	MULTI_POINT(MultiPoint.class),
	MULTI_LINE_STRING(MultiLineString.class),
	MULTI_POLYGON(MultiPolygon.class);

	private Class geometryClass;

	GeometryType(Class<? extends Geometry> clazz) {
		this.geometryClass = clazz;
	}

	public static GeometryType findByClass(Class<? extends Geometry> clazz) {
		return stream(GeometryType.values())
				.filter(geometryType -> geometryType.geometryClass.equals(clazz))
				.findFirst()
				.orElseThrow();
	}
}
