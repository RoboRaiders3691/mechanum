/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//examplecode

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  Joystick joy = new Joystick(0);

  Spark br = new Spark(4);
  Spark fr = new Spark(2);
  Spark bl = new Spark(3);
  Spark fl = new Spark(1);

  float spd = 0.35f;
  float side = 1.3f;

  int drivetype = 2;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if (drivetype == 1) {
      if (!joy.getRawButton(1)) {
        fr.set(spd * (joy.getRawAxis(5)));
        fl.set(spd * (-joy.getRawAxis(1)));
        br.set(spd * (joy.getRawAxis(5)));
        bl.set(spd * (-joy.getRawAxis(1))); 
      }     
      else if (joy.getRawButton(1)) {
        fr.set(spd * side * joy.getRawAxis(0));
        fl.set(spd * side * joy.getRawAxis(0));
        br.set(spd * -side * joy.getRawAxis(0));
        bl.set(spd * -side * joy.getRawAxis(0));
      }
    }
    else if (drivetype == 2) {
      fr.set(spd * (joy.getRawAxis(5) + side * joy.getRawAxis(4)));
      fl.set(spd * (-joy.getRawAxis(1) + side * joy.getRawAxis(0)));
      br.set(spd * (joy.getRawAxis(5) - side * joy.getRawAxis(4)));
      bl.set(spd * (-joy.getRawAxis(1) - side * joy.getRawAxis(0)));
    }
    if (joy.getRawButton(3)) {
      drivetype = 1;
    }
    else if (joy.getRawButton(4)) {
      drivetype = 2;
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
