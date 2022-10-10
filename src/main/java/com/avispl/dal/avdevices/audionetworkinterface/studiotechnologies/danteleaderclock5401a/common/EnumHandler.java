/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common;

import java.lang.reflect.Method;

import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.DanteLeaderClockConstant;
import com.avispl.symphony.dal.util.StringUtils;

/**
 * Enum Handler class
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/30/2022
 * @since 1.0.0
 */
public class EnumHandler {

	/**
	 * Method to check if input name is valid, if it is then return the inputName, else return None
	 *
	 * @param enumType the type of enum class
	 * @return Valid inputName
	 */
	public static <T extends Enum<T>> String populateNoneIfNotValidName(String inputName, Class<T> enumType) {
		if (!StringUtils.isNullOrEmpty(inputName)) {
			for (T c : enumType.getEnumConstants()) {
				try {
					Method method = c.getClass().getMethod("getName");
					String name = (String) method.invoke(c); // getName executed
					if (inputName.contains(name)) {
						return inputName;
					}
				} catch (Exception e) {
					return DanteLeaderClockConstant.NONE;
				}
			}
		}
		return DanteLeaderClockConstant.NONE;
	}
}
