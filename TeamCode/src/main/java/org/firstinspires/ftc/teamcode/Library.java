package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public abstract class Library extends OpMode{
    // the rotation of the encoders is measured in steps
    final static int ENCODER_STEPS_PER_ROTATION = 1120;
    // mm the lift moves for each rotation of the lift motor
    // TODO: measured as the diameter of the spool
    final static int MM_PER_LIFT_ROTATION = 1;
    private static final int TRACTION_SCALER = 1; //temp value will be changed // Used in driveForEncoders/slideForEncoders
    // Declare hardware devices
    public static DcMotor frontLeft, frontRight, backLeft, backRight, intakeOne, intakeTwo, lift;
    public static Servo platform, grabber;
    public static CRServo rotateGrabber;
    public static VoltageSensor voltageSensor;
    // Initialize hardware devices and their zero behavior
    public static TouchSensor front1, front2;
    public static ColorSensor color;
    public DRIVING mode;

<<<<<<< HEAD
    public static void driveUntil( boolean sensor, int l, int r, int s ){
        if( !sensor ){
            drive(l, r, s);
        }else{
=======
    // the rotation of the encoders is measured in steps
    final static int ENCODER_STEPS_PER_ROTATION = 1120;

    // mm the lift moves for each rotation of the lift motor
    // TODO: measured as the diameter of the spool
    final static int MM_PER_LIFT_ROTATION = 1;
    public enum DRIVING{
        Slow, Medium, Fast;
        public DRIVING getNext(){
            return values()[(ordinal() + 1) % values().length];
        } // change driving mode
    }

    public void hardwareInit(){
        frontLeft = hardwareMap.dcMotor.get("m0");
        frontRight = hardwareMap.dcMotor.get("m1");
        backLeft = hardwareMap.dcMotor.get("m2");
        backRight = hardwareMap.dcMotor.get("m3");

        lift = hardwareMap.dcMotor.get("l0");
        intakeOne = hardwareMap.dcMotor.get("l1");
        intakeTwo = hardwareMap.dcMotor.get("l2");

        platform = hardwareMap.servo.get("s0");
        grabber = hardwareMap.servo.get("s1");
        rotateGrabber = hardwareMap.crservo.get("s2");

        color = hardwareMap.colorSensor.get("color1");
        front1 = hardwareMap.touchSensor.get("touch1");
        front2 = hardwareMap.touchSensor.get("touch2");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //liftGivenControllerValues.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //liftGivenControllerValues.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        mode = DRIVING.Fast;
    }

    public static void driveUntil(boolean sensor, int l, int r, int s){
        if (!sensor){
            drive(l, r, s);
        } else{
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            drive(0, 0, 0);
        }
    }

    //Each method below uses inputs to dictate the robot's actions
    //(i.e gripSkystone, which determines weather the robot should grab or not)
<<<<<<< HEAD
    /*public static void intake( boolean a, boolean b ){
        double num = 0.0;

        if( a ){
            num = .5;
        }
        if( b ){
=======
    public static void intake(boolean a, boolean b){
        double num = 0.0;

        if (a){
            num = .5;
        }
        if (b){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            num = -.5;
        }

        intakeOne.setPower(num);
        intakeTwo.setPower(num);
    }*/

<<<<<<< HEAD
    public static void gripStone( boolean x ){
        if( x ){
=======
    public static void gripStone(boolean x){
        if(x){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            grabber.setPosition(1);
        }else{
            grabber.setPosition(0);
        }
    }

<<<<<<< HEAD
    public static void gripFoundation( boolean y ){
        if( y ){
            platform.setPosition(1);
        }else{
            platform.setPosition(0);
        }
    }

    public static void liftGivenControllerValues( boolean up, boolean down ){
        if( up ){
            lift.setPower(.5);
        }
        if( down ){
            lift.setPower(-.5);
        }
        if( !up && !down ){
=======
    public static void gripFoundation(boolean y){
        if(y){
            platform.setPosition(0);
        }else{
            platform.setPosition(1);
        }
    }

    public static void liftGivenControllerValues(boolean up, boolean down){
        if(up){
            lift.setPower(.5);
        }
        if(down){
            lift.setPower(-.5);
        }
        if(!up && !down){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            lift.setPower(0);
        }
    }

<<<<<<< HEAD
    /*public static void gripRotate( float left, float right ){
        if( right > left ){
            rotateGrabber.setPower(right);
        }else if( left > right ){
=======
    public static void gripRotate(float left, float right)
    {
        if(right > left){
            rotateGrabber.setPower(right);
        }else if(left > right){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            rotateGrabber.setPower(-left);
        }else{
            rotateGrabber.setPower(0);
        }
<<<<<<< HEAD
    }*/
=======
    }

    public static boolean isSkystoneVisible()
    {
        return true;
    }

>>>>>>> df36624dbfe63ded08321f49380742554375be1c

    //Drive is the central movement and robot handling method of the code
    //Its parameters are l (forward component), r (rotational component), and s (skating component)
    //The method creates a list of the sums of each parameter multiplied by the i index of the
    //forward, rotational and horizontal multiplier arrays
    //Any resulting values above .9 are rounded down to .9 (any higher value might cause the robot
    //to crash) and used to set the power of each of the motors
<<<<<<< HEAD
    public static void drive( float l, float r, float s ){
=======
    public static void drive(float l, float r, float s){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
        float[] sums = new float[4];
        float[] forwardMultiplier = { -1f, 1f, -1f, 1f };
        float[] rotationalMultiplier = { 1f, 1f, 1f, 1f };
        float[] horizontalMultiplier = { 1f, 1f, -1f, -1f };

<<<<<<< HEAD
        for( int i = 0; i < 4; i++ ){
            sums[i] += forwardMultiplier[i] * l + rotationalMultiplier[i] * r + horizontalMultiplier[i] * s;
        }

        for( int i = 0; i < 4; i++ ){
            if( sums[i] > .9 ){
                sums[i] = .9f;
=======
        for(int i = 0; i < 4; i++){
            sums[i] += forwardMultiplier[i] * l + rotationalMultiplier[i] * r + horizontalMultiplier[i] * s;
        }

        for(int i = 0; i < 4; i++){
            if (sums[i] > 1f){
                sums[i] = 1f;
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            }
        }

        frontLeft.setPower(sums[0]);
        frontRight.setPower(sums[1]);
        backLeft.setPower(sums[2]);
        backRight.setPower(sums[3]);
        /* telemetry.addData("Front Left", sums[0]);
        telemetry.addData("Front Right", sums[1]);
        telemetry.addData("Back Left", sums[2]);
        telemetry.addData("Back Right", sums[3]); */
    }

    //drive method for auto using encoders
    //float scalar to chose direction+power
    //float distance in CM is the magnitude of the distance traveled forwards or backwards
    //use this method if and only if no other sensors can be used to complete the motion
<<<<<<< HEAD
    public static void driveForEncoders( float distanceInMM, boolean sensor ){
        float startPosition = backLeft.getCurrentPosition();
        float num = distanceInMM;
        while( ( Math.abs(startPosition - ( backLeft.getCurrentPosition() + backRight.getCurrentPosition() - frontRight.getCurrentPosition() - frontLeft.getCurrentPosition() ) / 4f) < ( ( distanceInMM / 31.9f ) * 10 ) * 1120f * TRACTION_SCALER + startPosition ) && !sensor ){
            drive(-.5f * Math.abs(num) / distanceInMM, 0, 0);
            if( startPosition - backLeft.getCurrentPosition() < ( ( distanceInMM / 31.9f ) * 10 ) * 1120f * TRACTION_SCALER + startPosition - ( distanceInMM * .20 ) ){
                num *= .95;
            }
        }

=======
//    public static void driveForEncoders(float distanceInCM, float scalar){
//        float startPosition = (backLeft.getCurrentPosition()+frontLeft.getCurrentPosition()+frontRight.getCurrentPosition()+backRight.getCurrentPosition())/4f;
//        while(Math.abs((backLeft.getCurrentPosition()+frontLeft.getCurrentPosition()+frontRight.getCurrentPosition()+backRight.getCurrentPosition())/4f) < (distanceInCM / 31.9f) * 1120f + startPosition){
//            drive(scalar, 0, 0);
//        }
//
//        drive(0, 0, 0);
//    }
    public static Float getEncoderValue()
    {
        return (backLeft.getCurrentPosition() + frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backRight.getCurrentPosition()) / 4f;
    }

    public static void driveForEncoders(float startPos, float curPos, float distGoal)
    {
        if (Math.abs(curPos) - startPos < Math.abs(distGoal))
        {
            drive(distGoal / Math.abs(distGoal) * .5f, 0, 0);
            driveForEncoders(startPos, getEncoderValue(), distGoal);
        }

        drive(0, 0, 0);
    }

    public static void strafeForEncoders(float startPos, float curPos, float distGoal)
    {
        if (Math.abs(curPos) - startPos < Math.abs(distGoal))
        {
            drive(0, distGoal / Math.abs(distGoal) * .5f, 0);
            driveForEncoders(startPos, getEncoderValue(), distGoal);
        }
>>>>>>> df36624dbfe63ded08321f49380742554375be1c

        drive(0, 0, 0);
    }

<<<<<<< HEAD
    public static void strafeForEncoders( float distanceInMM, boolean sensor ){

        float startPosition = backLeft.getCurrentPosition();
        float num = distanceInMM;

        while( ( Math.abs(startPosition - ( backLeft.getCurrentPosition() + backRight.getCurrentPosition() - frontRight.getCurrentPosition() - frontLeft.getCurrentPosition() ) / 4f) < ( ( distanceInMM / 31.9f ) * 10 ) * 1120f * TRACTION_SCALER + startPosition ) && !sensor ){
            drive(0, 0, -.5f * Math.abs(num) / distanceInMM);
            if( startPosition - backLeft.getCurrentPosition() < ( ( distanceInMM / 31.9f ) * 10 ) * 1120f * TRACTION_SCALER + startPosition - ( distanceInMM * .20 ) ){
                num *= .95;
            }
        }

        drive(0, 0, 0);
    }

    public static void encodersInit()//slap this in the init of classes that wanna use encoders
    {
=======
//    public static Float encoderHelper(float startPosition, float mm) {
//
////        float travel = (startPosition - (backLeft.getCurrentPosition() + backRight.getCurrentPosition() - frontRight.getCurrentPosition() - frontLeft.getCurrentPosition()) / 4f);
////        float compare = ((mm / 31.9f) * 10) * 1120f * TRACTION_SCALER + startPosition;
////        if (startPosition - ((backLeft.getCurrentPosition() + backRight.getCurrentPosition() + frontLeft.getCurrentPosition() + frontRight.getCurrentPosition()) / 4f) < ((mm / 31.9f) * 10) * 1120f * TRACTION_SCALER + startPosition - (mm * .20)){
////            travel *= .95;
////        }
////        if (travel <= compare)
////            return travel;
////        else
////            return mm;
//    }


    //drive method for auto using encoders
    //float scalar to chose direction+power
    //float distance in CM is the magnitude of the distance traveled left or right
    //use this method if and only if no other sensors can be used to complete the motion
    // public static void strafeForEncoders(float distanceInCM, float scalar)
    // {
    //     float startPosition = backLeft.getCurrentPosition();
    //     while (Math.abs(startPosition - backLeft.getCurrentPosition()) < (distanceInCM / 31.9f) * 1120f * TRACTION_SCALER + startPosition)
    //         drive(0, 0, scalar);

    //     drive(0, 0, 0);
    // }

//    public static void strafeForEncoders(float distanceInMM, boolean sensor){
//
//        float startPosition = (backLeft.getCurrentPosition()+frontLeft.getCurrentPosition()+frontRight.getCurrentPosition()+backRight.getCurrentPosition())/4f;
//        float num = distanceInMM;
//
//        while((Math.abs(startPosition - (backLeft.getCurrentPosition()+backRight.getCurrentPosition()-frontRight.getCurrentPosition()-frontLeft.getCurrentPosition())/4f) < ((distanceInMM / 31.9f) * 10) * 1120f * TRACTION_SCALER + startPosition)
//                && !sensor){
//            drive(0, 0, .5f * Math.abs(num) / distanceInMM);
//            if(startPosition - backLeft.getCurrentPosition() < ((distanceInMM / 31.9f) * 10) * 1120f * TRACTION_SCALER + startPosition - (distanceInMM*.20)){
//                num *= .95;
//            }
//        }
//
//        drive(0, 0, 0);
//    }

    public static void encodersInit(){//slap this in the init of classes that wanna use encoders
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

<<<<<<< HEAD
    //drive method for auto using encoders
    //float scalar to chose direction+power
    //float distance in CM is the magnitude of the distance traveled left or right
    //use this method if and only if no other sensors can be used to complete the motion
=======
    public static boolean isUnderBridge(int gray, boolean blue){//tells if under bidge or not (basic version)
        int minColor = (int)(gray*1.3);//number 1.3 worked on old robot in Lab. Expect change with new robot
        if(color.blue() > minColor && blue){
            return true;
        }if(color.red() > minColor && !blue){
            return true;
        }else{
            return false;
        }
    }

>>>>>>> df36624dbfe63ded08321f49380742554375be1c

    /**
     * needs to be called every time through loop
     *
     * @param motor    the target motor that will be rotating
     * @param finalPos desired final rotation of the motor in encoder steps, can be positive or negative
     */
<<<<<<< HEAD
    public static void rotateMotorToPosition( DcMotor motor, float finalPos ){

        // TODO: may cause overshooting, should probably be changed
        if( finalPos > 0 && motor.getCurrentPosition() < finalPos ){
            motor.setPower(0.9);
        }else if( finalPos < 0 && motor.getCurrentPosition() > finalPos ){
=======
    public static void rotateMotorToPosition(DcMotor motor, float finalPos){

        // TODO: may cause overshooting, should probably be changed
        if(finalPos > 0 && motor.getCurrentPosition() < finalPos){
            motor.setPower(0.9);
        }

        else if(finalPos < 0 && motor.getCurrentPosition() > finalPos){
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
            motor.setPower(-0.9);
        }
    }

    /**
     * moves the stone lift to a target position
     *
     * @param finalPos target lift position in mm
     */
<<<<<<< HEAD
    public static void moveLiftToPosition( float finalPos ){
        int finalPosInSteps = (int) ( ( finalPos / MM_PER_LIFT_ROTATION * ENCODER_STEPS_PER_ROTATION ) + 0.5 );
=======
    public static void moveLiftToPosition(float finalPos){
        int finalPosInSteps = (int)((finalPos / MM_PER_LIFT_ROTATION * ENCODER_STEPS_PER_ROTATION) + 0.5);
>>>>>>> df36624dbfe63ded08321f49380742554375be1c
        rotateMotorToPosition(lift, finalPosInSteps);
    }


    // TODO: make this use a PID controller

    public void hardwareInit(){
        frontLeft = hardwareMap.dcMotor.get("m0");
        frontRight = hardwareMap.dcMotor.get("m1");
        backLeft = hardwareMap.dcMotor.get("m2");
        backRight = hardwareMap.dcMotor.get("m3");

        lift = hardwareMap.dcMotor.get("l0");
        intakeOne = hardwareMap.dcMotor.get("l1");
        intakeTwo = hardwareMap.dcMotor.get("l2");

        platform = hardwareMap.servo.get("s0");
        grabber = hardwareMap.servo.get("s1");
        rotateGrabber = hardwareMap.crservo.get("s2");

        color = hardwareMap.colorSensor.get("color1");
        front1 = hardwareMap.touchSensor.get("touch1");
        front2 = hardwareMap.touchSensor.get("touch2");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //liftGivenControllerValues.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //liftGivenControllerValues.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        mode = DRIVING.Fast;
    }

    public enum DRIVING{
        Slow, Medium, Fast;

        public DRIVING getNext(){
            return values()[( ordinal() + 1 ) % values().length];
        } // change driving mode
    }


}