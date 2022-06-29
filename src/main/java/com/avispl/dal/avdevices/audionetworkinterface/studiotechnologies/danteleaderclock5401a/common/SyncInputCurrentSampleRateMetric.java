package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

public enum SyncInputCurrentSampleRateMetric {
	RATE_44_1_KHZ("44.1 kHz"),
	RATE_48_KHZ("48 kHz"),
	RATE_88_2_KHZ("88.2 kHz"),
	RATE_96_KHZ("96 kHz"),
	RATE_176_4_KHZ("176.4 kHz"),
	RATE_192_KHZ("192 kHz"),
	ERROR("Error");
	private final String name;

	SyncInputCurrentSampleRateMetric(String name) {
		this.name = name;
	}

	/**
	 * Retrieves {@code {@link #name}}
	 *
	 * @return value of {@link #name}
	 */
	public String getName() {
		return name;
	}
}
