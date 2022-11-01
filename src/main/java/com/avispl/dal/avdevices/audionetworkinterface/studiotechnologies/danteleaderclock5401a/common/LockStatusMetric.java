/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * LockStatusMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 7/4/2022
 * @since 1.0.0
 */
public enum LockStatusMetric {
	UNLOCKED("Unlocked"),
	LOCKED("Locked");
	private final String name;

	LockStatusMetric(String name) {
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
