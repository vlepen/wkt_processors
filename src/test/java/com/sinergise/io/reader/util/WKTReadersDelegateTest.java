package com.sinergise.io.reader.util;

import com.sinergise.io.reader.*;
import org.junit.Test;

import static com.sinergise.geometry.GeometryType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WKTReadersDelegateTest {
	@Test
	public void shouldDelegateToPointReaderWhenGeometryIsOfATypePoint() {
		String geometry = "10 20";

		assertThat(WKTReadersDelegate.delegateToReader(POINT, geometry)).isEqualTo(new PointWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToLineStringReaderWhenGeometryIsOfATypeLineString() {
		String geometry = "10 20, 30 40";

		assertThat(WKTReadersDelegate.delegateToReader(LINE_STRING, geometry))
				.isEqualTo(new LineStringWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToPolygonReaderWhenGeometryIsOfATypePolygon() {
		String geometry = "(30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15)";

		assertThat(WKTReadersDelegate.delegateToReader(POLYGON, geometry)).isEqualTo(new PolygonWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToGeometryCollectionReaderWhenGeometryIsOfATypeGeometryCollection() {
		String geometry =
				"POINT (10 20), " +
						"LINESTRING (4 6, 7 10), " +
						"POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (35 15, 45 45, 25 45, 15 25, 35 15))";

		assertThat(WKTReadersDelegate.delegateToReader(GEOMETRY_COLLECTION, geometry))
				.isEqualTo(new GeometryCollectionWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToMultiPointReaderWhenGeometryIsOfATypeMultiPoint() {
		String geometry = "(10 20), (30 40)";

		assertThat(WKTReadersDelegate.delegateToReader(MULTI_POINT, geometry))
				.isEqualTo(new MultiPointWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToMultiLineStringReaderWhenGeometryIsOfATypeMultiLineString() {
		String geometry = "(10 20, 30 40), (50 20, 10 90)";

		assertThat(WKTReadersDelegate.delegateToReader(MULTI_LINE_STRING, geometry))
				.isEqualTo(new MultiLineStringWKTReader().read(geometry));
	}

	@Test
	public void shouldDelegateToMultiPolygonReaderWhenGeometryIsOfATypeMultiPolygon() {
		String geometry = "((40 40, 20 45, 45 30, 40 40)), " +
				"((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20))";

		assertThat(WKTReadersDelegate.delegateToReader(MULTI_POLYGON, geometry))
				.isEqualTo(new MultiPolygonWKTReader().read(geometry));
	}

}