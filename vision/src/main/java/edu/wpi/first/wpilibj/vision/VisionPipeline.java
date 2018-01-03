/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.ArrayList;

/**
 * A vision pipeline is responsible for running a group of
 * OpenCV algorithms to extract data from an image.
 * <p>
 * This pipeline is slightly modified to allow us to change pipelines easily without trouble
 */
public interface VisionPipeline {

    /**
     * Processes the image input and sets the result objects.
     * Implementations should make these objects accessible.
     */
    void process(Mat image);

    ArrayList<MatOfPoint> filterContoursOutput();

}
