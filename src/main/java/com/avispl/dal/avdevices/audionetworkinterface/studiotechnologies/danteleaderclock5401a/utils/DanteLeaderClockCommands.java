/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

/**
 * DanteLeaderClockCommands
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum DanteLeaderClockCommands {
	GET_LOGIN_COMMAND("UNPW.cgi",""),
	GET_LOGOUT_COMMAND("LogOut.cgi",""),
	GET_GENERAL_COMMAND("main.shtml","General"),
	GET_SYNC_INPUT_COMMAND("sync.shtml", "SyncInput"),
	GET_TONE_GENERATOR_COMMAND("gen.shtml", "ToneGenerator"),
	GET_NETWORK_COMMAND("net.shtml", ""),
	GET_SYSTEM_COMMAND("sys.shtml","");

	private final String command;
	private final String groupName;

	/**
	 * DanteLeaderClockCommands constructor
	 *
	 * @param command {@code {@link #command}}
	 * @param groupName {@code {@link #groupName}}
	 */
	DanteLeaderClockCommands(String command, String groupName) {
		this.command = command;
		this.groupName = groupName;
	}

	/**
	 * Retrieves {@code {@link #command}}
	 *
	 * @return value of {@link #command}
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Retrieves {@code {@link #groupName}}
	 *
	 * @return value of {@link #groupName}
	 */
	public String getGroupName() {
		return groupName;
	}
}
