package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Measuring Tape Autonomous 10b")
public class MeasuringTapeAuto10 extends Library{

    enum State{
        PARKING, END
    }

    State currentState;
    ElapsedTime clock = new ElapsedTime();
    boolean moving = false;
    private final double SCALE_FACTOR = 255;
    private float[] hsvValues = { 0F, 0F, 0F };

    @Override
    public void init(){
        hardwareInit();
        currentState = State.PARKING;
    }

    public void loop(){
        /*
        Loop constantly checks state, and then executes a command based on this.
        */
        if( currentState == State.PARKING ){
            Parking();
        }
        if( currentState == State.END ){
            Stop();
        }

        Telemetry();
    }

    public void Parking(){
        if( !moving ){
            clock.reset();
            moving = true;
        }else if( clock.seconds() <= 10 ){
            tapeMeasure.setPower(1);
        }else{
            tapeMeasure.setPower(0);
            currentState = State.END;
        }
        //        if(!moving){
        //            clock.reset();
        //            moving = true;
        //        }else if(hsvValues[0] <= 60 || clock.seconds()>=6){
        //            moving = false;
        //            drive(0,0,0);
        //            currentState = State.END;
        //        }else if(clock.seconds()>=5){
        //            drive(0, 0, -.3f);
        //        }else{
        //            drive(0,0,.4f);
        //        }
        //
        //        if( distanceLeft.getDistance(DistanceUnit.CM)>8 || distanceRight.getDistance(DistanceUnit.CM)>8){
        //            drive(.3f,0,0);
        //        }
    }

    public void Stop(){
        moving = false;
        drive(0, 0, 0);
    }

    private void Telemetry(){
        telemetry.addData("Millis since State Start: ", clock.seconds());
        telemetry.addData("State: ", currentState);
        telemetry.addData("Distamce Left: ", distanceLeft.getDistance(DistanceUnit.CM));
        telemetry.addData("Distamce Right: ", distanceRight.getDistance(DistanceUnit.CM));
    }
}