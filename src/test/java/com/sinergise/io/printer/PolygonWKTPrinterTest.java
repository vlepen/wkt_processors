package com.sinergise.io.printer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PolygonWKTPrinterTest {
	@Test
	public void shouldPrintEmptyPolygon() {
		assertThat(new PolygonWKTPrinter().print(new Polygon())).isEqualTo("POLYGON EMPTY");
	}

	@Test
	public void shouldPrintNonEmptyPolygonWithNoHoles() {
		assertThat(new PolygonWKTPrinter().print(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), null)
		))
				.isEqualTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))");
	}

	@Test
	public void shouldPrintNonEmptyPolygonWithHole() {
		assertThat(new PolygonWKTPrinter().print(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{
								new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})
						}
				)
		))
				.isEqualTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15))");
	}

	@Test
	public void shouldPrintNonEmptyPolygonWithHoles() {
		assertThat(new PolygonWKTPrinter().print(createPolygon()))
				.isEqualTo(
						"POLYGON (" +
								"(30 10, 40 40, 20 40, 10 20, 30 10), " +
								"(35 15, 45 45, 25 45, 15 25, 35 15), " +
								"(36 16, 46 46, 26 46, 16 26, 36 16)" +
								")"
				);
	}

	@Test
	public void shouldPrintShortEmptyPolygon() {
		assertThat(new PolygonWKTPrinter().printShort(new Polygon())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldPrintShortNonEmptyPolygonWithNoHoles() {
		assertThat(new PolygonWKTPrinter().printShort(
				new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), null)
		))
				.isEqualTo("(30 10, 40 40, 20 40, 10 20, 30 10)");
	}

	@Test
	public void shouldPrintShortNonEmptyPolygonWithHole() {
		assertThat(new PolygonWKTPrinter().printShort(
				new Polygon(
						new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}),
						new LineString[]{new LineString(new double[]{35, 15, 45, 45, 25, 45, 15, 25, 35, 15})}
				)
		))
				.isEqualTo("(30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15)");
	}

	@Test
	public void shouldPrintShortNonEmptyPolygonWithHoles() {
		assertThat(new PolygonWKTPrinter().printShort(createPolygon()))
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