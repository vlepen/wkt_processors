package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineStringWKTReaderTest {
	@Test
	public void shouldReadEmptyLineString() {
		assertThat(new LineStringWKTReader().read("EMPTY")).isEqualTo(new LineString());
	}

	@Test
	public void shouldReadNonEmptyLineStringWithIntegerCoords() {
		assertThat(new LineStringWKTReader().read("4 6, 7 10"))
				.isEqualTo(new LineString(new double[]{4, 6, 7, 10}));
	}

	@Test
	public void shouldReadNonEmptyLineStringWithNegativeIntegerCoords() {
		assertThat(new LineStringWKTReader().read("-4 -6, -7 -10"))
				.isEqualTo(new LineString(new double[]{-4, -6, -7, -10}));
	}

	@Test
	public void shouldReadNonEmptyLineStringWithDecimalCoords() {
		assertThat(new LineStringWKTReader().read("4.43 6.12, 7 10.65"))
				.isEqualTo(new LineString(new double[]{4.43, 6.12, 7, 10.65}));
	}

	@Test
	public void shouldReadNonEmptyLineStringWithNegativeDecimalCoords() {
		assertThat(new LineStringWKTReader().read("-4.43 -6.12, -7 -10.65"))
				.isEqualTo(new LineString(new double[]{-4.43, -6.12, -7, -10.65}));
	}
}