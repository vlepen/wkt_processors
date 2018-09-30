package com.sinergise.io.printer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

final class WKTCoordPrinter {
	private static final ThreadLocal<DecimalFormat> FORMATTER =
			ThreadLocal.withInitial(() -> {
				DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
				decimalFormatSymbols.setDecimalSeparator('.');
				return new DecimalFormat("#.###########", decimalFormatSymbols);
			});

	private WKTCoordPrinter() {
	}

	static String print(double coord) {
		return FORMATTER.get().format(coord);
	}
}
