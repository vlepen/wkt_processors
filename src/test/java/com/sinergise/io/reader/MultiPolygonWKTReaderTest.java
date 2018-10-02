package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPolygonWKTReaderTest {
	@Test
	public void shouldReadEmptyMultiPolygon() {
		assertThat(new MultiPolygonWKTReader().read("EMPTY")).isEqualTo(new MultiPolygon());
	}

	@Test
	public void shouldReadNonEmptyMultiPolygon() {
		assertThat(new MultiPolygonWKTReader().read(
				"((40 40, 20 45, 45 30, 40 40)), " +
						"(" +
						"(20 35, 10 30, 10 10, 30 5, 45 20, 20 35), " +
						"(30 20, 20 15, 20 25, 30 20), " +
						"(35 25, 25 15, 25 25, 35 25)" +
						"), " +
						"((10 110, 110 10, 110 110, 10 110), (20 20, 20 30, 30 30, 20 20), (40 20, 40 30, 50 30, 40 20))"
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
								),
								new Polygon(
										new LineString(new double[]{10, 110, 110, 10, 110, 110, 10, 110}),
										new LineString[]{
												new LineString(new double[]{20, 20, 20, 30, 30, 30, 20, 20}),
												new LineString(new double[]{40, 20, 40, 30, 50, 30, 40, 20})
										}
								)
						}
				));
	}
}