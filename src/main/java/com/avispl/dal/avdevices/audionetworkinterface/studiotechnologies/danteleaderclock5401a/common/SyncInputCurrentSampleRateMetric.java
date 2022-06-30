/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * SyncInputCurrentSampleRateMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/29/2022
 * @since 1.0.0
 */
public enum SyncInputCurrentSampleRateMetric {
	RATE_44_1_KHZ("44.1 kHz"),
	RATE_48_KHZ("48 kHz"),
	RATE_88_2_KHZ("88.2 kHz"),
	RATE_96_KHZ("96 kHz"),
	RATE_176_4_KHZ("176.4 kHz"),
	RATE_192_KHZ("192 kHz"),
	ERROR("Error");

	private final String name;

	/**
	 * SyncInputCurrentSampleRateMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 */
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
