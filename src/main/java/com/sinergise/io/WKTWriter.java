package com.sinergise.io;

import com.sinergise.geometry.Geometry;

import static com.sinergise.io.printer.util.WKTPrintersDelegate.delegateToPrinter;

public class WKTWriter {

	/**
	 * Transforms the input Geometry object into WKT-formatted String. e.g.
	 * <pre><code>
	 * new WKTWriter().write(new LineString(new double[]{30, 10, 10, 30, 40, 40}));
	 * //returns "LINESTRING (30 10, 10 30, 40 40)"
	 * </code></pre>
	 */
	public String write(Geometry geom) {
		return delegateToPrinter(geom);
	}
}
