package com.sinergise.io.writer.util;

import com.sinergise.geometry.*;
import com.sinergise.io.writer.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WKTWritersDelegateTest {
	@Test
	public void shouldDelegateToPointWriterWhenGeometryIsOfATypePoint() {
		Point geometry = new Point(10, 20);

		assertThat(WKTWritersDelegate.delegateToWriter(geometry)).isEqualTo(new PointWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToLineStringWriterWhenGeometryIsOfATypeLineString() {
		LineString geometry = new LineString(new double[]{10, 20, 30, 40});

		assertThat(WKTWritersDelegate.delegateToWriter(geometry)).isEqualTo(new LineStringWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToPolygonWriterWhenGeometryIsOfATypePolygon() {
		Polygon geometry = new Polygon(
				new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
				new LineString[]{new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})}
		);

		assertThat(WKTWritersDelegate.delegateToWriter(geometry)).isEqualTo(new PolygonWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToGeometryCollectionWriterWhenGeometryIsOfATypeGeometryCollection() {
		GeometryCollection geometry = new GeometryCollection<>(
				new Geometry[]{
						new Point(10, 20),
						new LineString(new double[]{4, 6, 7, 10}),
						new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
								new LineString[]{
										new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})
								}
						)
				}
		);

		assertThat(WKTWritersDelegate.delegateToWriter(geometry))
				.isEqualTo(new GeometryCollectionWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToMultiPointWriterWhenGeometryIsOfATypeMultiPoint() {
		MultiPoint geometry = new MultiPoint(new Point[]{new Point(10, 20), new Point(30, 40)});

		assertThat(WKTWritersDelegate.delegateToWriter(geometry)).isEqualTo(new MultiPointWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToMultiLineStringWriterWhenGeometryIsOfATypeMultiLineString() {
		MultiLineString geometry = new MultiLineString(new LineString[]{
				new LineString(new double[]{10, 20, 30, 40}),
				new LineString(new double[]{50, 20, 10, 90})
		});

		assertThat(WKTWritersDelegate.delegateToWriter(geometry))
				.isEqualTo(new MultiLineStringWKTWriter().write(geometry));
	}

	@Test
	public void shouldDelegateToMultiPolygonWriterWhenGeometryIsOfATypeMultiPolygon() {
		MultiPolygon geometry = new MultiPolygon(
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

		assertThat(WKTWritersDelegate.delegateToWriter(geometry)).isEqualTo(new MultiPolygonWKTWriter().write(geometry));
	}
}