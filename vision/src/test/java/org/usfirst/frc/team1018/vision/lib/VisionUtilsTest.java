package org.usfirst.frc.team1018.vision.lib;

import org.junit.Test;
import org.opencv.core.Rect;
import org.usfirst.frc.team1018.lib.vision.VisionUtils;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan Blue
 */
public class VisionUtilsTest {

    @Test
    public void testGetRectCenterX() {
        Rect rect = new Rect(50, 50, 50, 50);
        assertEquals(75, VisionUtils.getRectCenterX(rect), 0.0001);
    }

    @Test
    public void testCalculateTargetAimingXFromDouble() {
        assertEquals(0, VisionUtils.calculateTargetAimingX(640, 320), 0.0001);
        assertEquals(-0.25, VisionUtils.calculateTargetAimingX(640, 240), 0.0001);
        assertEquals(0.75, VisionUtils.calculateTargetAimingX(640, 560), 0.0001);
    }

    @Test
    public void testCalculateTargetAimingXFromRect() {
        Rect rect = new Rect(295, 0, 50, 0);
        assertEquals(0, VisionUtils.calculateTargetAimingX(640, rect), 0.0001);
        rect.x = 215;
        assertEquals(-0.25, VisionUtils.calculateTargetAimingX(640, rect), 0.0001);
        rect.x = 535;
        assertEquals(0.75, VisionUtils.calculateTargetAimingX(640, rect), 0.0001);
    }
}
