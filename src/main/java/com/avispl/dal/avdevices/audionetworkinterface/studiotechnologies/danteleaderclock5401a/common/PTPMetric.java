package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

public enum PTPMetric {
	DISABLED("Disabled"),
	LEADER("Leader"),
	FOLLOWER("Follower"),
	LINK_DOWN("Link Down"),
	ERROR("Error");

	private final String name;

	PTPMetric(String name) {
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
