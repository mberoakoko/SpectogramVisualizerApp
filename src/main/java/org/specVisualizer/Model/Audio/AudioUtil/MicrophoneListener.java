package org.specVisualizer.Model.Audio.AudioUtil;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Date;
public class MicrophoneListener {
    private static final AudioFormat defaultAudioFormart = createAudioFormat();


    static AudioFormat createAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
                bigEndian);
    }


    public static void listenToMicrophone(ByteArrayOutputStream buffer) throws LineUnavailableException {
        int buffSize = 4096;
        TargetDataLine line = getTargetDataLine(defaultAudioFormart);
        line.open(defaultAudioFormart, buffSize);


    }




    public static TargetDataLine getTargetDataLine(AudioFormat format)
            throws LineUnavailableException {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            throw new LineUnavailableException();
        }
        return (TargetDataLine) AudioSystem.getLine(info);
    }
}
