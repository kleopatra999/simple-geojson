package com.mapzen.osrm;

import org.json.JSONArray;
import org.json.JSONException;

public class Instruction {
    public static String NoTurn = "No turn"; // 0;          //Give no instruction at all
    public static String GoStraight = "Continue on"; //1;      //Tell user to go straight!
    public static String TurnSlightRight = "Make a slight right on to"; //2;
    public static String TurnRight = "Make a right on to"; // 3;
    public static String TurnSharpRight = "Make a sharp right on to"; // 4;
    public static String UTurn = "U Turn"; // 5;
    public static String TurnSharpLeft = "Make a sharp left on to"; // 6;
    public static String TurnLeft = "Make a left on to"; // 7;
    public static String TurnSlightLeft = "Make a slight left on to"; // 8;
    public static String ReachViaPoint = "Reach via point"; // 9;
    public static String HeadOn = "Head on"; // 10;
    public static String EnterRoundAbout = "Enter round about"; // 11;
    public static String LeaveRoundAbout = "Leave round about"; // 12;
    public static String StayOnRoundAbout = "Stay on round about"; // 13;
    public static String StartAtEndOfStreet = "Start at end of street"; // 14;
    public static String ReachedYourDestination = "You have reached your destination"; // 15;
    public static String EnterAgainstAllowedDirection = "Enter against allowed direction"; // 16;
    public static String LeaveAgainstAllowedDirection = "Leave against allowed direction"; // 17;

    public static int METERS_IN_MILE = 1609;

    private JSONArray json;
    private int turn, distance;
    private double[] point = {};

    public Instruction(JSONArray json) {
        if (json.length() < 8) {
            throw new JSONException("too few arguments");
        }
        this.json = json;
        setTurnInstruction(json.getInt(0));
        setDistance(json.getInt(2));
    }

    public void setTurnInstruction(int turn) {
        this.turn = turn;
    }

    public int getTurnInstruction() {
        return turn;
    }

    public String getHumanTurnInstruction() {
        String[] decodedInstructions = { NoTurn, GoStraight, TurnSlightRight, TurnRight, TurnSharpRight, UTurn,
            TurnSharpLeft, TurnLeft, TurnSlightLeft, ReachViaPoint, HeadOn, EnterRoundAbout, LeaveRoundAbout,
            StayOnRoundAbout, StartAtEndOfStreet, ReachedYourDestination, EnterAgainstAllowedDirection,
            LeaveAgainstAllowedDirection
        };
        return decodedInstructions[turn];
    }

    public String getName() {
        return json.getString(1);
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public int getDistanceInMiles() {
        return distance / METERS_IN_MILE;
    }

    public String getDirection() {
        return json.getString(6);
    }

    public float getDirectionAngle() {
        String direction = getDirection();
        float angle = 0;
        if (direction.equals("NE")) {
            angle = 315.0f;
        } else if (direction.equals("E")) {
            angle = 270.0f;
        } else if (direction.equals("SE")) {
            angle = 225.0f;
        } else if (direction.equals("S")) {
            angle = 180.0f;
        } else if (direction.equals("SW")) {
            angle = 135.0f;
        } else if (direction.equals("W")) {
            angle = 90.0f;
        } else if (direction.equals("NW")) {
            angle = 45.0f;
        }
        return angle;
    }

    public int getBearing() {
        return 360 - json.getInt(7);
    }

    public double[] getPoint() {
        return point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }
}
