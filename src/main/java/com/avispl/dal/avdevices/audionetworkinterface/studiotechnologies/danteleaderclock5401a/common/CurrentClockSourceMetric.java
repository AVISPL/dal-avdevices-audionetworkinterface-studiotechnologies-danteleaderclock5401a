/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * CurrentClockSourceMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum CurrentClockSourceMetric {
	INTERNAL("Internal", "0"),
	DANTE("Dante", "1"),
	SYNC_INPUT("SyncInput", "2");

	private final String name;
	private final String value;

	/**
	 * CurrentClockSourceMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 * @param value {@code {@link #value }}
	 */
	CurrentClockSourceMetric(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Retrieves {@code {@link #name}}
	 *
	 * @return value of {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves {@code {@link #value}}
	 *
	 * @return value of {@link #value}
	 */
	public String getValue() {
		return value;
	}
}
