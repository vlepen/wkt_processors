package com.sinergise.io.printer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiLineStringWKTPrinterTest {
	@Test
	public void shouldPrintEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTPrinter().print(new MultiLineString())).isEqualTo("MULTILINESTRING EMPTY");
	}

	@Test
	public void shouldPrintNonEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTPrinter().print(createMultiLineString()))
				.isEqualTo("MULTILINESTRING ((10 10, 20 20, 10 40), (40.456 40, 30 30, 40.21 20, 30 10))");
	}

	@Test
	public void shouldPrintShortEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTPrinter().printShort(new MultiLineString())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldPrintShortNonEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTPrinter().printShort(createMultiLineString()))
				.isEqualTo("(10 10, 20 20, 10 40), (40.456 40, 30 30, 40.21 20, 30 10)");
	}

	private MultiLineString createMultiLineString() {
		return new MultiLineString(
				new LineString[]{
						new LineString(new double[]{10, 10, 20, 20, 10, 40}),
						new LineString(new double[]{40.456, 40, 30, 30, 40.21, 20, 30, 10})
				}
		);
	}
}