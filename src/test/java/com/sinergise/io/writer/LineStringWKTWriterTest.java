package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineStringWKTWriterTest {
	@Test
	public void shouldWriteEmptyLineString() {
		assertThat(new LineStringWKTWriter().write(new LineString())).isEqualTo("LINESTRING EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyLineStringWithIntegerCoords() {
		assertThat(new LineStringWKTWriter().write(new LineString(new double[]{4, 6, 7, 10})))
				.isEqualTo("LINESTRING (4 6, 7 10)");
	}

	@Test
	public void shouldWriteNonEmptyLineStringWithDecimalCoords() {
		assertThat(new LineStringWKTWriter().write(new LineString(new double[]{4.43, 6.12, 7, 10.65})))
				.isEqualTo("LINESTRING (4.43 6.12, 7 10.65)");
	}

	@Test
	public void shouldWriteShortEmptyLineString() {
		assertThat(new LineStringWKTWriter().writeShort(new LineString())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyLineStringWithIntegerCoords() {
		assertThat(new LineStringWKTWriter().writeShort(new LineString(new double[]{4, 6, 7, 10})))
				.isEqualTo("4 6, 7 10");
	}

	@Test
	public void shouldWriteShortNonEmptyLineStringWithDecimalCoords() {
		assertThat(new LineStringWKTWriter().writeShort(new LineString(new double[]{4.43, 6.12, 7, 10.65})))
				.isEqualTo("4.43 6.12, 7 10.65");
	}
}