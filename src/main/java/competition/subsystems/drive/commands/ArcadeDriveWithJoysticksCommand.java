package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.operator_interface.OperatorInterface;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    OperatorInterface operatorInterface;
    DriveSubsystem drive;

    private double clamp(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        this.operatorInterface = oi;
        this.drive = driveSubsystem;
        this.addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double rotation = operatorInterface.gamepad.getLeftStickX();
        double velocity = operatorInterface.gamepad.getLeftStickY();

        double leftPower  = clamp(velocity - rotation, -1, 1);
        double rightPower = clamp(velocity + rotation, -1, 1);

        drive.tankDrive(leftPower, rightPower);
    }

}
