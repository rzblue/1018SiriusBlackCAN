package org.usfirst.frc.team1018.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * @author Ryan Blue
 */
public class VisionNetworkTable {
    private enum  VisionTableKeys {

    }
    private final String VISION_TABLE_KEY = "vision";

    private static final VisionNetworkTable instance = new VisionNetworkTable();

    private final NetworkTable visionTable;

    private VisionNetworkTable() {
        visionTable = NetworkTable.getTable(VISION_TABLE_KEY);
    }

    public static VisionNetworkTable getInstance() {
        return instance;
    }



}
