package main.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class SoundManager {
    private Map<String, String> sounds;
    private String mainMusic;
    private String loseMusic;
    private Clip mainMusicClip;
    public static boolean isMainMusicPlaying = false;

    public SoundManager() {
        sounds = new HashMap<>();
        // Initialize different coin sounds
        sounds.put("coin1", "res/music/Dime1.wav");
        sounds.put("coin2", "res/music/Coin2.wav");
        sounds.put("coin3", "res/music/ChangeDrop1.wav");
        // Initialize other sounds
        mainMusic = "res/music/GameMusic.wav";
        loseMusic = "res/music/DeathSound.wav";
    }

    private void playSound(String soundFile) {
        try {
            File fileSound = new File(soundFile);
//            URL soundURL = getClass().getResource(soundFile);
//            if (soundURL == null) {
//                System.err.println("Sound not found: " + soundFile);
//                return;
//            }
//            play from /res/music folder
//            URL soundURL = getClass().getResource(soundFile);
            System.out.println("Playing sound: " + soundFile); // Debugging
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private Clip loopSound(String soundFile) {
        try {

            File fileSound = new File(soundFile);
            System.out.println("Looping sound: " + soundFile); // Debugging
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playCoinSound(int coinNumber) {
        String soundFile = sounds.get("coin" + coinNumber);
        if (soundFile != null) {
            playSound(soundFile);
        } else {
            System.out.println("Sound not found: coin" + coinNumber);
        }
    }

    public void playMainMusic() {
        if (isMainMusicPlaying) {
            return;
        } else{
            mainMusicClip = loopSound(mainMusic);
            isMainMusicPlaying = true;
        }
    }

    public void stopMainMusic() {
        if (mainMusicClip != null && mainMusicClip.isRunning()) {
            mainMusicClip.stop();
            isMainMusicPlaying = false;
        }
    }

    public void playLoseMusic() {
        playSound(loseMusic);
    }

    // Simulating game events
    public void playerCollectedCoin(int coinNumber) {
        System.out.println("Player collected coin " + coinNumber);
        playCoinSound(coinNumber);
    }

    public void playerLost() {
        System.out.println("Player lost the game");
        stopMainMusic();
        playLoseMusic();
    }

    public static SoundManager soundManager = new SoundManager();
}
