package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiLineStringWKTWriterTest {
	@Test
	public void shouldWriteEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTWriter().write(new MultiLineString())).isEqualTo("MULTILINESTRING EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTWriter().write(createMultiLineString()))
				.isEqualTo("MULTILINESTRING ((10 10, 20 20, 10 40), (40.456 40, 30 30, 40.21 20, 30 10))");
	}

	@Test
	public void shouldWriteShortEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTWriter().writeShort(new MultiLineString())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTWriter().writeShort(createMultiLineString()))
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