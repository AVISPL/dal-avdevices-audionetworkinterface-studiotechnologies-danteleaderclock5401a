package com.avispl.dal.avdevices.audionetworkinterface.studiotechnologies.danteleaderclock5401a;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.avispl.symphony.api.dal.dto.monitor.ExtendedStatistics;
import com.avispl.symphony.api.dal.error.ResourceNotReachableException;

class DanteLeaderClockCommunicatorTest {
	private final DanteLeaderClockCommunicator danteLeaderClockCommunicator = new DanteLeaderClockCommunicator();

	@BeforeEach
	public void setUp() throws Exception {
		danteLeaderClockCommunicator.setHost("10.8.53.115");
		danteLeaderClockCommunicator.setPort(80);
		danteLeaderClockCommunicator.setLogin("admin");
		danteLeaderClockCommunicator.setPassword("admin");
		danteLeaderClockCommunicator.init();
	}

	@AfterEach()
	public void destroy() {
		danteLeaderClockCommunicator.destroy();
	}

	/**
	 * Test all statistics are collected successfully
	 *
	 */
	@Test
	@Tag("RealDevice")
	void testGetMultipleStatistics() throws Exception {
		ExtendedStatistics extendedStatistics = (ExtendedStatistics) danteLeaderClockCommunicator.getMultipleStatistics().get(0);
		Map<String, String> stats = extendedStatistics.getStatistics();
		for (int i = 1; i < 8; i++) {
			assertNotNull(stats.get("ToneGenerator#Tone"+i+"Level(dBFS)"));
			assertNotNull(stats.get("ToneGenerator#Tone"+i+"Frequency(Hz)"));
		}
		// None-group
		assertNotNull(stats.get("DantePrimaryIpAddress"));
		assertNotNull(stats.get("DantePrimarySubnetMask"));
		assertNotNull(stats.get("MainFPGAFirmwareVersion"));
		assertNotNull(stats.get("MainFPGAFirmwareDate"));
		assertNotNull(stats.get("DantePrimaryGateway"));

		assertNotNull(stats.get("DanteProductDate"));
		assertNotNull(stats.get("DanteSecondaryState"));
		assertNotNull(stats.get("ManagementState"));
		assertNotNull(stats.get("ManagementSubnetMask"));
		assertNotNull(stats.get("MainMCUFirmwareVersion"));
		assertNotNull(stats.get("DantePrimaryMACAddress"));


		assertNotNull(stats.get("MainMCUFirmwareDate"));
		assertNotNull(stats.get("DanteFirmwareVersion"));
		assertNotNull(stats.get("SerialNumber"));
		assertNotNull(stats.get("ManagementGateway"));

		assertNotNull(stats.get("DanteProductVersion"));
		assertNotNull(stats.get("SyncFPGAFirmwareDate"));
		assertNotNull(stats.get("ManagementIpAddress"));
		assertNotNull(stats.get("ManagementMACAddress"));

		assertNotNull(stats.get("SyncFPGAFirmwareVersion"));
		assertNotNull(stats.get("DanteFirmwareDate"));
		// Main menu
		assertNotNull(stats.get("General#ForcePreferredLeader"));
		assertNotNull(stats.get("General#MainClockSource"));
		assertNotNull(stats.get("General#FailoverClockSource"));
		assertNotNull(stats.get("General#CurrentClockSource"));

		assertNotNull(stats.get("General#PrimaryPTPv2State"));
		assertNotNull(stats.get("General#PrimaryPTPv1State"));
		assertNotNull(stats.get("General#PrimaryLeaderClock"));
		assertNotNull(stats.get("General#SecondaryPTPv1State"));
		assertNotNull(stats.get("General#SecondaryPTPv2State"));
		// Sync input group
		assertNotNull(stats.get("SyncInput#CurrentDanteSampleRate(kHz)"));
		assertNotNull(stats.get("SyncInput#SyncInputType"));
		assertNotNull(stats.get("SyncInput#LockStatus"));
		assertNotNull(stats.get("SyncInput#SyncInputStatusMetric"));
		assertNotNull(stats.get("SyncInput#SyncInputTermination"));
		assertEquals(51, stats.size());
	}

	/**
	 * Test wrong username, expect throw exception
	 *
	 * @throws Exception when fail to init()
	 */
	@Test
	@Tag("RealDevice")
	void testFailLoginWrongAccount() throws Exception {
		this.destroy();
		danteLeaderClockCommunicator.setLogin("not-exist-account");
		danteLeaderClockCommunicator.init();
		ResourceNotReachableException exception = assertThrows(ResourceNotReachableException.class, danteLeaderClockCommunicator::getMultipleStatistics);
		assertEquals(String.format("Fail to login with username: not-exist-account, password: %s", danteLeaderClockCommunicator.getPassword()), exception.getMessage());
	}

	/**
	 * Test wrong password, expect throw exception
	 *
	 * @throws Exception when fail to init()
	 */
	@Test
	@Tag("RealDevice")
	void testFailLoginWrongPassword() throws Exception {
		this.destroy();
		danteLeaderClockCommunicator.setPassword("not-exist-password");
		danteLeaderClockCommunicator.init();
		ResourceNotReachableException exception = assertThrows(ResourceNotReachableException.class, danteLeaderClockCommunicator::getMultipleStatistics);
		assertEquals(String.format("Fail to login with username: %s, password: not-exist-password", danteLeaderClockCommunicator.getLogin()), exception.getMessage());
	}
}