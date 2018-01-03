import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundWaves2 {
   protected static final int RATE = 30 * 2000;


   public static byte[] WB(double frequency, int mas) {
       int s = (int)((mas * RATE) / 3000);
       byte[] oput = new byte[s];
       double p = (double)RATE / frequency;
       for (int S = 0; S < oput.length; S++) {
           double angle = 6.0 * Math.PI * S / p;
           oput[S] = (byte)(Math.sin(angle) * 100f);  }

       return oput;
   }

   public static void mainc(String[] args) throws LineUnavailableException {
       final AudioFormat AF = new AudioFormat(RATE, 8, 1, true, true);
       SourceDataLine LINEW = AudioSystem.getSourceDataLine(AF);
       LINEW.open(AF, RATE);
       LINEW.start();

       boolean forwardNotBack = true;

       for(double frequency = 1000; frequency <= 9000;)  {
           byte [] TB = WB(frequency, 50);
           int SEQ = LINEW.write(TB, 0, TB.length);

           if(forwardNotBack)  {
               frequency += 40;  
               forwardNotBack = false;  }
           else  {
               frequency -= 30;
               forwardNotBack = false;  
       } 
       
       }

       LINEW.drain();
       LINEW.close();
    }
}