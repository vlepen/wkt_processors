package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sinergise.io.common.WKTConstants.EMPTY;
import static java.lang.ThreadLocal.withInitial;

public class MultiLineStringWKTReader implements GeometryWKTReader<MultiLineString> {
	private static final String WKT_REGEX = "\\((?<lineString>[-\\d\\. ,]+)\\)";
	private static final ThreadLocal<LineStringWKTReader> LINE_STRING_READER = withInitial(LineStringWKTReader::new);

	@Override
	public MultiLineString read(String wktString) {
		if (EMPTY.equals(wktString)) {
			return new MultiLineString();
		}
		Pattern pattern = Pattern.compile(WKT_REGEX);
		Matcher matcher = pattern.matcher(wktString);
		List<LineString> lineStrings = new ArrayList<>();
		while (matcher.find()) {
			String lineString = matcher.group("lineString");
			if (lineString != null) {
				lineStrings.add(LINE_STRING_READER.get().read(lineString));
			}
		}
		return new MultiLineString(lineStrings.toArray(new LineString[0]));
	}
}
