package com.sinergise.io.reader;

import com.sinergise.geometry.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeometryCollectionWKTReaderTest {
	@Test
	public void shouldReadWKTEmptyGeometryCollection() {
		assertThat(new GeometryCollectionWKTReader().read("EMPTY"))
				.isEqualTo(new GeometryCollection<>());
	}

	@Test
	public void shouldReadNonEmptyGeometryCollection() {
		assertThat(new GeometryCollectionWKTReader().read(
				"POINT (10 20), " +
						"LINESTRING (4 6, 7 10), " +
						"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15)), " +
						"MULTIPOINT EMPTY, " +
						"MULTIPOINT ((-4 6), (7.4 -8.2), (10 3.22)), " +
						"MULTILINESTRING ((-4 6, 7.4 -8.2), (10 3.22, 5.4 32.1), (-33.2 2)), " +
						"MULTIPOLYGON (" +
						"((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20), (35 25, 25 15, 25 25, 35 25))" +
						")"
		))
				.isEqualTo(new GeometryCollection<>(
						new Geometry[]{
								new Point(10, 20),
								new LineString(new double[]{4, 6, 7, 10}),
								new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
										new LineString[]{
												new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})
										}
								),
								new MultiPoint(),
								new MultiPoint(
										new Point[]{
												new Point(-4, 6),
												new Point(7.4, -8.2),
												new Point(10, 3.22)
										}
								),
								new MultiLineString(
										new LineString[]{
												new LineString(new double[]{-4, 6, 7.4, -8.2}),
												new LineString(new double[]{10, 3.22, 5.4, 32.1}),
												new LineString(new double[]{-33.2, 2})
										}
								),
								new MultiPolygon(
										new Polygon[]{
												new Polygon(
														new LineString(new double[]{40, 40, 20, 45, 45, 30, 40, 40}),
														null
												),
												new Polygon(
														new LineString(new double[]{20, 35, 10, 30, 10, 10, 30, 5, 45, 20, 20, 35}),
														new LineString[]{
																new LineString(new double[]{30, 20, 20, 15, 20, 25, 30, 20}),
																new LineString(new double[]{35, 25, 25, 15, 25, 25, 35, 25})
														}
												)
										}
								)
						}
				));
	}
}