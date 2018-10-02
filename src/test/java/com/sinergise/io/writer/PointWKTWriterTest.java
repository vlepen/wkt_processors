package com.sinergise.io.writer;

import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointWKTWriterTest {
	@Test
	public void shouldWriteEmptyPoint() {
		assertThat(new PointWKTWriter().write(new Point())).isEqualTo("POINT EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyPointWithIntegerCoords() {
		assertThat(new PointWKTWriter().write(new Point(10, 30))).isEqualTo("POINT (10 30)");
	}

	@Test
	public void shouldWriteNonEmptyPointWithDecimalCoords() {
		assertThat(new PointWKTWriter().write(new Point(10.43, 30.98))).isEqualTo("POINT (10.43 30.98)");
	}

	@Test
	public void shouldWriteShortEmptyPoint() {
		assertThat(new PointWKTWriter().writeShort(new Point())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyPointWithIntegerCoords() {
		assertThat(new PointWKTWriter().writeShort(new Point(10, 30))).isEqualTo("10 30");
	}

	@Test
	public void shouldWriteShortNonEmptyPointWithDecimalCoords() {
		assertThat(new PointWKTWriter().writeShort(new Point(10.43, 30.98))).isEqualTo("10.43 30.98");
	}
}