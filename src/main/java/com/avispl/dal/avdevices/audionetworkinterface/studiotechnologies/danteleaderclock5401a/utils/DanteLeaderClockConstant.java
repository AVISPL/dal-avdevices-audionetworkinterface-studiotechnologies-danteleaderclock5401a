/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

/**
 * DanteLeaderClockConstant
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public class DanteLeaderClockConstant {

	public static final String GROUP_PROPERTY_NAME = "%s#%s";
	public static final String EMPTY = "";
	public static final String SPACE_REGEX = "\\s";
	public static final String CLASS_TAG_VAL = "val";
	public static final String ATTRIBUTE_VALUE = "value";
	public static final String SELECTED_OPTION = "select option[selected]";
	public static final String FORM_TAG = "form";
	public static final String P_TAG = "p";
	public static final String SPAN_TAG = "span";
	public static final String TR_TAG = "tr";
	public static final String TD_TAG = "td";
	public static final String ACTION_ATTRIBUTE = "action";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String SERIAL_NUMBER = "SerialNumber";
	public static final String DANTE_PRIMARY = "DantePrimary";
	public static final String DANTE_SECONDARY = "DanteSecondary";
	public static final String MANAGEMENT = "Management";
	public static final String TWO_STRINGS_FORMAT = "%s%s";
	public static final String TONE = "Tone";
	public static final String FREQUENCY = "Frequency";
	public static final String LEVEL = "Level";
	public static final String LOGIN_PAYLOADS = "usn=%s&usp=%s&login=Log+In";
	public static final String LOGIN_FAILED = "Login Failed";
	public static final String STATE = "State";
	public static final String ENABLED = "Enabled";
	public static final String DISABLED = "Disabled";
	public static final String SYNC_INPUT_TYPE_PAYLOAD = "sIT";
	public static final String SYNC_INPUT_TERMINATION_PAYLOAD = "sIZ";
	public static final String MAIN_CLOCK_SOURCE_PAYLOAD = "sCS";
	public static final String FAIL_OVER_CLOCK_SOURCE_PAYLOAD = "sLS";
	public static final String FORCE_PREFERRED_LEADER_PAYLOAD = "sFM";
	public static final int NO_OF_MONITORING_METRICS = 5;
	public static final String NONE = "None";
	public static final String SYSTEM = "System";
	public static final String NETWORK = "Network";
	public static final String TONE_GENERATOR = "ToneGenerator";
	public static final String NORMALIZED_TONE_FREQUENCY = "(Hz)";
	public static final String NORMALIZED_TONE_LEVEL = "(dBFS)";
	public static final String SYNC_INPUT = "SyncInput";
	public static final String GENERAL = "General";
	public static final String SPACE = " ";
	public static final String IS_MAC_ADDRESS_REGEX =
			"^([0-9A-Fa-f]{2}[:-])"
			+ "{5}([0-9A-Fa-f]{2})|"
			+ "([0-9a-fA-F]{4}\\."
			+ "[0-9a-fA-F]{4}\\."
			+ "[0-9a-fA-F]{4})$";
	public static final String THIS_DEVICE = "This Device";
	public static final String OTHER_DEVICE = "Other Device";
	public static final String FAIL_POPULATE_ERROR_MESSAGE = "Fail to populate statistics for";
	public static final String FAIL_TO_GET_MONITORING_DATA = "Fail to get monitoring data: ";
	public static final String LOGIN_FAILED_ALREADY_LOGGED_IN = "A user is already logged in.";
	public static final String NEXT_POLLING_INTERVAL = "NextPollingInterval";
	public static final String SLASH = "/";
}
