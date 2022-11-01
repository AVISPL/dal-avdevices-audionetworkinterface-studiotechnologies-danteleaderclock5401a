/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

/**
 * DanteLeaderClockMonitoringMetrics
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum DanteLeaderClockMonitoringMetrics {
	// MAIN MENU GROUP
	MAIN_CLOCK_SOURCE("MainClockSource"),
	FAILOVER_CLOCK_SOURCE("FailoverClockSource"),
	FORCE_PREFERRED_LEADER("ForcePreferredLeader"),
	CURRENT_CLOCK_SOURCE("CurrentClockSource"),
	PRIMARY_LEADER_CLOCK("PrimaryLeaderClock"),
	PRIMARY_PTPV1_STATE("PrimaryPTPv1State"),
	PRIMARY_PTPV2_STATE("PrimaryPTPv2State"),
	SECONDARY_PTPV1_STATE("SecondaryPTPv1State"),
	SECONDARY_PTPV2_STATE("SecondaryPTPv2State"),
	// SYNC INPUT GROUP
	LOCK_STATUS("LockStatus"),
	SYNC_INPUT_STATUS("SyncInputStatus"),
	SYNC_INPUT_TYPE("SyncInputType"),
	SYNC_INPUT_TERMINATION("SyncInputTermination"),
	CURRENT_DANTE_SAMPLE_RATE("CurrentDanteSampleRate(kHz)"),
	// NETWORK GROUP
	NETWORK_IP_ADDRESS("IPAddress"),
	// SYSTEM GROUP
	SYSTEM_VERSION("Version"),
	SYSTEM_DATE("Date")
	;
	// NO GROUP

	private final String propertyName;

	/**
	 * DanteLeaderClockMonitoringMetrics constructor
	 *
	 * @param propertyName {@code {@link #propertyName}}
	 */
	DanteLeaderClockMonitoringMetrics(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Retrieves {@code {@link #propertyName}}
	 *
	 * @return value of {@link #propertyName}
	 */
	public String getPropertyName() {
		return propertyName;
	}
}
