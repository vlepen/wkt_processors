package com.sinergise.io.geometry;

import com.sinergise.geometry.GeometryType;

import static java.util.Arrays.stream;

public enum WKTGeometryType {
	POINT("POINT", GeometryType.POINT),
	LINE_STRING("LINESTRING", GeometryType.LINE_STRING),
	POLYGON("POLYGON", GeometryType.POLYGON),
	MULTI_POINT("MULTIPOINT", GeometryType.MULTI_POINT),
	MULTI_LINE_STRING("MULTILINESTRING", GeometryType.MULTI_LINE_STRING),
	MULTI_POLYGON("MULTIPOLYGON", GeometryType.MULTI_POLYGON),
	GEOMETRY_COLLECTION("GEOMETRYCOLLECTION", GeometryType.GEOMETRY_COLLECTION);

	private final String wktName;
	private final GeometryType geometryType;

	WKTGeometryType(String wktName, GeometryType geometryType) {
		this.wktName = wktName;
		this.geometryType = geometryType;
	}

	public GeometryType getGeometryType() {
		return geometryType;
	}

	public String getWktName() {
		return wktName;
	}

	public static WKTGeometryType findByWKTName(String wktName) {
		return stream(WKTGeometryType.values())
				.filter(wktGeometryType -> wktGeometryType.wktName.equals(wktName))
				.findFirst()
				.orElseThrow();
	}
}
