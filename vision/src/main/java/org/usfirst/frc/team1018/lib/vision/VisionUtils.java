package org.usfirst.frc.team1018.lib.vision;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Ryan Blue
 */
public class VisionUtils {
    public static final Comparator<Rect> RECT_SIZE_COMPARATOR = (c1, c2) -> {
        double sizeOne = c1.area();
        double sizeTwo = c2.area();
        return (int) (sizeOne - sizeTwo);
    };
    public static final Comparator<Rect> RECT_X_CENTER_COMPARATOR = (c1, c2) -> {
        double centerOne = getRectCenterX(c1);
        double centerTwo = getRectCenterX(c2);
        return (int) (centerOne - centerTwo);
    };

    private VisionUtils() {}

    /**
     * Calculates the Z-Offset (distance) from a target
     * <p>
     * The value returned will be in the same unit as the focal length and the real width of the
     * target. If you put them in different units, bad things will happen. Don't do that.
     *
     * @param targetWidthPixels the width of the target in pixels
     * @param focalLength       the focal length of your camera
     * @param targetRealWidth   the width of the target in real life.
     * @return the distance from the target
     */
    public static double calculateZOffsetFromXWidth(int targetWidthPixels, double focalLength, double targetRealWidth) {
        return (targetRealWidth * focalLength) / targetWidthPixels;
    }

    public static double calculateZOffsetFromXWidth() {
        return 0;
    }

    /**
     * Not supported yet
     *
     * @param targetWidthPixels
     * @return
     */
    public static double calculateXOffsetFromXWidth(int targetWidthPixels) {
        return 0;
    }

    public static double calculateYAngle() {
        return 0;
    }

    /**
     * Calculates the x value of the "aiming coordinates" of a target from the center pixel of the
     * target.
     * <p>
     * The "aiming" coordinate system has its origin in the center, and bounds of 1 in either
     * direction.
     *
     * @param width        the width of the image
     * @param xCenterPixel the center (in pixels) of the target
     * @return the x value of the center of the target in the aiming coordinate system
     */
    public static double calculateTargetAimingX(double width, double xCenterPixel) {
        width /= 2;
        return (xCenterPixel - width) / width;
    }

    /**
     * Calculates the x value of the "aiming coordinates" of a target from a {@link Rect}
     *
     * @param rect  the {@link Rect} to find the aiming coordinates of
     * @param width the width of the image
     * @return the x value of the center of the {@link Rect} in the aiming coordinate system
     * @see VisionUtils#calculateTargetAimingX(double, double)
     */
    public static double calculateTargetAimingX(double width, Rect rect) {
        return calculateTargetAimingX(width, getRectCenterX(rect));
    }

    public static double calculateMultipleTargetAimingX(double width, double... xCenterPixels) {
        double sum = 0;
        for(double xCenterPixel : xCenterPixels) {
            sum += calculateTargetAimingX(width, xCenterPixel);
        }
        return sum / xCenterPixels.length;
    }

    public static double calculateMultipleTargetAimingX(double width, Rect... rects) {
        double sum = 0;
        for(Rect rect : rects) {
            sum += calculateTargetAimingX(width, getRectCenterX(rect));
        }
        return sum / rects.length;
    }

    /**
     * Gets the x value of the center coordinate of a {@link Rect}.
     * <p>
     * Note that the OpenCV coordinate space starts from the top-left corner.
     *
     * @param rect the {@link Rect} to find the center of
     * @return the x value of the center coordinate of the {@link Rect}
     */
    public static double getRectCenterX(Rect rect) {
        return rect.x + (rect.width / 2);
    }

    public static ArrayList<Rect> getBoundingBoxesFromCountours(ArrayList<MatOfPoint> contours) {
        ArrayList<Rect> boundingRects = new ArrayList<>(contours.size());

        for(MatOfPoint contour : contours) {
            boundingRects.add(Imgproc.boundingRect(contour));
        }
        return boundingRects;
    }

}
