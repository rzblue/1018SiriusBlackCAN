package org.usfirst.frc.team1018.vision;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.IRemote;
import edu.wpi.first.wpilibj.tables.IRemoteConnectionListener;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import org.usfirst.frc.team1018.lib.vision.VisionClientThread;
import org.usfirst.frc.team1018.lib.vision.VisionNetworkTable;

public class Main {
    static VisionProcessor processor;
    static VisionClientThread clientThread;

    public static void main(String[] args) {
        // Loads our OpenCV library. This MUST be included
        System.loadLibrary("opencv_java310");

        UsbCamera camera = new UsbCamera("CoprocessorCamera", 0);
        VisionPipeline pipeline = new TargetPipeline();

        processor = new VisionProcessor(
                camera,
                pipeline,
                VisionConstants.VISION_WIDTH,
                VisionConstants.VISION_HEIGHT);

        // This stores our reference to our mjpeg server for streaming the input image
        MjpegServer inputStream = new MjpegServer("MJPEG Server", VisionConstants.STREAM_PORT);
        inputStream.setSource(processor.getCamera());

        NetworkTable.setClientMode();
        NetworkTable.setTeam(1018);
        NetworkTable.setIPAddress("10.10.18.6");
        NetworkTable.initialize();

        VisionNetworkTable visionClient = VisionNetworkTable.getInstance();
        clientThread = new VisionClientThread(visionClient, processor);
        processor.start();
        clientThread.start();
    }
}

