package org.firstinspires.ftc.teamcode.challengeVortex.blake;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.challengeVortex.duval.subClasses.LauncherControlFull;

/**
 * Created by mbca on 1/21/17.
 */
@Autonomous(name = "Blue Corner + Yoga Ball  - White Battery")
@Disabled

public class AutonomousBlueCornerYogaBallWhiteBattery extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftDriveMotor;
    DcMotor rightDriveMotor;

    DcMotor leftLauncherMotor;
    DcMotor rightLauncherMotor;

    double leftLaunchPower = 0;
    double rightLaunchPower = 0;
    double launchStartPowerIncrement = 0.004;
    double launchStopPowerIncrement = 0.001;
    double maximumLauncherPower = 0.4;
    double minimumLauncherPower = 0;

    DcMotor beltMotor;

    Servo yogaArm;

    double yogaArmDownPosition = 0.825;
    double yogaArmUpPosition = 0.215;

    double beltPower = 0;
    double beltStartPowerIncrement = 0.05;
    double beltStopPowerIncrement = 0.01;
    double maximumForwardBeltPower = 0.3;
    double minimumBeltPower = 0;
    double maximumReverseBeltPower = -1.0;
    double forwardRunningBeltPower = 0.1;
    double reverseRunningBeltPower = -0.1;

    Servo leftArm;
    Servo rightArm;

    final static double leftArmUpPosition = 0.26;
    final static double rightArmUpPosition = 0.86;
    final static double leftArmDownPosition = 0.94;
    final static double rightArmDownPosition = 0.18;

    double leftGateArmClosedPositionForLaunch = .57; // These values are only place-holder values
    double leftGateArmClosedPosition = .715;
    double leftGateArmOpenPosition = 0.1; // You will need to experiment with these values to find the perfect servo positions
    double rightGateArmClosedPositionForLaunch = 0.43;
    double rightGateArmClosedPosition = .29;
    double rightGateArmOpenPosition = 0.9;

    Servo leftGateArm;
    Servo rightGateArm;

    LauncherControlFull launcherControl;


    @Override
    public void runOpMode() throws InterruptedException {

        launcherControl = new LauncherControlFull(hardwareMap.dcMotor.get("left_launcher"), hardwareMap.dcMotor.get("right_launcher"), hardwareMap.dcMotor.get("conveyor_belt"));

        leftDriveMotor = hardwareMap.dcMotor.get("left_drive");
        rightDriveMotor = hardwareMap.dcMotor.get("right_drive");
        leftDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        leftLauncherMotor = hardwareMap.dcMotor.get("left_launcher");
        rightLauncherMotor = hardwareMap.dcMotor.get("right_launcher");
        rightLauncherMotor.setDirection(DcMotor.Direction.REVERSE);

        beltMotor = hardwareMap.dcMotor.get("conveyor_belt");

        yogaArm = hardwareMap.servo.get("yoga_arm");
        yogaArm.setPosition(yogaArmDownPosition);

        leftGateArm = hardwareMap.servo.get("left_gate_arm"); // Don't forget to program left and right _gate_arm into the RC phone as servos
        rightGateArm = hardwareMap.servo.get("right_gate_arm"); // Or else the phone won't be able to communicate with the program (stuff won't work)
        leftGateArm.setPosition(leftGateArmClosedPosition); // Also, while doing this, don't forget to delete the old gate_arm which was the dcMotor registered in the RC phone
        rightGateArm.setPosition(rightGateArmClosedPosition); // Remember that it matters how you plug in the servos to the module; it depends on how you assigned them in the RC phone

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            leftDriveMotor.setPower(-.5);
            rightDriveMotor.setPower(-.5);
//distance to first line to launch
            sleep(950);

            leftDriveMotor.setPower(0);
            rightDriveMotor.setPower(0);

            sleep(100);

            launcherControl.startLauncherWhiteBattery();

/*
            while (leftLaunchPower < maximumLauncherPower || rightLaunchPower < maximumLauncherPower) {
                if (leftLaunchPower < maximumLauncherPower) {
                    leftLaunchPower = leftLaunchPower + launchStartPowerIncrement;
                }
                if (rightLaunchPower < maximumLauncherPower) {
                    rightLaunchPower = rightLaunchPower + launchStartPowerIncrement;
                }
                leftLauncherMotor.setPower(leftLaunchPower);
                rightLauncherMotor.setPower(rightLaunchPower);
            }


*/

//time for launcher to start
            sleep(1000);

            leftGateArm.setPosition(leftGateArmClosedPositionForLaunch); // Also, while doing this, don't forget to delete the old gate_arm which was the dcMotor registered in the RC phone
            rightGateArm.setPosition(rightGateArmClosedPositionForLaunch); // Remember that it matters how you plug in the servos to the module; it depends on how you assigned them in the RC phone

            launcherControl.conveyorBeltStart();
            //beltMotor.setPower(maximumForwardBeltPower);

// time for belt to run
            sleep(7000);


            launcherControl.conveyorBeltStop();
            //beltMotor.setPower(0);

            launcherControl.stopLauncher();

/*
            while (leftLaunchPower > 0 || rightLaunchPower > 0) {
                if (leftLaunchPower > 0) {
                    leftLaunchPower = leftLaunchPower - launchStopPowerIncrement;
                }
                if (rightLaunchPower > 0) {
                    rightLaunchPower = rightLaunchPower - launchStopPowerIncrement;
                }
                leftLauncherMotor.setPower(leftLaunchPower);
                rightLauncherMotor.setPower(rightLaunchPower);
            }

*/

            //closes the arms to make sure doesn't interfere with going to walls.
            leftGateArm.setPosition(leftGateArmClosedPosition); // Also, while doing this, don't forget to delete the old gate_arm which was the dcMotor registered in the RC phone
            rightGateArm.setPosition(rightGateArmClosedPosition); // Remember that it matters how you plug in the servos to the module; it depends on how you assigned them in the RC phone

            leftDriveMotor.setPower(-.5);
            rightDriveMotor.setPower(-.5);
//time to go forward from launch point to the yoga ball / center vortex
            sleep(800);

            leftDriveMotor.setPower(0);
            rightDriveMotor.setPower(0);

            yogaArm.setPosition(yogaArmUpPosition);

//time to stop and raise arms to knock yoga ball off center vortex
            sleep(1600);

            leftDriveMotor.setPower(.3);
            rightDriveMotor.setPower(-.3);
//time to turn LEFT
            sleep(150);
            leftDriveMotor.setPower(0);
            rightDriveMotor.setPower(0);
//sits still after turning before moving forward
            sleep(300);


            leftDriveMotor.setPower(-.5);
            rightDriveMotor.setPower(-.5);
//time to go forward on to the center vortex
            sleep (450);

            leftDriveMotor.setPower(0);
            rightDriveMotor.setPower(0);

            yogaArm.setPosition(yogaArmDownPosition);

            sleep(1000);

//stops OpMode
            requestOpModeStop();
        }
    }
}

