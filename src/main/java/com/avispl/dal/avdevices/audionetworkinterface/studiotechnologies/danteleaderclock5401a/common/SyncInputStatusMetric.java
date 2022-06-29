package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

public enum SyncInputStatusMetric {
	LOCKED_ACTIVE("Locked (Active)"), LOCKED_STANDBY("Locked (Standby)"), UNLOCKED("Unlocked"), IDLE("Idle");

	private final String name;

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
