package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPolygonWKTWriterTest {
	@Test
	public void shouldWriteEmptyMultiPolygon() {
		assertThat(new MultiPolygonWKTWriter().write(new MultiPolygon())).isEqualTo("MULTIPOLYGON EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyMultiPolygonWithHole() {
		assertThat(new MultiPolygonWKTWriter().write(createMultiPolygonWithHole()))
				.isEqualTo("MULTIPOLYGON (" +
						"((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), " +
						"(30 20, 20 15, 20 25, 30 20))" +
						")");
	}

	@Test
	public void shouldWriteNonEmptyMultiPolygonWithMultipleHoles() {
		assertThat(new MultiPolygonWKTWriter().write(createMultiPolygonWithMultipleHoles()))
				.isEqualTo("MULTIPOLYGON (" +
						"((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), " +
						"(30 20, 20 15, 20 25, 30 20), (35 25, 25 15, 25 25, 35 25))" +
						")");
	}

	@Test
	public void shouldWriteShortEmptyMultiPolygon() {
		assertThat(new MultiPolygonWKTWriter().writeShort(new MultiPolygon())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyMultiPolygonWithHole() {
		assertThat(new MultiPolygonWKTWriter().writeShort(createMultiPolygonWithHole()))
				.isEqualTo("((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), " +
						"(30 20, 20 15, 20 25, 30 20))");
	}

	@Test
	public void shouldWriteShortNonEmptyMultiPolygonWithMultipleHoles() {
		assertThat(new MultiPolygonWKTWriter().writeShort(createMultiPolygonWithMultipleHoles()))
				.isEqualTo("((40 40, 20 45, 45 30, 40 40)), " +
						"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), " +
						"(30 20, 20 15, 20 25, 30 20), (35 25, 25 15, 25 25, 35 25))");
	}

	private MultiPolygon createMultiPolygonWithHole() {
		return new MultiPolygon(
				new Polygon[]{
						new Polygon(
								new LineString(new double[]{40, 40, 20, 45, 45, 30, 40, 40}),
								null
						),
						new Polygon(
								new LineString(new double[]{20, 35, 10, 30, 10, 10, 30, 5, 45, 20, 20, 35}),
								new LineString[]{new LineString(new double[]{30, 20, 20, 15, 20, 25, 30, 20})
								}
						)
				}
		);
	}

	private MultiPolygon createMultiPolygonWithMultipleHoles() {
		return new MultiPolygon(
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
		);
	}
}