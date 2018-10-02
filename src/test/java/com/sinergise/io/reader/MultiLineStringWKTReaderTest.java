package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiLineStringWKTReaderTest {
	@Test
	public void shouldReadEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTReader().read("EMPTY")).isEqualTo(new MultiLineString());
	}

	@Test
	public void shouldReadNonEmptyMultiLineString() {
		assertThat(new MultiLineStringWKTReader().read("(-4 6, 7.4 -8.2), (10 3.22, 5.4 32.1), (-33.2 2)"))
				.isEqualTo(new MultiLineString(
						new LineString[]{
								new LineString(new double[]{-4, 6, 7.4, -8.2}),
								new LineString(new double[]{10, 3.22, 5.4, 32.1}),
								new LineString(new double[]{-33.2, 2})
						}
				));
	}
}