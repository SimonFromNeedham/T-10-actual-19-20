package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp")
public class DriveTeleOp extends Library{
    boolean subroutine;

    public void init()
    {
        hardwareInit();
        driveInit();
    }
    private float[] sums;
    public void loop(){

        if (isSkystoneVisible() && distanceLeft.getDistance(DistanceUnit.CM) > 10)
        {
            subroutine = true;
            drive(.5f, 0, 0);

            return;
        }

        if (subroutine && distanceLeft.getDistance(DistanceUnit.CM) < 11)
        {
            telemetry.addLine("Stone should be placed");
            drive(0, 0, 0);

            subroutine = true;
        }

        //tapemeasure code
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;

        //Stone gripping | both gamepads
        boolean x = gamepad1.x;
        boolean x2 = gamepad2.x;

        //Hook control to grab foundation | both gamepads
        boolean y = gamepad1.y;
        boolean y2 = gamepad2.y;

        //Lift controls | Both gamepads
        boolean liftUp = gamepad1.right_bumper;
        boolean liftDown = gamepad1.left_bumper;
        boolean liftUp2 = gamepad2.right_bumper;
        boolean liftDown2 = gamepad2.left_bumper;

        //Movement inputs
        float linear = gamepad1.left_stick_y; //Forward and back
        float side = gamepad1.left_stick_x; //Right and left
        float rotation = gamepad1.right_stick_x; //Rotating in place

        //If controller two gives any commands (true) than the robot will use those inputs
        //Otherwise, it will use the inputs of controller one

        if( x2 ){
            gripStone(true);
        }else{
            gripStone(x);
        }

        if(a)
            tapeMeasure.setPower(-1);
        else if (b)
            tapeMeasure.setPower(1);
        else
            tapeMeasure.setPower(0);

        if( y2 ){
                       gripFoundation(true);
        }else{
                       gripFoundation(y);
        }

        if( liftUp2 || liftDown2 ){
                        liftGivenControllerValues(liftUp2, liftDown2);
        }else{
                        liftGivenControllerValues(liftUp, liftDown);
        }

        if( gamepad1.right_stick_button ){
            mode = mode.getNext();
        }

        if( mode == DRIVING.Slow ){
            sums = drive(linear / 2, rotation / 2, side / 2); // slow driving
        }else if( mode == DRIVING.Medium ){
            sums = drive(linear / 1.5f, rotation / 1.5f, side / 1.5f); // medium driving
        }else if( mode == DRIVING.Fast ){
            sums = drive(linear, rotation, side); // fast driving
        }

        telemetry.addData("Front Left", sums[0]);
        telemetry.addData("Front Right", sums[1]);
        telemetry.addData("Back Left", sums[2]);
        telemetry.addData("Back Right", sums[3]);

        telemetry.addData("Values: ", linear + "\n " + rotation + "\n " + side);
    }
}