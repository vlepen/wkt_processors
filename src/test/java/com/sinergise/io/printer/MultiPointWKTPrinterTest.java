package com.sinergise.io.printer;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPointWKTPrinterTest {
	@Test
	public void shouldPrintEmptyMultiPoint() {
		assertThat(new MultiPointWKTPrinter().print(new MultiPoint())).isEqualTo("MULTIPOINT EMPTY");
	}

	@Test
	public void shouldPrintNonEmptyMultiPoint() {
		assertThat(new MultiPointWKTPrinter().print(createMultiPoint()))
				.isEqualTo("MULTIPOINT ((4 6), (7.4 8.2), (10 3.22))");
	}

	@Test
	public void shouldPrintShortEmptyMultiPoint() {
		assertThat(new MultiPointWKTPrinter().printShort(new MultiPoint())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldPrintShortNonEmptyMultiPoint() {
		assertThat(new MultiPointWKTPrinter().printShort(createMultiPoint()))
				.isEqualTo("(4 6), (7.4 8.2), (10 3.22)");
	}

	private MultiPoint createMultiPoint() {
		return new MultiPoint(
				new Point[]{
						new Point(4, 6),
						new Point(7.4, 8.2),
						new Point(10, 3.22)
				}
		);
	}
}