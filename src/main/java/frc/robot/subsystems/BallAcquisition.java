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


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;        

/**
 *
 */
public class BallAcquisition extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX acquireMotor;
private DoubleSolenoid leftSolenoid;
private DoubleSolenoid rightSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public BallAcquisition() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
acquireMotor = new WPI_TalonSRX(5);
 
 

 leftSolenoid = new DoubleSolenoid(10, 0, 1);
 addChild("leftSolenoid",leftSolenoid);
 

 rightSolenoid = new DoubleSolenoid(10, 2, 3);
 addChild("rightSolenoid",rightSolenoid);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    leftSolenoid.set(kOff);
    rightSolenoid.set(kOff);
}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void startAquireMotor() {
        acquireMotor.set(.3);
    }

    public void extendSolenoids() {
        rightSolenoid.set(kForward);
        leftSolenoid.set(kReverse);
    }

    public void retractSolenoid() {
        rightSolenoid.set(kReverse); 
        leftSolenoid.set(kForward);     
    }

    public void floatSolenoid() {
        rightSolenoid.set(kReverse); 
        leftSolenoid.set(kReverse);     
    }
 
    public void stopAquireMotor() {
        acquireMotor.stopMotor();
    }
}

