package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;

public interface GeometryWKTReader<T extends Geometry> {
	T read(String wktString);
}
