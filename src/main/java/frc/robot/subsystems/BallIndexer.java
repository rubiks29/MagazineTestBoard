// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BeltMotorConstants;
	
/**
 *
 */
public class BallIndexer extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BeltMotorConstants

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BeltMotorConstants

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX leftBeltMotor;
private WPI_TalonSRX rightBeltMotor;
private WPI_TalonSRX gateMotor;
private DigitalInput topSensor;
private DigitalInput bottomSensor;
private DigitalInput gateSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public BallIndexer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftBeltMotor = new WPI_TalonSRX(12);
 
 

rightBeltMotor = new WPI_TalonSRX(14);
 
 

gateMotor = new WPI_TalonSRX(15);
 
 

topSensor = new DigitalInput(9);
 addChild("topSensor",topSensor);
 

bottomSensor = new DigitalInput(8);
 addChild("bottomSensor",bottomSensor);
 

gateSensor = new DigitalInput(7);
 addChild("gateSensor",gateSensor);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		beltMotorConfig();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Left Encoder", rightBeltMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Encoder",leftBeltMotor.getSelectedSensorPosition(0));
		SmartDashboard.putBoolean("Gate Sensor", !gateSensor.get());
		SmartDashboard.putBoolean("Top Sensor", !topSensor.get());
		SmartDashboard.putBoolean("Bottom Sensor", !bottomSensor.get());
	

		if (!bottomSensor.get() && advanceBallComplete()) {
			advanceBall();
		}
	}

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void startLeftBeltMotor() {
        leftBeltMotor.set(.5);
    }
    public void startRightBeltMotor() {
        rightBeltMotor.set(.5);
    }
    public void stopLeftBeltMotor() {
        leftBeltMotor.stopMotor();
    }
    public void stopRightBeltMotor() {
        rightBeltMotor.stopMotor();
	}

	public void startGateMotor() {
		gateMotor.set(.5);
	}
	public void stopGateMotor() {
		gateMotor.stopMotor();
	}
	
	public void advanceBall() {
		System.out.println("This is Advance Ball");
	
		/* Determine which slot affects which PID */
		rightBeltMotor.selectProfileSlot(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.PID_PRIMARY);
		rightBeltMotor.selectProfileSlot(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.PID_TURN);

	
		/* Set target */
		double speed = .3;
		double currentPosition =  rightBeltMotor.getSelectedSensorPosition(0);
		double target_sensorUnits = speed * BeltMotorConstants.kSensorUnitsPerRotation * BeltMotorConstants.kRotationsToTravel  + currentPosition;
		double target_turn = 0;

		SmartDashboard.putNumber("Target Sensor",target_sensorUnits);
	
		/* Configured for Position Closed loop on Quad Encoders' Sum and Auxiliary PID on Quad Encoders' Difference */
		rightBeltMotor.set(ControlMode.Position, target_sensorUnits, DemandType.AuxPID, target_turn);
		leftBeltMotor.follow(rightBeltMotor, FollowerType.AuxOutput1);
	}

	public boolean advanceBallComplete() {
		return rightBeltMotor.getClosedLoopError() < 500;
	}

    public void beltMotorConfig() {
        /* Disable all motor controllers */
		rightBeltMotor.set(ControlMode.PercentOutput, 0);
		leftBeltMotor.set(ControlMode.PercentOutput, 0);

		/* Factory Default all hardware to prevent unexpected behaviour */
		rightBeltMotor.configFactoryDefault();
		leftBeltMotor.configFactoryDefault();
		
		/* Set Neutral Mode */
		leftBeltMotor.setNeutralMode(NeutralMode.Brake);
		rightBeltMotor.setNeutralMode(NeutralMode.Brake);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor as local QuadEncoder */
		leftBeltMotor.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source
													BeltMotorConstants.PID_PRIMARY,					// PID Slot for Source [0, 1]
													BeltMotorConstants.kTimeoutMs);					// Configuration Timeout

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		rightBeltMotor.configRemoteFeedbackFilter(leftBeltMotor.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	// Remote Feedback Source
												BeltMotorConstants.REMOTE_1,							// Source number [0, 1]
												BeltMotorConstants.kTimeoutMs);						// Configuration Timeout
		
		/* Setup Sum signal to be used for Distance */
		rightBeltMotor.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor1, BeltMotorConstants.kTimeoutMs);				// Feedback Device of Remote Talon
		rightBeltMotor.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, BeltMotorConstants.kTimeoutMs);	// Quadrature Encoder of current Talon
		
		/* Setup Difference signal to be used for Turn */
		rightBeltMotor.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor1, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, BeltMotorConstants.kTimeoutMs);
		
		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		rightBeltMotor.configSelectedFeedbackSensor(	FeedbackDevice.SensorSum, 
													BeltMotorConstants.PID_PRIMARY,
													BeltMotorConstants.kTimeoutMs);
		
		/* Scale Feedback by 0.5 to half the sum of Distance */
		rightBeltMotor.configSelectedFeedbackCoefficient(	0.5, 						// Coefficient
														BeltMotorConstants.PID_PRIMARY,		// PID Slot of Source 
														BeltMotorConstants.kTimeoutMs);		// Configuration Timeout
		
		/* Configure Difference [Difference between both QuadEncoders] to be used for Auxiliary PID Index */
		rightBeltMotor.configSelectedFeedbackSensor(	FeedbackDevice.SensorDifference, 
													BeltMotorConstants.PID_TURN, 
													BeltMotorConstants.kTimeoutMs);
		
		/* Scale the Feedback Sensor using a coefficient */
		rightBeltMotor.configSelectedFeedbackCoefficient(	1,
														BeltMotorConstants.PID_TURN, 
														BeltMotorConstants.kTimeoutMs);
		
		/* Configure output and sensor direction */
		leftBeltMotor.setInverted(true);
	//	leftBeltMotor.setSensorPhase(true);
	//	rightBeltMotor.setInverted(true);
		rightBeltMotor.setSensorPhase(false);
		
		/* Set status frame periods to ensure we don't have stale data */
		rightBeltMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, BeltMotorConstants.kTimeoutMs);
		leftBeltMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, BeltMotorConstants.kTimeoutMs);

		/* Configure neutral deadband */
		rightBeltMotor.configNeutralDeadband(BeltMotorConstants.kNeutralDeadband, BeltMotorConstants.kTimeoutMs);
		leftBeltMotor.configNeutralDeadband(BeltMotorConstants.kNeutralDeadband, BeltMotorConstants.kTimeoutMs);

		/* Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		leftBeltMotor.configPeakOutputForward(+1.0, BeltMotorConstants.kTimeoutMs);
		leftBeltMotor.configPeakOutputReverse(-1.0, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.configPeakOutputForward(+1.0, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.configPeakOutputReverse(-1.0, BeltMotorConstants.kTimeoutMs);

		/* FPID Gains for distance servo */
		rightBeltMotor.config_kP(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kP, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kI(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kI, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kD(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kD, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kF(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kF, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_IntegralZone(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kIzone, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.configClosedLoopPeakOutput(BeltMotorConstants.kSlot_Distanc, BeltMotorConstants.kGains_Distanc.kPeakOutput, BeltMotorConstants.kTimeoutMs);

		/* FPID Gains for turn servo */
		rightBeltMotor.config_kP(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kP, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kI(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kI, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kD(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kD, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_kF(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kF, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.config_IntegralZone(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kIzone, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.configClosedLoopPeakOutput(BeltMotorConstants.kSlot_Turning, BeltMotorConstants.kGains_Turning.kPeakOutput, BeltMotorConstants.kTimeoutMs);
			
		/* 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
        int closedLoopTimeMs = 1;
        rightBeltMotor.configClosedLoopPeriod(0, closedLoopTimeMs, BeltMotorConstants.kTimeoutMs);
        rightBeltMotor.configClosedLoopPeriod(1, closedLoopTimeMs, BeltMotorConstants.kTimeoutMs);

		/* configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		rightBeltMotor.configAuxPIDPolarity(false, BeltMotorConstants.kTimeoutMs);

		/* Initialize */
	//	_firstCall = true;
	//	_state = false;
		zeroSensors();
    }

    	/* Zero quadrature encoders on Talons */
	void zeroSensors() {
		leftBeltMotor.getSensorCollection().setQuadraturePosition(0, BeltMotorConstants.kTimeoutMs);
		rightBeltMotor.getSensorCollection().setQuadraturePosition(0, BeltMotorConstants.kTimeoutMs);
		System.out.println("[Quadrature Encoders] All sensors are zeroed.\n");
	}

}

