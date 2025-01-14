// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final BallAcquisition m_ballAcquisition = new BallAcquisition();
    public final BallShooter m_ballShooter = new BallShooter();
    public final BallIndexer m_ballIndexer = new BallIndexer();

// Joysticks
private final Joystick testJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton btnRetractSolenoid = new JoystickButton(testJoystick, 8);        
btnRetractSolenoid.whenPressed(new retractSolenoid( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRetractSolenoid",new retractSolenoid( m_ballAcquisition ) );

final JoystickButton btnFireSolenoid = new JoystickButton(testJoystick, 7);        
btnFireSolenoid.whenPressed(new fireSolenoid( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnFireSolenoid",new fireSolenoid( m_ballAcquisition ) );

final JoystickButton btnStopShootMotor = new JoystickButton(testJoystick, 3);        
btnStopShootMotor.whenReleased(new stopShootMotor( m_ballShooter ) ,true);
    SmartDashboard.putData("btnStopShootMotor",new stopShootMotor( m_ballShooter ) );

final JoystickButton btnStartShootMotor = new JoystickButton(testJoystick, 3);        
btnStartShootMotor.whileHeld(new startShootMotor( m_ballShooter ) ,true);
    SmartDashboard.putData("btnStartShootMotor",new startShootMotor( m_ballShooter ) );

final JoystickButton btnStopGateMotor = new JoystickButton(testJoystick, 6);        
btnStopGateMotor.whenReleased(new stopGateMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnStopGateMotor",new stopGateMotor( m_ballIndexer ) );

final JoystickButton btnStartGateMotor = new JoystickButton(testJoystick, 6);        
btnStartGateMotor.whileHeld(new startGateMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnStartGateMotor",new startGateMotor( m_ballIndexer ) );

final JoystickButton btnStopAquireMotor = new JoystickButton(testJoystick, 5);        
btnStopAquireMotor.whenReleased(new stopAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnStopAquireMotor",new stopAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnRunAcquireMotor = new JoystickButton(testJoystick, 5);        
btnRunAcquireMotor.whileHeld(new startAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRunAcquireMotor",new startAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnAdvanceBall = new JoystickButton(testJoystick, 2);        
btnAdvanceBall.whenPressed(new AdvanceBall( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnAdvanceBall",new AdvanceBall( m_ballIndexer ) );

final JoystickButton btnRightBeltStop = new JoystickButton(testJoystick, 11);        
btnRightBeltStop.whenReleased(new stopRightBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnRightBeltStop",new stopRightBeltMotor( m_ballIndexer ) );

final JoystickButton btnRightBeltStart = new JoystickButton(testJoystick, 11);        
btnRightBeltStart.whileHeld(new startRightBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnRightBeltStart",new startRightBeltMotor( m_ballIndexer ) );

final JoystickButton btnLeftBeltStop = new JoystickButton(testJoystick, 12);        
btnLeftBeltStop.whenReleased(new stopLeftBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnLeftBeltStop",new stopLeftBeltMotor( m_ballIndexer ) );

final JoystickButton btnLeftBeltStart = new JoystickButton(testJoystick, 12);        
btnLeftBeltStart.whileHeld(new startLeftBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnLeftBeltStart",new startLeftBeltMotor( m_ballIndexer ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getTestJoystick() {
        return testJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}

