/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

/**
 * PTPMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/29/2022
 * @since 1.0.0
 */
public enum PTPMetric {
	DISABLED("Disabled"),
	LEADER("Leader"),
	FOLLOWER("Follower"),
	LINK_DOWN("Link Down"),
	ERROR("Error");

	private final String name;

	/**
	 * PTPMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 */
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
