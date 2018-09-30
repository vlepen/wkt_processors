package com.sinergise.io.printer.util;

import com.sinergise.geometry.*;
import com.sinergise.io.printer.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WKTPrintersDelegateTest {
	@Test
	public void shouldDelegateToPointPrinterWhenGeometryIsOfATypePoint() {
		Point geometry = new Point(10, 20);

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry)).isEqualTo(new PointWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToLineStringPrinterWhenGeometryIsOfATypeLineString() {
		LineString geometry = new LineString(new double[]{10, 20, 30, 40});

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry)).isEqualTo(new LineStringWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToPolygonPrinterWhenGeometryIsOfATypePolygon() {
		Polygon geometry = new Polygon(
				new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
				new LineString[]{new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})}
		);

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry)).isEqualTo(new PolygonWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToGeometryCollectionPrinterWhenGeometryIsOfATypeGeometryCollection() {
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

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry))
				.isEqualTo(new GeometryCollectionWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToMultiPointPrinterWhenGeometryIsOfATypeMultiPoint() {
		MultiPoint geometry = new MultiPoint(new Point[]{new Point(10, 20), new Point(30, 40)});

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry)).isEqualTo(new MultiPointWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToMultiLineStringPrinterWhenGeometryIsOfATypeMultiLineString() {
		MultiLineString geometry = new MultiLineString(new LineString[]{
				new LineString(new double[]{10, 20, 30, 40}),
				new LineString(new double[]{50, 20, 10, 90})
		});

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry))
				.isEqualTo(new MultiLineStringWKTPrinter().print(geometry));
	}

	@Test
	public void shouldDelegateToMultiPolygonPrinterWhenGeometryIsOfATypeMultiPolygon() {
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

		assertThat(WKTPrintersDelegate.delegateToPrinter(geometry)).isEqualTo(new MultiPolygonWKTPrinter().print(geometry));
	}
}