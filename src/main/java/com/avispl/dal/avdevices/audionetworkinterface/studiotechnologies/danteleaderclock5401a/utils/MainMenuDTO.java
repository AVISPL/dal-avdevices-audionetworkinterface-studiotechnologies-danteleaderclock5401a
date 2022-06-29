package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

import java.lang.reflect.Field;

/**
 * MainMenuDTO
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/28/2022
 * @since 1.0.0
 */
public class MainMenuDTO {
	private String currentClockSource;
	private String primaryLeaderClock;
	private String primaryPTPV1;
	private String primaryPTPV2;
	private String secondaryPTPV1;
	private String secondaryPTPV2;
	private String mainClockSource;
	private String failOverClockSource;
	private String forcePreferredLeader;

	/**
	 * MainMenuDTO no argument constructor
	 */
	public MainMenuDTO() {
	}

	/**
	 * Retrieves {@code {@link #currentClockSource}}
	 *
	 * @return value of {@link #currentClockSource}
	 */
	public String getCurrentClockSource() {
		return currentClockSource;
	}

	/**
	 * Sets {@code currentClockSource}
	 *
	 * @param currentClockSource the {@code java.lang.String} field
	 */
	public void setCurrentClockSource(String currentClockSource) {
		this.currentClockSource = currentClockSource;
	}

	/**
	 * Retrieves {@code {@link #primaryLeaderClock}}
	 *
	 * @return value of {@link #primaryLeaderClock}
	 */
	public String getPrimaryLeaderClock() {
		return primaryLeaderClock;
	}

	/**
	 * Sets {@code primaryLeaderClock}
	 *
	 * @param primaryLeaderClock the {@code java.lang.String} field
	 */
	public void setPrimaryLeaderClock(String primaryLeaderClock) {
		this.primaryLeaderClock = primaryLeaderClock;
	}

	/**
	 * Retrieves {@code {@link #primaryPTPV1}}
	 *
	 * @return value of {@link #primaryPTPV1}
	 */
	public String getPrimaryPTPV1() {
		return primaryPTPV1;
	}

	/**
	 * Sets {@code primaryPTPV1}
	 *
	 * @param primaryPTPV1 the {@code java.lang.String} field
	 */
	public void setPrimaryPTPV1(String primaryPTPV1) {
		this.primaryPTPV1 = primaryPTPV1;
	}

	/**
	 * Retrieves {@code {@link #primaryPTPV2}}
	 *
	 * @return value of {@link #primaryPTPV2}
	 */
	public String getPrimaryPTPV2() {
		return primaryPTPV2;
	}

	/**
	 * Sets {@code primaryPTPV2}
	 *
	 * @param primaryPTPV2 the {@code java.lang.String} field
	 */
	public void setPrimaryPTPV2(String primaryPTPV2) {
		this.primaryPTPV2 = primaryPTPV2;
	}

	/**
	 * Retrieves {@code {@link #secondaryPTPV1}}
	 *
	 * @return value of {@link #secondaryPTPV1}
	 */
	public String getSecondaryPTPV1() {
		return secondaryPTPV1;
	}

	/**
	 * Sets {@code secondaryPTPV1}
	 *
	 * @param secondaryPTPV1 the {@code java.lang.String} field
	 */
	public void setSecondaryPTPV1(String secondaryPTPV1) {
		this.secondaryPTPV1 = secondaryPTPV1;
	}

	/**
	 * Retrieves {@code {@link #secondaryPTPV2}}
	 *
	 * @return value of {@link #secondaryPTPV2}
	 */
	public String getSecondaryPTPV2() {
		return secondaryPTPV2;
	}

	/**
	 * Sets {@code secondaryPTPV2}
	 *
	 * @param secondaryPTPV2 the {@code java.lang.String} field
	 */
	public void setSecondaryPTPV2(String secondaryPTPV2) {
		this.secondaryPTPV2 = secondaryPTPV2;
	}

	/**
	 * Retrieves {@code {@link #mainClockSource}}
	 *
	 * @return value of {@link #mainClockSource}
	 */
	public String getMainClockSource() {
		return mainClockSource;
	}

	/**
	 * Sets {@code mainClockSource}
	 *
	 * @param mainClockSource the {@code java.lang.String} field
	 */
	public void setMainClockSource(String mainClockSource) {
		this.mainClockSource = mainClockSource;
	}

	/**
	 * Retrieves {@code {@link #failOverClockSource}}
	 *
	 * @return value of {@link #failOverClockSource}
	 */
	public String getFailOverClockSource() {
		return failOverClockSource;
	}

	/**
	 * Sets {@code failOverClockSource}
	 *
	 * @param failOverClockSource the {@code java.lang.String} field
	 */
	public void setFailOverClockSource(String failOverClockSource) {
		this.failOverClockSource = failOverClockSource;
	}

	/**
	 * Retrieves {@code {@link #forcePreferredLeader}}
	 *
	 * @return value of {@link #forcePreferredLeader}
	 */
	public String getForcePreferredLeader() {
		return forcePreferredLeader;
	}

	/**
	 * Sets {@code forcePreferredLeader}
	 *
	 * @param forcePreferredLeader the {@code java.lang.String} field
	 */
	public void setForcePreferredLeader(String forcePreferredLeader) {
		this.forcePreferredLeader = forcePreferredLeader;
	}

	public boolean isAllNone() {
		for (Field f : getClass().getDeclaredFields()) {
			try {
				if (f.get(this) != "None")
					return false;
			} catch (IllegalAccessException e) {
				return false;
			}
		}
		return true;
	}
}
