package com.sinergise.io.writer;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPointWKTWriterTest {
	@Test
	public void shouldWriteEmptyMultiPoint() {
		assertThat(new MultiPointWKTWriter().write(new MultiPoint())).isEqualTo("MULTIPOINT EMPTY");
	}

	@Test
	public void shouldWriteNonEmptyMultiPoint() {
		assertThat(new MultiPointWKTWriter().write(createMultiPoint()))
				.isEqualTo("MULTIPOINT ((4 6), (7.4 8.2), (10 3.22))");
	}

	@Test
	public void shouldWriteShortEmptyMultiPoint() {
		assertThat(new MultiPointWKTWriter().writeShort(new MultiPoint())).isEqualTo("EMPTY");
	}

	@Test
	public void shouldWriteShortNonEmptyMultiPoint() {
		assertThat(new MultiPointWKTWriter().writeShort(createMultiPoint()))
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