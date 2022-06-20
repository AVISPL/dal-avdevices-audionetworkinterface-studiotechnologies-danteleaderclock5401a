/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avispl.symphony.api.dal.control.Controller;
import com.avispl.symphony.api.dal.dto.control.ControllableProperty;
import com.avispl.symphony.api.dal.dto.monitor.ExtendedStatistics;
import com.avispl.symphony.api.dal.dto.monitor.Statistics;
import com.avispl.symphony.api.dal.monitor.Monitorable;
import com.avispl.symphony.api.dal.snmp.SnmpQueryable;

/**
 * DanteWorldClockCommunicator
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/20/2022
 * @since 1.0.0
 */
public class DanteLeaderClockCommunicator extends UDPCommunicator implements Monitorable, Controller, SnmpQueryable {
	@Override
	public List<Statistics> getMultipleStatistics() throws Exception {
		final Map<String, String> stats = new HashMap<>();
		stats.put("Property 1", "Value 1");

		ExtendedStatistics extendedStatistics = new ExtendedStatistics();
		extendedStatistics.setStatistics(stats);
		return Collections.singletonList(extendedStatistics);
	}

	@Override
	public void controlProperty(ControllableProperty controllableProperty) throws Exception {
	//ToDo:
	}

	@Override
	public void controlProperties(List<ControllableProperty> list) throws Exception {
	//ToDo:
	}
}
