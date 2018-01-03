package org.usfirst.frc.team1018.vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.usfirst.frc.team1018.lib.vision.VisionPacket;
import org.usfirst.frc.team1018.lib.vision.VisionProvider;
import org.usfirst.frc.team1018.lib.vision.VisionUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Ryan Blue
 */
public class VisionProcessor extends Thread implements VisionProvider {

    final int width, height;
    final VisionPipeline pipeline;
    final CvSink imageSink;
    private final VideoCamera camera;
    public long lastRunTime;
    private VisionPacket packet = new VisionPacket();
    private Mat inputImage = new Mat();

    public VisionProcessor(VideoCamera camera, VisionPipeline pipeline, int width, int height) {
        this.width = width;
        this.height = height;
        this.pipeline = pipeline;
        this.camera = camera;
        this.camera.setResolution(width, height);
        this.camera.setBrightness(53);
        this.camera.getProperty("contrast").set(100);
        this.camera.getProperty("saturation").set(100);
        this.camera.setWhiteBalanceAuto();
        this.camera.getProperty("gain").set(0);
        this.camera.getProperty("sharpness").set(51);
        this.camera.setExposureManual(1);
        imageSink = new CvSink("ImageGrabber");
        imageSink.setSource(camera);
    }

    public VisionPacket getLatestData() {
        synchronized(packet) {
            return packet;
        }
    }

    private void updatePacket(VisionPacket packet) {
        synchronized(packet) {
            this.packet = packet;
        }
    }

    public VideoCamera getCamera() {
        return camera;
    }

    @Override
    public synchronized void start() {
        super.start();
        lastRunTime = System.nanoTime();
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            updatePacket(process());
        }
    }

    private VisionPacket process() {

        VisionPacket loopPacket = new VisionPacket();
        // Grab a frame. If it has a frame time of 0, there was an error.
        // Return an empty packet
        long frameTime = imageSink.grabFrame(inputImage);
        if(frameTime == 0) return loopPacket;

        //process the image in the pipeline
        pipeline.process(inputImage);
        int numberOfTargets = pipeline.filterContoursOutput().size();
        loopPacket.numberOfTargets = numberOfTargets;
        loopPacket.targetFound = numberOfTargets > 0;
        Rect left, right;
        boolean singleTarget = false;
        boolean leftOnly = false;
        if(numberOfTargets != 0) {
            ArrayList<Rect> boundingBoxes = VisionUtils.getBoundingBoxesFromCountours(pipeline.filterContoursOutput());
            switch(numberOfTargets) {
                default:
                    boundingBoxes.sort(VisionUtils.RECT_SIZE_COMPARATOR);
                    Collections.reverse(boundingBoxes);
                    boundingBoxes = new ArrayList<>(boundingBoxes.subList(0, 2));
                case 2: {
                    boundingBoxes.sort(VisionUtils.RECT_X_CENTER_COMPARATOR);
                    left = boundingBoxes.get(0);
                    right = boundingBoxes.get(1);
                    break;
                }
                case 1: {
                    singleTarget = true;
                    Rect target = boundingBoxes.get(0);
                    if(VisionUtils.calculateTargetAimingX(width, target) < 0) {
                        leftOnly = true;
                        left = target;
                        right = null;
                    } else {
                        leftOnly = false;
                        left = null;
                        right = target;
                    }
                }
            }
            if(singleTarget) {
                loopPacket.aimingCenterX = VisionUtils.calculateTargetAimingX(width, leftOnly ? left : right);
            } else {
                loopPacket.aimingCenterX = VisionUtils.calculateMultipleTargetAimingX(width, left, right);
            }
            loopPacket.zOffset = VisionUtils.calculateZOffsetFromXWidth();
            loopPacket.xOffset = VisionUtils.calculateXOffsetFromXWidth(0);
            loopPacket.yAngleDegrees = VisionUtils.calculateYAngle();
        }


        // Here is where you would write a processed image that you want to restreams
        // This will most likely be a marked up image of what the camera sees
        // For now, we are just going to stream the HSV image
        //imageSource.putFrame(hsv);
        long currentRunTime = System.nanoTime();
        long loopTime = currentRunTime - lastRunTime;
        double instantFps = (double) 1 / (loopTime / 1000000000.0);
        loopPacket.fps = instantFps;
        lastRunTime = currentRunTime;
        return loopPacket;
    }

    public enum VisionMode {
        TARGET_TRACK_ALL,
        TARGET_TRACK_LITE
    }
}
