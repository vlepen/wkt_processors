package com.sinergise.io.writer;

import com.sinergise.geometry.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeometryCollectionWKTWriterTest {
	@Test
	public void shouldWriterEmptyGeometryCollection() {
		assertThat(new GeometryCollectionWKTWriter().write(new GeometryCollection<>()))
				.isEqualTo("GEOMETRYCOLLECTION EMPTY");
	}

	@Test
	public void shouldWriterNonEmptyGeometryCollectionWithPrimitiveGeometryTypes() {
		assertThat(new GeometryCollectionWKTWriter().write(new GeometryCollection<>(
				new Geometry[]{
						new Point(10, 20),
						new LineString(new double[]{4, 6, 7, 10}),
						new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
								new LineString[]{
										new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})
								}
						)
				}
		)))
				.isEqualTo("GEOMETRYCOLLECTION (" +
						"POINT (10 20), " +
						"LINESTRING (4 6, 7 10), " +
						"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15))" +
						")");
	}
}