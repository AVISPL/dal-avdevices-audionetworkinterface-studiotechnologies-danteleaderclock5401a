/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * SyncInputTerminationMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum SyncInputTerminationMetric {
	ON("On", "1"),
	OFF("Off", "0");

	private final String name;
	private final String value;

	/**
	 * SyncInputTerminationMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 * @param value {@code {@link #value }}
	 */
	SyncInputTerminationMetric(String name, String value) {
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
	 * Get SyncInputTerminationMetric by its code
	 *
	 * @param code value of code in number;
	 * @return SyncInputTerminationMetric
	 */
	public static SyncInputTerminationMetric getByValue(String code) {
		for (SyncInputTerminationMetric syncInputTerminationMetric: SyncInputTerminationMetric.values()
		) {
			if (syncInputTerminationMetric.getValue().equals(code)) {
				return syncInputTerminationMetric;
			}
		}
		// null is well-handled in the communicator class so that it won't cause NPE.
		return null;
	}
}
