/*
 * Copyright (c) 2022 AVI-SPL, Inc. All Rights Reserved.
 */
package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.CurrentClockSourceMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.EnumHandler;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.FailOverClockSourceMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.ForcePreferredLeaderMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.MainClockSourceMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.PTPMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.SyncInputCurrentSampleRateMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.SyncInputStatusMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.SyncInputTerminationMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.common.SyncInputTypeMetric;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.DanteLeaderClockCommands;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.DanteLeaderClockConstant;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.DanteLeaderClockMonitoringMetrics;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.MainMenuDTO;
import com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a.utils.SyncInputDTO;
import com.avispl.symphony.api.dal.dto.monitor.ExtendedStatistics;
import com.avispl.symphony.api.dal.dto.monitor.Statistics;
import com.avispl.symphony.api.dal.error.ResourceNotReachableException;
import com.avispl.symphony.api.dal.monitor.Monitorable;
import com.avispl.symphony.dal.communicator.RestCommunicator;
import com.avispl.symphony.dal.util.StringUtils;

/**
 * DanteWorldClockCommunicator
 *
 * Monitorable properties:
 * <ol>
 *   <li>Device Information</li>
 *   <li>Sync Input Information</li>
 *   <li>Tone Generator Information</li>
 *   <li>Main Menu Information</li>
 * </ol>
 *
 * Controllable properties: Not Support in this version
 *
 * @author Kevin / Symphony Dev Team<br>
 * Created on 6/20/2022
 * @since 1.0.0
 */
public class DanteLeaderClockCommunicator extends RestCommunicator implements Monitorable {

