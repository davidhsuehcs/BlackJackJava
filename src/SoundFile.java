import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundFile {

    Clip clip;

    //constructor - specify the filename as the parameter
    public SoundFile(String filename) {
        try {

            File soundFile = new File(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //plays the clip
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

}






