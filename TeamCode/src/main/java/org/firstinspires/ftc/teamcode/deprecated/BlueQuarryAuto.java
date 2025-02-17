package org.firstinspires.ftc.teamcode.deprecated;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Library;

public class BlueQuarryAuto extends Library
{
    State currentState;

    long startTime;
    float curEncoderValue;
    int skystoneConfiguration;

    private static final double ticksPerInch = 76.43;

    public void init()
    {
        hardwareInit();
        currentState = State.DRIVE_TO_QUARRY;

        skystoneConfiguration = 0;
        curEncoderValue = getEncoderValue();
        startTime = System.currentTimeMillis();
    }

    public void loop()
    {
        switch (currentState)
        {
            case PICK_UP_BLOCK:
                gripStone(true);

                if (System.currentTimeMillis() - 100 < startTime)
                    liftGivenControllerValues(true, false);

                else
                {
                    liftGivenControllerValues(false, false);

                    if (skystoneConfiguration > -1)
                        currentState = State.SLIDE_TO_FOUNDATION;

                    else
                        currentState = State.MOVE_TO_FOUNDATION;

                    curEncoderValue = getEncoderValue();
                }

            case PLACE_BLOCK:
                gripStone(false);

                if (System.currentTimeMillis() - 100 < startTime)
                    liftGivenControllerValues(false, true);

                else
                    curEncoderValue = getEncoderValue();

                if (skystoneConfiguration > -1)
                {
                    skystoneConfiguration = -1;
                    currentState = State.DRAG_FOUNDATION;
                }

                else
                    currentState = State.MOVE_UNDER_BRIDGE;

            case DRIVE_TO_QUARRY:
                if (getEncoderValue() - ticksPerInch * 30 > curEncoderValue)
                    drive(.5f, 0, 0);

                else
                {
                    drive(0, 0, 0);
                    currentState = State.PICK_UP_BLOCK;
                }

            case MOVE_TO_QUARRY:
                if (getEncoderValue() - ticksPerInch * 120 > curEncoderValue)
                    drive(0, 0, -.5f);

                else
                {
                    drive(0, 0, 0);
                    currentState = State.PICK_UP_BLOCK;
                }

            case SLIDE_TO_FOUNDATION:
                if (getEncoderValue() - ticksPerInch * 108 > curEncoderValue)
                    drive(0, 0, .5f);

                else
                {
                    drive(0, 0, 0);
                    currentState = State.PLACE_BLOCK;

                    startTime = System.currentTimeMillis();
                }

            case MOVE_TO_FOUNDATION:
                if (getEncoderValue() - ticksPerInch * 10 > curEncoderValue)
                    drive(-.5f, 0, 0);

                else
                {
                    drive(0, 0, 0);
                    currentState = State.PLACE_BLOCK;
                }

            case DRAG_FOUNDATION:
                if (getEncoderValue() - ticksPerInch * 20 > curEncoderValue)
                    drive(-.5f, 0, 0);

                else
                {
                    drive(0, 0, 0);
                    currentState = State.MOVE_TO_QUARRY;

                    curEncoderValue = getEncoderValue();
                }

//            case MOVE_UNDER_BRIDGE:
//                if (!isUnderBridge(255, true))
//                    drive(0, 0, .5f);
//
//                else
//                    drive(0, 0, 0);

            default:
                telemetry.addLine("The routine has been completed");
        }
    }

    enum State
    {
        PICK_UP_BLOCK, PLACE_BLOCK, DRIVE_TO_QUARRY, MOVE_TO_QUARRY, SLIDE_TO_FOUNDATION,
        MOVE_TO_FOUNDATION, DRAG_FOUNDATION, MOVE_UNDER_BRIDGE
    }
}