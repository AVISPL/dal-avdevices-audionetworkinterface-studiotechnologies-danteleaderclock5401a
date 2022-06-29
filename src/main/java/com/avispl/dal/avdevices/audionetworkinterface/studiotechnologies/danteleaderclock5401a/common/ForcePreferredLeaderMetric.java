package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

import com.avispl.symphony.api.dal.error.ResourceNotReachableException;

/**
 * ForcePreferredLeaderMetric
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum ForcePreferredLeaderMetric {
	DISABLED("Disabled", "0"),
	ENABLED("Enabled", "1");

	private final String name;
	private final String value;

	/**
	 * ForcePreferredLeaderMetric constructor
	 *
	 * @param name {@code {@link #name }}
	 * @param value {@code {@link #value }}
	 */
	ForcePreferredLeaderMetric(String name, String value) {
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
	 * Get ForcePreferredLeaderMetric by its stateCode
	 *
	 * @param stateCode value of state
	 * @return ForcePreferredLeaderMetric
	 * @throws ResourceNotReachableException when fail to get ForcePreferredLeaderMetric by source value.
	 */
	public static ForcePreferredLeaderMetric getByValue(String stateCode) {
		for (ForcePreferredLeaderMetric state: ForcePreferredLeaderMetric.values()
		) {
			if (state.getValue().equals(stateCode)) {
				return state;
			}
		}
		return null;
	}
}
