package com.sinergise.io;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import org.junit.Test;

import static com.sinergise.io.writer.util.WKTWritersDelegate.delegateToWriter;
import static org.assertj.core.api.Assertions.assertThat;

public class WKTWriterTest {
	@Test
	public void shouldWriteGeometryByDelegatingToWritersDelegate() {
		GeometryCollection<Geometry> geometry = new GeometryCollection<>(new Geometry[]{
				new Point(4, 6),
				new LineString(new double[]{4, 6, 7, 10})
		});

		assertThat(new WKTWriter().write(geometry)).isEqualTo(delegateToWriter(geometry));
	}

	@Test
	public void shouldWriteGeometry() {
		assertThat(new WKTWriter().write(new GeometryCollection<>(new Geometry[]{
				new Point(4, 6),
				new LineString(new double[]{4, 6, 7, 10})
		})))
				.isEqualTo("GEOMETRYCOLLECTION (POINT (4 6), LINESTRING (4 6, 7 10))");
	}
}