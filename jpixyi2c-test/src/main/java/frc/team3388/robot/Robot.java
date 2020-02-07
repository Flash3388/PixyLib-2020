package frc.team3388.robot;

import com.castle.code.NativeLibrary;
import com.castle.code.NativeLibraryFinder;
import com.castle.code.loader.NativeLibraryLoader;
import com.castle.code.loader.TempNativeLibraryLoader;
import com.castle.exceptions.CodeLoadException;
import com.castle.exceptions.FindException;
import com.castle.nio.temp.TempPathGenerator;
import com.castle.nio.zip.ArchivedNativeLibraryFinder;
import com.castle.nio.zip.Zip;
import com.castle.util.java.JavaSources;
import com.flash3388.flashlib.frc.robot.TimedFrcRobot;
import com.flash3388.flashlib.robot.scheduling.actions.Actions;
import com.flash3388.flashlib.time.Time;
import com.pixy.frc.Block;
import com.pixy.frc.Pixy;
import com.pixy.frc.PixyInstance;

public class Robot extends TimedFrcRobot {

    private Pixy mPixy;

    public Robot() {
        loadNatives();
    }

    @Override
    protected void robotInit() {
        mPixy = PixyInstance.getDefault();
    }

    @Override
    public void endCompetition() {
        super.endCompetition();
        mPixy.close();
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
        Actions.periodic(()-> {
            System.out.println("==========================");
            for (Block block : mPixy.getBlocks(20)) {
                System.out.println(block);
            }
        }, Time.milliseconds(500))
                .start();
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void robotPeriodic() {

    }

    private void loadNatives() {
        try {
            Zip zip = JavaSources.currentJar(Pixy.class);

            NativeLibraryLoader loader = new TempNativeLibraryLoader(new TempPathGenerator());
            NativeLibraryFinder finder = new ArchivedNativeLibraryFinder(zip);

            NativeLibrary nativeLibrary = finder.find("libpixyrio");
            loader.load(nativeLibrary);
        } catch (CodeLoadException | FindException e) {
            throw new Error(e);
        }
    }
}
