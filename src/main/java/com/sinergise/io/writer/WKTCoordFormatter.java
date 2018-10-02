package com.sinergise.io.writer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

final class WKTCoordFormatter {
	private static final ThreadLocal<DecimalFormat> FORMATTER =
			ThreadLocal.withInitial(() -> {
				DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
				decimalFormatSymbols.setDecimalSeparator('.');
				return new DecimalFormat("#.###########", decimalFormatSymbols);
			});

	private WKTCoordFormatter() {
	}

	static String format(double coord) {
		return FORMATTER.get().format(coord);
	}
}
