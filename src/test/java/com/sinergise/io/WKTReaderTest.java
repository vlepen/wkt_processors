package com.sinergise.io;

import com.sinergise.geometry.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WKTReaderTest {
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenReadingInvalidGeometryString() {
		new WKTReader().read("invalid string");
	}

	@Test
	public void shouldReadEmptyWKTPoint() {
		assertThat(new WKTReader().read("POINT EMPTY")).isEqualTo(new Point());
	}

	@Test
	public void shouldReadWKTPoint() {
		assertThat(new WKTReader().read("POINT (-2.2 3)")).isEqualTo(new Point(-2.2, 3));
	}

	@Test
	public void shouldReadWKTEmptyLineString() {
		assertThat(new WKTReader().read("LINESTRING EMPTY")).isEqualTo(new LineString());
	}

	@Test
	public void shouldReadWKTLineString() {
		assertThat(new WKTReader().read("LINESTRING (-2.2 3, 4.3 -87.5, 43.3 0.3)"))
				.isEqualTo(new LineString(new double[]{-2.2, 3, 4.3, -87.5, 43.3, 0.3}));
	}

	@Test
	public void shouldReadWKTEmptyPolygon() {
		assertThat(new WKTReader().read("POLYGON EMPTY"))
				.isEqualTo(new Polygon());
	}

	@Test
	public void shouldReadWKTPolygon() {
		assertThat(new WKTReader().read(
				"POLYGON (" +
						"(30 10, 40 40, 20 40, 10 20, 30 10), " +
						"(35 15, 45 45, 25 45, 15 25, 35 15), " +
						"(36 16, 46 46, 26 46, 16 26, 36 16)" +
						")"
		))
				.isEqualTo(new Polygon(
						new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{
								new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15}),
								new LineString(new double[]{36, 16, 46, 46, 26, 46, 16, 26, 36, 16})
						}
				));
	}

	@Test
	public void shouldReadWKTEmptyMultiPoint() {
		assertThat(new WKTReader().read("MULTIPOINT EMPTY"))
				.isEqualTo(new MultiPoint());
	}

	@Test
	public void shouldReadWKTMultiPoint() {
		assertThat(new WKTReader().read("MULTIPOINT ((-4 6), (7.4 -8.2), (10 3.22))"))
				.isEqualTo(new MultiPoint(
						new Point[]{
								new Point(-4, 6),
								new Point(7.4, -8.2),
								new Point(10, 3.22)
						}
				));
	}

	@Test
	public void shouldReadWKTEmptyMultiLineString() {
		assertThat(new WKTReader().read("MULTILINESTRING EMPTY"))
				.isEqualTo(new MultiLineString());
	}

	@Test
	public void shouldReadWKTMultiLineString() {
		assertThat(new WKTReader().read("MULTILINESTRING ((-4 6, 7.4 -8.2), (10 3.22, 5.4 32.1), (-33.2 2))"))
				.isEqualTo(new MultiLineString(
						new LineString[]{
								new LineString(new double[]{-4, 6, 7.4, -8.2}),
								new LineString(new double[]{10, 3.22, 5.4, 32.1}),
								new LineString(new double[]{-33.2, 2})
						}
				));
	}

	@Test
	public void shouldReadEmptyMultiPolygon() {
		assertThat(new WKTReader().read("MULTIPOLYGON EMPTY")).isEqualTo(new MultiPolygon());
	}

	@Test
	public void shouldReadNonEmptyMultiPolygon() {
		assertThat(new WKTReader().read(
				"MULTIPOLYGON (" +
						"((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20), (35 25, 25 15, 25 25, 35 25))" +
						")"
		))
				.isEqualTo(new MultiPolygon(
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
				));
	}

	@Test
	public void shouldReadWKTEmptyGeometryCollection() {
		assertThat(new WKTReader().read("GEOMETRYCOLLECTION EMPTY")).isEqualTo(new GeometryCollection<>());
	}

	@Test
	public void shouldReadNonEmptyGeometryCollection() {
		assertThat(new WKTReader().read(
				"GEOMETRYCOLLECTION (" +
						"POINT (10 20), " +
						"LINESTRING (4 6, 7 10), " +
						"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15))" +
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
								)
						}
				));
	}
}