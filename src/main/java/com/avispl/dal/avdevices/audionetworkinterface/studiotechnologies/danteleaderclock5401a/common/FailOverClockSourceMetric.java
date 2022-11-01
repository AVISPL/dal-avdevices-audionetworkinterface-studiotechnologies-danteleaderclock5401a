/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * FailOverClockSourceMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum FailOverClockSourceMetric {
	INTERNAL("Internal", "0"),
	DANTE("Dante", "1");

	private final String name;
	private final String value;

	/**
	 * FailOverClockSourceMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 * @param value {@code {@link #value }}
	 */
	FailOverClockSourceMetric(String name, String value) {
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
	 * Get FailOverClockSourceMetric by its sourceValue
	 *
	 * @param sourceValue value of source in number;
	 * @return FailOverClockSourceMetric
	 */
	public static FailOverClockSourceMetric getByValue(String sourceValue) {
		for (FailOverClockSourceMetric source: FailOverClockSourceMetric.values()
		) {
			if (source.getValue().equals(sourceValue)) {
				return source;
			}
		}
		// null is well-handled in the communicator class so that it won't cause NPE.
		return null;
	}
}
