package org.usfirst.frc.team1018.robot.pathfinder;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

/**
 * @author Ryan Blue
 */
public class PathfinderWaypoints {
    public static Waypoint[] BLUE_RIGHT = new Waypoint[] {
            new Waypoint(0,0, 0),
            new Waypoint(-1, 2, Pathfinder.d2r(-60))
    };
    public static Waypoint[] BLUE_LEFT = new Waypoint[] {
            new Waypoint(0, 0, 0),
            new Waypoint(1, 2, Pathfinder.d2r(60))
    };

}