	private boolean isLoginSuccess = false;
	private String syncInputStatus;
	private final Map<String, String> failedMonitor = new HashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void authenticate() {
		try {
			String loginPayloads = String.format(DanteLeaderClockConstant.LOGIN_PAYLOADS, this.getLogin(), this.getPassword());
			Document doc = Jsoup.parse(this.doPost(DanteLeaderClockCommands.GET_LOGIN_COMMAND.getCommand(), loginPayloads));
			// Get all forms from the HTML response
			Elements formElements = doc.select(DanteLeaderClockConstant.FORM_TAG);
			// If formElements doesn't contain the login.htm form, the login is success.
			for (Element e : formElements) {
				// Check if selected form is login form then preceded
				if (e.attr(DanteLeaderClockConstant.ACTION_ATTRIBUTE).equals(DanteLeaderClockCommands.GET_LOGIN_COMMAND.getCommand())) {
					Elements pTags = e.select(DanteLeaderClockConstant.P_TAG);
					for (Element tag : pTags) {
						if (tag.toString().contains(DanteLeaderClockConstant.LOGIN_FAILED)) {
							return;
						}
					}
				}
			}
			isLoginSuccess = true;
		} catch (Exception e) {
			throw new ResourceNotReachableException(String.format("Fail to login with username: %s, password: %s", this.getLogin(), this.getPassword()), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Statistics> getMultipleStatistics() throws Exception {
		this.authenticate();
		if (!isLoginSuccess) {
			throw new ResourceNotReachableException(String.format("Fail to login with username: %s, password: %s", this.getLogin(), this.getPassword()));
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Perform getMultipleStatistics() at host: %s, port: %s", this.getHost(), this.getPort()));
		}
		final Map<String, String> stats = new HashMap<>();

		failedMonitor.clear();
		mainMenuProperties(stats);
		syncInputProperties(stats);
		networkProperties(stats);
		toneGeneratorProperties(stats);
		systemProperties(stats);
		if (failedMonitor.size() == DanteLeaderClockConstant.NO_OF_MONITORING_METRICS) {
			StringBuilder stringBuilder = new StringBuilder();
			for (Map.Entry<String, String> messageFailed : failedMonitor.entrySet()) {
				stringBuilder.append(messageFailed.getValue());
			}
			failedMonitor.clear();
			throw new ResourceNotReachableException("Fail to get monitoring data: " + stringBuilder);
		}
		ExtendedStatistics extendedStatistics = new ExtendedStatistics();
		extendedStatistics.setStatistics(stats);
		return Collections.singletonList(extendedStatistics);
	}

	/**
	 * Populate statistics for main menu  properties
	 *
	 * @param stats map of statistics
	 */
	private void mainMenuProperties(Map<String, String> stats) {
		try {
			Document doc = Jsoup.parse(doGet(DanteLeaderClockCommands.GET_MAIN_MENU_COMMAND.getCommand()));
			MainMenuDTO mainMenuDTO = populateMainMenuDTO(doc);
			// Not populate this group if all fields are None
			if (mainMenuDTO.isAllNone()) {
				throw new ResourceNotReachableException("Fail to populate statistics for MainMenu group");
			}
			String groupName = DanteLeaderClockCommands.GET_MAIN_MENU_COMMAND.getGroupName();
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.MAIN_CLOCK_SOURCE.getPropertyName()), mainMenuDTO.getMainClockSource());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.FAILOVER_CLOCK_SOURCE.getPropertyName()),
					mainMenuDTO.getFailOverClockSource());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.FORCE_PREFERRED_LEADER.getPropertyName()),
					mainMenuDTO.getForcePreferredLeader());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.CURRENT_CLOCK_SOURCE.getPropertyName()), mainMenuDTO.getCurrentClockSource());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.PRIMARY_LEADER_CLOCK.getPropertyName()), mainMenuDTO.getPrimaryLeaderClock());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.PRIMARY_PTPV1_STATE.getPropertyName()), mainMenuDTO.getPrimaryPTPV1());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.PRIMARY_PTPV2_STATE.getPropertyName()), mainMenuDTO.getPrimaryPTPV2());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.SECONDARY_PTPV1_STATE.getPropertyName()), mainMenuDTO.getSecondaryPTPV1());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.SECONDARY_PTPV2_STATE.getPropertyName()), mainMenuDTO.getSecondaryPTPV2());
		} catch (Exception e) {
			logger.error(e);
			failedMonitor.put(DanteLeaderClockConstant.MAIN_MENU, e.getMessage());
		}
	}

	/**
	 * Populate properties for MainMenuDTO
	 *
	 * @param doc {@link Document} node that contains the required information
	 * @return MainMenuDTO
	 */
	private MainMenuDTO populateMainMenuDTO(Document doc) {
		MainMenuDTO mainMenuDTO = new MainMenuDTO();
		String rawMainClockSource = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, DanteLeaderClockConstant.MAIN_CLOCK_SOURCE_PAYLOAD)
				.select(DanteLeaderClockConstant.SELECTED_OPTION).attr(DanteLeaderClockConstant.ATTRIBUTE_VALUE);
		MainClockSourceMetric mainClockSourceMetric = MainClockSourceMetric.getByValue(rawMainClockSource);
		String mainClockSource = DanteLeaderClockConstant.NONE;
		if (mainClockSourceMetric != null) {
			mainClockSource = mainClockSourceMetric.getName();
		}
		mainMenuDTO.setMainClockSource(mainClockSource);

		String rawFailOverClockSource = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, DanteLeaderClockConstant.FAIL_OVER_CLOCK_SOURCE_PAYLOAD)
				.select(DanteLeaderClockConstant.SELECTED_OPTION).attr(DanteLeaderClockConstant.ATTRIBUTE_VALUE);
		String failOverClockSource = DanteLeaderClockConstant.NONE;
		FailOverClockSourceMetric failOverClockSourceMetric = FailOverClockSourceMetric.getByValue(rawFailOverClockSource);
		if (failOverClockSourceMetric != null) {
			failOverClockSource = failOverClockSourceMetric.getName();
		}
		mainMenuDTO.setFailOverClockSource(failOverClockSource);

		String rawForcePreferredLeader = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, DanteLeaderClockConstant.FORCE_PREFERRED_LEADER_PAYLOAD)
				.select(DanteLeaderClockConstant.SELECTED_OPTION).attr(DanteLeaderClockConstant.ATTRIBUTE_VALUE);
		ForcePreferredLeaderMetric forcePreferredLeaderMetric = ForcePreferredLeaderMetric.getByValue(rawForcePreferredLeader);
		String forcePreferredLeader = DanteLeaderClockConstant.NONE;
		if (forcePreferredLeaderMetric != null) {
			forcePreferredLeader = forcePreferredLeaderMetric.getName();
		}
		mainMenuDTO.setForcePreferredLeader(forcePreferredLeader);
		Elements valClassElements = doc.getElementsByClass(DanteLeaderClockConstant.CLASS_TAG_VAL);
		// Span tags contain information about: Current Clock Source, Sync Input Status, Primary Leader Clock, Primary PTPV1,
		// Primary PTPV2, Secondary PTPV1, Secondary PTPV2,
		Elements spanTagElements = valClassElements.select(DanteLeaderClockConstant.SPAN_TAG);
		mainMenuDTO.setCurrentClockSource(EnumHandler.populateNoneIfNotValidName(spanTagElements.get(0).text(), CurrentClockSourceMetric.class));
		String primaryLeaderClock = DanteLeaderClockConstant.NONE;
		String primaryLeaderClockResponse = spanTagElements.get(2).text();
		if (!StringUtils.isNullOrEmpty(primaryLeaderClockResponse)) {
			primaryLeaderClock = primaryLeaderClockResponse;
		}
		mainMenuDTO.setPrimaryLeaderClock(primaryLeaderClock);
		mainMenuDTO.setPrimaryPTPV1(EnumHandler.populateNoneIfNotValidName(spanTagElements.get(3).text(), PTPMetric.class));
		mainMenuDTO.setPrimaryPTPV2(EnumHandler.populateNoneIfNotValidName(spanTagElements.get(4).text(), PTPMetric.class));
		mainMenuDTO.setSecondaryPTPV1(EnumHandler.populateNoneIfNotValidName(spanTagElements.get(5).text(), PTPMetric.class));
		mainMenuDTO.setSecondaryPTPV2(EnumHandler.populateNoneIfNotValidName(spanTagElements.get(6).text(), PTPMetric.class));
		this.syncInputStatus = EnumHandler.populateNoneIfNotValidName(spanTagElements.get(1).text(), SyncInputStatusMetric.class);
		return mainMenuDTO;
	}

	/**
	 * Populate statistics for sync input properties
	 *
	 * @param stats Map of statistics
	 */
	private void syncInputProperties(Map<String, String> stats) {
		try {
			Document doc = Jsoup.parse(doGet(DanteLeaderClockCommands.GET_SYNC_INPUT_COMMAND.getCommand()));
			SyncInputDTO syncInputDTO = new SyncInputDTO();
			Elements valClassElements = doc.getElementsByClass(DanteLeaderClockConstant.CLASS_TAG_VAL);
			Elements spanTagElements = valClassElements.select(DanteLeaderClockConstant.SPAN_TAG);
			String lockStatus = spanTagElements.get(0).text();
			syncInputDTO.setLockStatus(lockStatus);
			String currentDanteSampleRateNotNormalized = EnumHandler.populateNoneIfNotValidName(spanTagElements.get(1).text(), SyncInputCurrentSampleRateMetric.class);
			String currentDanteSampleRate = DanteLeaderClockConstant.NONE;
			if (!currentDanteSampleRateNotNormalized.equals( DanteLeaderClockConstant.NONE)) {
				currentDanteSampleRate = currentDanteSampleRateNotNormalized.split( DanteLeaderClockConstant.SPACE_REGEX)[0];
			}
			syncInputDTO.setCurrentDanteSampleRate(currentDanteSampleRate);
			String rawSyncInputType = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, DanteLeaderClockConstant.SYNC_INPUT_TYPE_PAYLOAD)
					.select(DanteLeaderClockConstant.SELECTED_OPTION).attr(DanteLeaderClockConstant.ATTRIBUTE_VALUE);
			String syncInputType = DanteLeaderClockConstant.NONE;
			SyncInputTypeMetric syncInputTypeMetric = SyncInputTypeMetric.getByValue(rawSyncInputType);
			if (syncInputTypeMetric != null) {
				syncInputType = syncInputTypeMetric.getName();
			}
			syncInputDTO.setSyncInputType(syncInputType);
			String rawSyncInputTermination = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, DanteLeaderClockConstant.SYNC_INPUT_TERMINATION_PAYLOAD)
					.select(DanteLeaderClockConstant.SELECTED_OPTION).attr(DanteLeaderClockConstant.ATTRIBUTE_VALUE);
			SyncInputTerminationMetric syncInputTerminationMetric = SyncInputTerminationMetric.getByValue(rawSyncInputTermination);
			String syncInputTermination = DanteLeaderClockConstant.NONE;
			if (syncInputTerminationMetric != null) {
				syncInputTermination = syncInputTerminationMetric.getName();
			}
			syncInputDTO.setSyncInputTermination(syncInputTermination);
			syncInputDTO.setSyncInputStatus(this.syncInputStatus);
			if (syncInputDTO.isAllNone()) {
				throw new ResourceNotReachableException("Fail to populate statistics for SyncInput group");
			}
			String groupName = DanteLeaderClockCommands.GET_SYNC_INPUT_COMMAND.getGroupName();
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.LOCK_STATUS.getPropertyName()), syncInputDTO.getLockStatus());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.SYNC_INPUT_STATUS.getPropertyName()), syncInputDTO.getSyncInputStatus());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.CURRENT_DANTE_SAMPLE_RATE.getPropertyName()), syncInputDTO.getCurrentDanteSampleRate());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.SYNC_INPUT_TYPE.getPropertyName()), syncInputDTO.getSyncInputType());
			stats.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockMonitoringMetrics.SYNC_INPUT_TERMINATION.getPropertyName()), syncInputDTO.getSyncInputTermination());
		} catch (Exception e) {
			logger.error(e);
			failedMonitor.put(DanteLeaderClockConstant.SYNC_INPUT, e.getMessage());
		}
	}

	/**
	 * Populate statistics for tone generator properties
	 *
	 * @param stats Map of statistics
	 */
	private void toneGeneratorProperties(Map<String, String> stats) {
		try {
			Map<String, String> toneGeneratorInfoMap = new HashMap<>();
			Document doc = Jsoup.parse(this.doGet(DanteLeaderClockCommands.GET_TONE_GENERATOR_COMMAND.getCommand()));
			String groupName = DanteLeaderClockCommands.GET_TONE_GENERATOR_COMMAND.getGroupName();
			int numberOfFailToneLevelOrFrequency = 0;
			for (int i = 1; i <= 8; i++) {
				String currentToneFrequency = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, "i" + i + "f").val();
				if (StringUtils.isNullOrEmpty(currentToneFrequency)) {
					currentToneFrequency = DanteLeaderClockConstant.NONE;
					numberOfFailToneLevelOrFrequency++;
				}
				String currentToneLevel = doc.getElementsByAttributeValue(DanteLeaderClockConstant.NAME_ATTRIBUTE, "i" + i + "l").val();
				if (StringUtils.isNullOrEmpty(currentToneLevel)) {
					currentToneLevel = DanteLeaderClockConstant.NONE;
					numberOfFailToneLevelOrFrequency++;
				}
				toneGeneratorInfoMap.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockConstant.TONE + i + DanteLeaderClockConstant.FREQUENCY+ DanteLeaderClockConstant.NORMALIZED_TONE_FREQUENCY), currentToneFrequency);
				toneGeneratorInfoMap.put(String.format(DanteLeaderClockConstant.GROUP_PROPERTY_NAME, groupName, DanteLeaderClockConstant.TONE + i + DanteLeaderClockConstant.LEVEL+ DanteLeaderClockConstant.NORMALIZED_TONE_LEVEL), currentToneLevel);
			}
			if (numberOfFailToneLevelOrFrequency == toneGeneratorInfoMap.size()) {
				throw new ResourceNotReachableException("Fail to populate statistics for ToneGenerator group");
			}
			// Only put to stats here if no exception occur.
			stats.putAll(toneGeneratorInfoMap);
		} catch (Exception e) {
			logger.error(e);
			failedMonitor.put(DanteLeaderClockConstant.TONE_GENERATOR, e.getMessage());
		}
	}

	/**
	 * Populate statistics for network properties
	 *
	 * @param stats Map of statistics
	 */
	private void networkProperties(Map<String, String> stats) {
		try {
			Map<String, String> networkMap = new HashMap<>();
			Document doc = Jsoup.parse(doGet(DanteLeaderClockCommands.GET_NETWORK_COMMAND.getCommand()));
			Elements trElements = doc.select(DanteLeaderClockConstant.TR_TAG);
			// Remove unused tr tag
			trElements.remove(0);
			// List that contains network configurations is enabled
			List<Integer> validNetworkList = new ArrayList<>();
			// Handle the first 4 tds from the second tr to find which network properties are enabled then store to a list.
			// If DanteSecondary is disabled => the format should look like this:
			// ...
			// <tr>
			//      <td>IP Address</td>
			//      <td>X.X.X.X</td>
			//      <td rowspan="4">Disabled</td>
			//      <td>X.X.X.X</td>
			// </tr>
			Elements tdElements = trElements.get(0).select(DanteLeaderClockConstant.TD_TAG);
			// Populate ip addresses to stats map and add valid network properties to validNetworkList.
			populateIpAddressProperties(networkMap, validNetworkList, tdElements);
			int noneNetworkProperties = 0;
			// Loop through other tr tags to extract needed information
			for (int i = 1; i < trElements.size(); i++) {
				Elements tdElements2 = trElements.get(i).select(DanteLeaderClockConstant.TD_TAG);
				// First td store the name(Subnet Mask/Gateway/MAC Address) of the property that will be extracted.
				String firstTd = tdElements2.get(0).text();
				int index = 1;
				for (Integer id : validNetworkList) {
					String tdValue = tdElements2.get(index).text();
					String propertyName;
					if (id == 1) {
						propertyName = DanteLeaderClockConstant.DANTE_PRIMARY;
					} else if (id == 2) {
						propertyName = DanteLeaderClockConstant.DANTE_SECONDARY;
					} else {
						propertyName = DanteLeaderClockConstant.MANAGEMENT;
					}
					String noSpaceFirstTd = firstTd.replaceAll(DanteLeaderClockConstant.SPACE_REGEX, DanteLeaderClockConstant.EMPTY);
					if (StringUtils.isNullOrEmpty(tdValue)) {
						tdValue = DanteLeaderClockConstant.NONE;
						noneNetworkProperties++;
					}
					networkMap.put(String.format(DanteLeaderClockConstant.TWO_STRINGS_FORMAT, propertyName, noSpaceFirstTd), tdValue);
					index++;
				}
			}
			if (noneNetworkProperties == networkMap.size()) {
				throw new ResourceNotReachableException("Fail to populate statistics for Network group");
			}
			// Only put to stats here if no exception occur.
			stats.putAll(networkMap);
		} catch (Exception e) {
			logger.error(e);
			failedMonitor.put(DanteLeaderClockConstant.NETWORK, e.getMessage());
		}
	}

	/**
	 * Populate ip address, state for Dante Primary/Secondary/Management properties.
	 * Add valid properties(properties that are not disabled) to validNetworkList list.
	 *
	 * @param stats Map of statistics
	 * @param validNetworkList List of valid network properties
	 * @param tdElements Td tags of ip addresses
	 */
	private void populateIpAddressProperties(Map<String, String> stats, List<Integer> validNetworkList, Elements tdElements) {
		for (int i = 1; i < tdElements.size(); i++) {
			String propertyName;
			if (i == 1) {
				propertyName = DanteLeaderClockConstant.DANTE_PRIMARY;
			} else if (i == 2) {
				propertyName = DanteLeaderClockConstant.DANTE_SECONDARY;
			} else {
				propertyName = DanteLeaderClockConstant.MANAGEMENT;
			}
			if (!DanteLeaderClockConstant.DISABLED.equals(tdElements.get(i).text())) {
				validNetworkList.add(i);
				stats.put(String.format(DanteLeaderClockConstant.TWO_STRINGS_FORMAT, propertyName, DanteLeaderClockMonitoringMetrics.NETWORK_IP_ADDRESS.getPropertyName()), tdElements.get(i).text());
				if (DanteLeaderClockConstant.DANTE_SECONDARY.equals(propertyName) || DanteLeaderClockConstant.MANAGEMENT.equals(propertyName)) {
					stats.put(propertyName + DanteLeaderClockConstant.STATE, DanteLeaderClockConstant.ENABLED);
				}
			} else {
				stats.put(propertyName + DanteLeaderClockConstant.STATE, DanteLeaderClockConstant.DISABLED);
			}
		}
	}

	/**
	 * Populate statistics for system properties
	 *
	 * @param stats Map of statistics
	 */
	private void systemProperties(Map<String, String> stats) {
		try {
			Map<String, String> systemMap = new HashMap<>();
			Document doc = Jsoup.parse(doGet(DanteLeaderClockCommands.GET_SYSTEM_COMMAND.getCommand()));
			String serialNumber = doc.getElementsByClass(DanteLeaderClockConstant.CLASS_TAG_VAL).get(0).text();
			stats.put(DanteLeaderClockConstant.SERIAL_NUMBER, serialNumber);
			Elements trElements = doc.select(DanteLeaderClockConstant.TR_TAG);
			trElements.remove(0);
			int numberOfNoneSystemProperties = 0;
			for (Element trElement : trElements
			) {
				Elements tdElements = trElement.select(DanteLeaderClockConstant.TD_TAG);
				String propertyName = tdElements.get(0).text().replaceAll(DanteLeaderClockConstant.SPACE_REGEX, DanteLeaderClockConstant.EMPTY);
				String systemVersion = tdElements.get(1).text();
				if (StringUtils.isNullOrEmpty(systemVersion)) {
					systemVersion = DanteLeaderClockConstant.NONE;
					numberOfNoneSystemProperties++;
				}
				String systemDate = tdElements.get(2).text();
				if (StringUtils.isNullOrEmpty(systemDate)) {
					systemDate = DanteLeaderClockConstant.NONE;
					numberOfNoneSystemProperties++;
				}
				systemMap.put(String.format(DanteLeaderClockConstant.TWO_STRINGS_FORMAT, propertyName, DanteLeaderClockMonitoringMetrics.SYSTEM_VERSION.getPropertyName()), systemVersion);
				systemMap.put(String.format(DanteLeaderClockConstant.TWO_STRINGS_FORMAT, propertyName, DanteLeaderClockMonitoringMetrics.SYSTEM_DATE.getPropertyName()), systemDate);
			}
			if (numberOfNoneSystemProperties == systemMap.size()) {
				throw new ResourceNotReachableException("Fail to populate statistics for System group");
			}
			stats.putAll(systemMap);
		} catch (Exception e) {
			logger.error(e);
			failedMonitor.put(DanteLeaderClockConstant.SYSTEM, e.getMessage());
		}
	}
}
