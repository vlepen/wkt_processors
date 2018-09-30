package com.sinergise.io.printer;

import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointWKTPrinterTest {
	@Test
	public void shouldPrintEmptyPoint() {
		assertThat(new PointWKTPrinter().print(new Point())).isEqualTo("POINT EMPTY");
	}

	@Test
	public void shouldPrintNonEmptyPointWithIntegerCoords() {
		assertThat(new PointWKTPrinter().print(new Point(10, 30))).isEqualTo("POINT (10 30)");
	}

	@Test
	public void shouldPrintNonEmptyPointWithDecimalCoords() {
		assertThat(new PointWKTPrinter().print(new Point(10.43, 30.98))).isEqualTo("POINT (10.43 30.98)");
	}

	@Test
	public void shouldPrintShortEmptyPoint() {
		assertThat(new PointWKTPrinter().printShort(new Point())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldPrintShortNonEmptyPointWithIntegerCoords() {
		assertThat(new PointWKTPrinter().printShort(new Point(10, 30))).isEqualTo("10 30");
	}

	@Test
	public void shouldPrintShortNonEmptyPointWithDecimalCoords() {
		assertThat(new PointWKTPrinter().printShort(new Point(10.43, 30.98))).isEqualTo("10.43 30.98");
	}
}