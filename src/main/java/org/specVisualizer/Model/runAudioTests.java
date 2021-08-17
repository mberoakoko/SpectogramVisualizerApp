package org.specVisualizer.Model;

import org.specVisualizer.Model.Audio.SoundRecordUtil;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class runAudioTests {

    private static final int RECORD_TIME = 5;   // 60 seconds
    public static void main(String[] args) {
        File wavFile = new File("src/main/resources/Audio/sample_1.wav");

        SoundRecordUtil recordUtil = new SoundRecordUtil();
        Runnable record = () -> {
            System.out.println("Started Record...");
            try {
                recordUtil.startRecord();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        };

        Runnable stopRecord = ()-> {
            System.out.println("Stopping record");
            recordUtil.stopRecord();
            try {
                recordUtil.saveRecording(wavFile);
                System.out.println("Stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        /*ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.schedule(record, 30, TimeUnit.MILLISECONDS);
        executorService.schedule(stopRecord, RECORD_TIME, TimeUnit.SECONDS);
        executorService.shutdown();*/

        try {
            Clip clip = SoundRecordUtil.loadAudioClip("src/main/resources/Audio/Record.wav");
            SoundRecordUtil.playAudio(clip, 5);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }


    }
}
