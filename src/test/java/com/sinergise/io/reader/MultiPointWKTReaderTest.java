package com.sinergise.io.reader;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPointWKTReaderTest {
	@Test
	public void shouldReadEmptyMultiPoint() {
		assertThat(new MultiPointWKTReader().read("EMPTY")).isEqualTo(new MultiPoint());
	}

	@Test
	public void shouldReadNonEmptyMultiPoint() {
		assertThat(new MultiPointWKTReader().read("(10 20), (-3.4 54.3), (221.3 -0.5)"))
				.isEqualTo(new MultiPoint(new Point[]{
						new Point(10, 20),
						new Point(-3.4, 54.3),
						new Point(221.3, -0.5)
				}));
	}
}