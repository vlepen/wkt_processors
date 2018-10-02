package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PolygonWKTReaderTest {
	@Test
	public void shouldReadEmptyPolygon() {
		assertThat(new PolygonWKTReader().read("EMPTY")).isEqualTo(new Polygon());
	}

	@Test
	public void shouldReadNonEmptyPolygonWithIntegerCoordsAndNoHoles() {
		assertThat(new PolygonWKTReader().read("(30 10, 40 40, 20 40, 10 20, 30 10)"))
				.isEqualTo(new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), null));
	}

	@Test
	public void shouldReadNonEmptyPolygonWithDoubleCoordsAndNoHoles() {
		assertThat(new PolygonWKTReader().read("(30.43 -10.4, 40.21 40, 20 40, -10 20, 30.43 -10.4)"))
				.isEqualTo(new Polygon(
						new LineString(new double[]{30.43, -10.4, 40.21, 40, 20, 40, -10, 20, 30.43, -10.4}),
						null
				));
	}

	@Test
	public void shouldReadNonEmptyPolygonWithIntegerCoordsAndHoles() {
		assertThat(new PolygonWKTReader().read(
				"(30 10, 40 40, 20 40, 10 20, 30 10), " +
						"(35 15, 45 45, 25 45, 15 25, 35 15), " +
						"(36 16, 46 46, 26 46, 16 26, 36 16)"
		))
				.isEqualTo(new Polygon(
						new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{
								new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15}),
								new LineString(new double[]{36, 16, 46, 46, 26, 46, 16, 26, 36, 16})
						}
				));
	}
}