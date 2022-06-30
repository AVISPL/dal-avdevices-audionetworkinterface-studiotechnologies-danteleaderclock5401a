/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * SyncInputStatusMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/29/2022
 * @since 1.0.0
 */
public enum SyncInputStatusMetric {
	LOCKED_ACTIVE("Locked (Active)"), LOCKED_STANDBY("Locked (Standby)"), UNLOCKED("Unlocked"), IDLE("Idle");

	private final String name;

	/**
	 * SyncInputStatusMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 */
	SyncInputStatusMetric(String name) {
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
