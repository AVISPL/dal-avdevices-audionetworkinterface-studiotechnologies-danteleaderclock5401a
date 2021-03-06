/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

import java.lang.reflect.Field;

/**
 * SyncInputDTO
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/28/2022
 * @since 1.0.0
 */
public class SyncInputDTO {

	private String lockStatus;
	private String syncInputStatus;
	private String syncInputType;
	private String syncInputTermination;
	private String currentDanteSampleRate;

	/**
	 * SyncInputDTO no argument constructor
	 */
	public SyncInputDTO() {
	}

	/**
	 * Retrieves {@code {@link #lockStatus}}
	 *
	 * @return value of {@link #lockStatus}
	 */
	public String getLockStatus() {
		return lockStatus;
	}

	/**
	 * Sets {@code lockStatus}
	 *
	 * @param lockStatus the {@code java.lang.String} field
	 */
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	/**
	 * Retrieves {@code {@link #syncInputStatus }}
	 *
	 * @return value of {@link #syncInputStatus}
	 */
	public String getSyncInputStatus() {
		return syncInputStatus;
	}

	/**
	 * Sets {@code syncInputStatusMetric}
	 *
	 * @param syncInputStatus the {@code java.lang.String} field
	 */
	public void setSyncInputStatus(String syncInputStatus) {
		this.syncInputStatus = syncInputStatus;
	}

	/**
	 * Retrieves {@code {@link #syncInputType}}
	 *
	 * @return value of {@link #syncInputType}
	 */
	public String getSyncInputType() {
		return syncInputType;
	}

	/**
	 * Sets {@code syncInputType}
	 *
	 * @param syncInputType the {@code java.lang.String} field
	 */
	public void setSyncInputType(String syncInputType) {
		this.syncInputType = syncInputType;
	}

	/**
	 * Retrieves {@code {@link #syncInputTermination}}
	 *
	 * @return value of {@link #syncInputTermination}
	 */
	public String getSyncInputTermination() {
		return syncInputTermination;
	}

	/**
	 * Sets {@code syncInputTermination}
	 *
	 * @param syncInputTermination the {@code java.lang.String} field
	 */
	public void setSyncInputTermination(String syncInputTermination) {
		this.syncInputTermination = syncInputTermination;
	}

	/**
	 * Retrieves {@code {@link #currentDanteSampleRate}}
	 *
	 * @return value of {@link #currentDanteSampleRate}
	 */
	public String getCurrentDanteSampleRate() {
		return currentDanteSampleRate;
	}

	/**
	 * Sets {@code currentDanteSampleRate}
	 *
	 * @param currentDanteSampleRate the {@code java.lang.String} field
	 */
	public void setCurrentDanteSampleRate(String currentDanteSampleRate) {
		this.currentDanteSampleRate = currentDanteSampleRate;
	}

	/**
	 * Check if every field in DTO are None
	 *
	 * @return true if every field are None, else false
	 */
	public boolean isAllNone() {
		for (Field f : getClass().getDeclaredFields()) {
			try {
				if (f.get(this) != DanteLeaderClockConstant.NONE)
					return false;
			} catch (IllegalAccessException e) {
				return false;
			}
		}
		return true;
	}
}
