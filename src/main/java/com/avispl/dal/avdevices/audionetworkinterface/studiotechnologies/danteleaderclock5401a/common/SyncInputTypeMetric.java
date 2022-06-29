/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

import com.avispl.symphony.api.dal.error.ResourceNotReachableException;

/**
 * SyncInputTypeMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum SyncInputTypeMetric {
	WORD_CLOCK("Word Clock", "0"),
	VIDEO("Video", "1"),
	TEN_MHZ("10 MHz", "2");

	private final String name;
	private final String value;

	/**
	 * SyncInputTypeMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 * @param value {@code {@link #value }}
	 */
	SyncInputTypeMetric(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Retrieves {@code {@link #name }}
	 *
	 * @return value of {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves {@code {@link #value }}
	 *
	 * @return value of {@link #value}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Get SyncInputTypeMetric by its code
	 *
	 * @param code value of code in number;
	 * @return SyncInputTypeMetric
	 * @throws ResourceNotReachableException when fail to get SyncInputTypeMetric by code.
	 */
	public static SyncInputTypeMetric getByValue(String code) {
		for (SyncInputTypeMetric syncInputTypeMetric: SyncInputTypeMetric.values()
		) {
			if (syncInputTypeMetric.getValue().equals(code)) {
				return syncInputTypeMetric;
			}
		}
		return null;
	}
}
