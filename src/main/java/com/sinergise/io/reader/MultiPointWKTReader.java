package com.sinergise.io.reader;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;

public class MultiPointWKTReader implements GeometryWKTReader<MultiPoint> {
	private static final String WKT_REGEX = "\\((?<point>[-\\d\\. ,]+)\\)";
	private static final ThreadLocal<PointWKTReader> POINT_READER =
			withInitial(PointWKTReader::new);

	@Override
	public MultiPoint read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new MultiPoint();
		}
		Pattern pattern = Pattern.compile(WKT_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		List<Point> points = new ArrayList<>();
		while (matcher.find()) {
			String point = matcher.group("point");
			if (point != null) {
				points.add(POINT_READER.get().read(point));
			}
		}
		return new MultiPoint(points.toArray(new Point[0]));
	}
}
