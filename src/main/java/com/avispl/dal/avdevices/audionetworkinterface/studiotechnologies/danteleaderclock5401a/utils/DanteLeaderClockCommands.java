package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils;

/**
 * DanteLeaderClockCommands
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/27/2022
 * @since 1.0.0
 */
public enum DanteLeaderClockCommands {
	GET_LOGIN_COMMAND("login.htm",""),
	GET_MAIN_MENU_COMMAND("main.htm","MainMenu"),
	GET_SYNC_INPUT_COMMAND("sync.htm", "SyncInput"),
	GET_TONE_GENERATOR_COMMAND("gen.htm", "ToneGenerator"),
	GET_NETWORK_COMMAND("net.htm", ""),
	GET_SYSTEM_COMMAND("sys.htm","");

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
