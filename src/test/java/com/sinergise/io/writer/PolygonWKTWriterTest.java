package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PolygonWKTWriterTest {
	@Test
	public void shouldWriteEmptyPolygon() {
		assertThat(new PolygonWKTWriter().write(new Polygon())).isEqualTo("POLYGON EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyPolygonWithNoHoles() {
		assertThat(new PolygonWKTWriter().write(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), null)
		))
				.isEqualTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))");
	}

	@Test
	public void shouldWriteNonEmptyPolygonWithHole() {
		assertThat(new PolygonWKTWriter().write(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{
								new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})
						}
				)
		))
				.isEqualTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15))");
	}

	@Test
	public void shouldWriteNonEmptyPolygonWithHoles() {
		assertThat(new PolygonWKTWriter().write(createPolygon()))
				.isEqualTo(
						"POLYGON (" +
								"(30 10, 40 40, 20 40, 10 20, 30 10), " +
								"(35 15, 45 45, 25 45, 15 25, 35 15), " +
								"(36 16, 46 46, 26 46, 16 26, 36 16)" +
								")"
				);
	}

	@Test
	public void shouldWriteShortEmptyPolygon() {
		assertThat(new PolygonWKTWriter().writeShort(new Polygon())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyPolygonWithNoHoles() {
		assertThat(new PolygonWKTWriter().writeShort(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), null)
		))
				.isEqualTo("(30 10, 40 40, 20 40, 10 20, 30 10)");
	}

	@Test
	public void shouldWriteShortNonEmptyPolygonWithHole() {
		assertThat(new PolygonWKTWriter().writeShort(
				new Polygon(
						new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})}
				)
		))
				.isEqualTo("(30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15)");
	}

	@Test
	public void shouldWriteShortNonEmptyPolygonWithHoles() {
		assertThat(new PolygonWKTWriter().writeShort(createPolygon()))
				.isEqualTo(
						"(30 10, 40 40, 20 40, 10 20, 30 10), " +
								"(35 15, 45 45, 25 45, 15 25, 35 15), " +
								"(36 16, 46 46, 26 46, 16 26, 36 16)"
				);
	}

	private Polygon createPolygon() {
		return new Polygon(
				new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
				new LineString[]{
						new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15}),
						new LineString(new double[]{36, 16, 46, 46, 26, 46, 16, 26, 36, 16})
				}
		);
	}
}