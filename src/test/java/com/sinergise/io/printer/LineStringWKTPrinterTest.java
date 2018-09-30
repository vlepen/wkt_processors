package com.sinergise.io.printer;

import com.sinergise.geometry.LineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineStringWKTPrinterTest {
	@Test
	public void shouldPrintEmptyLineString() {
		assertThat(new LineStringWKTPrinter().print(new LineString())).isEqualTo("LINESTRING EMPTY");
	}

	@Test
	public void shouldPrintNonEmptyLineStringWithIntegerCoords() {
		assertThat(new LineStringWKTPrinter().print(new LineString(new double[]{4, 6, 7, 10})))
				.isEqualTo("LINESTRING (4 6, 7 10)");
	}

	@Test
	public void shouldPrintNonEmptyLineStringWithDecimalCoords() {
		assertThat(new LineStringWKTPrinter().print(new LineString(new double[]{4.43, 6.12, 7, 10.65})))
				.isEqualTo("LINESTRING (4.43 6.12, 7 10.65)");
	}

	@Test
	public void shouldPrintShortEmptyLineString() {
		assertThat(new LineStringWKTPrinter().printShort(new LineString())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldPrintShortNonEmptyLineStringWithIntegerCoords() {
		assertThat(new LineStringWKTPrinter().printShort(new LineString(new double[]{4, 6, 7, 10})))
				.isEqualTo("4 6, 7 10");
	}

	@Test
	public void shouldPrintShortNonEmptyLineStringWithDecimalCoords() {
		assertThat(new LineStringWKTPrinter().printShort(new LineString(new double[]{4.43, 6.12, 7, 10.65})))
				.isEqualTo("4.43 6.12, 7 10.65");
	}
}