package org.specVisualizer.Model.Audio;

import org.specVisualizer.Model.MathUtil.Complex;
import org.specVisualizer.Model.MathUtil.FastFourierTransform;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.sampled.*;

public class SoundRecordUtil {
    private static final  int BUFFER_SIZE = 4096;
    private ByteArrayOutputStream recordBytes;
    private TargetDataLine audioLine;
    private AudioFormat format;
    private boolean isRunning;




    private static final float AUDIO_VOLUME = 5.0f;
    private static final float DECAY_FACTOR = 0.12f;

    private AudioFormat createAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
                bigEndian);
    }
    public void startRecord() throws LineUnavailableException {
        format = createAudioFormat();
        DataLine.Info  info = new DataLine.Info(TargetDataLine.class, format);
        if(!AudioSystem.isLineSupported(info))
            throw new LineUnavailableException("The system doesn't output specified format");
        audioLine = AudioSystem.getTargetDataLine(format);
        audioLine.open(format);
        audioLine.start();
        byte [] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        recordBytes = new ByteArrayOutputStream();
        isRunning = true;

        while (isRunning){
            System.out.println(Arrays.toString(buffer));
            Complex [] x = new Complex[BUFFER_SIZE];
            for (int i = 0; i < buffer.length; i++) {
                x[i] = new Complex(buffer[i], 0 );
            }
            Complex [] fourierTransform = FastFourierTransform.FFT(x);
            System.out.println(Arrays.toString(fourierTransform));
            bytesRead = audioLine.read(buffer, 0, buffer.length);
            recordBytes.write(buffer, 0, bytesRead);

        }


    }
    public void stopRecord(){
        System.out.println("Stopping Recording");
        isRunning = false;
        if(audioLine != null){
            System.out.println("Closing AudioLine " );
            //audioLine.drain();
            audioLine.close();
            System.out.println("AudioLine closed");
        }
    }

    public void saveRecording(File wavFile) throws IOException {
        System.out.println("Saving Recording");
        byte [] audioData = recordBytes.toByteArray();
        ByteArrayInputStream bias = new ByteArrayInputStream(audioData);
        AudioInputStream audioInputStream = new AudioInputStream(bias, format, audioData.length/format.getFrameSize() );

        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, wavFile);
        audioInputStream.close();
        recordBytes.close();
    }

    public static Clip loadAudioClip(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = null;
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File(path));
        AudioFormat format = stream.getFormat();
        clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
        clip.open(stream);
        return clip;

    }

    public static void playAudio(Clip clip, float distance) {
        FloatControl volume = (FloatControl) clip
                .getControl(FloatControl.Type.MASTER_GAIN);

        float volumeAmount = AUDIO_VOLUME - (distance * distance * DECAY_FACTOR);

        if (volumeAmount < -80) volumeAmount = -80;
        volume.setValue(volumeAmount);

        //if (clip.isRunning())  clip.stop();

        clip.setFramePosition(0);
        clip.start();
    }

}











