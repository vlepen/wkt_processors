package com.sinergise.io.reader;

import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointWKTReaderTest {
	@Test
	public void shouldReadEmptyPoint() {
		assertThat(new PointWKTReader().read("EMPTY")).isEqualTo(new Point());
	}

	@Test
	public void shouldReadNonEmptyPoint() {
		assertThat(new PointWKTReader().read("10 20")).isEqualTo(new Point(10, 20));
	}

	@Test
	public void shouldReadNonEmptyPointWithNegativeIntegerCorrds() {
		assertThat(new PointWKTReader().read("-10 -20")).isEqualTo(new Point(-10, -20));
	}

	@Test
	public void shouldReadNonEmptyPointWithDecimalCoords() {
		assertThat(new PointWKTReader().read("10.43 20.2")).isEqualTo(new Point(10.43, 20.2));
	}

	@Test
	public void shouldReadNonEmptyPointWithNegativeDecimalCoords() {
		assertThat(new PointWKTReader().read("-10.43 -20.2")).isEqualTo(new Point(-10.43, -20.2));
	}
}