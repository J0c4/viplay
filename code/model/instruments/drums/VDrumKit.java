package code.model.instruments.drums;

import code.model.instruments.VInstrument;
import code.model.instruments.VInstrumentBuilder;
/**
 *
 * @author José Carlos
 */
public class VDrumKit extends VInstrument
{
    /** Drum kit sounds */
    public static String BASS_DRUM_AIF = "resources/sounds/drums/drums/DrumMetal.aif";
    public static String BASS_DRUM_WAV = "resources/sounds/drums/drums/DrumMetal2.wav";
    public static String SNARE_WAV = "resources/sounds/drums/snares/SnareMetal.wav";
    public static String TOM_YAMAHA_HIGH_WAV = "resources/sounds/drums/toms/TomYamaha-oak-high.wav";
    public static String TOM_YAMAHA_MID_WAV = "resources/sounds/drums/toms/TomYamaha-oak-mid.wav";
    public static String TOM_YAMAHA_LOW_WAV = "resources/sounds/drums/toms/TomYamaha-oak-low.wav";
    public static String ZYMBAL_1_WAV = "resources/sounds/drums/zymbals/ZymbalPlash1.wav";
    public static String ZYMBAL_2_WAV = "resources/sounds/drums/zymbals/ZymbalPlash2.wav";
    public static String ZYMBAL_SMALL_WAV = "resources/sounds/drums/zymbals/SmallZym16_909.wav";
    public static String DIN_1_AIF = "resources/sounds/drums/zymbals/Din1.aif";
    
    public VDrumKit()
    {
        this.type = VInstrumentBuilder.DRUM_KIT_INSTRUMENT;
    }
    
    @Override
    public VDrumElement[] getElementOptions()
    {
        return new VDrumElement[]{ new VDrumElement("Bass drum aif", BASS_DRUM_AIF),
                                   new VDrumElement("Bass drum wav", BASS_DRUM_WAV),
                                   new VDrumElement("Snare", SNARE_WAV),
                                   new VDrumElement("Tom Yamaha high", TOM_YAMAHA_HIGH_WAV),
                                   new VDrumElement("Tom Yamaha mid", TOM_YAMAHA_MID_WAV),
                                   new VDrumElement("Tom Yamaha low", TOM_YAMAHA_LOW_WAV),
                                   new VDrumElement("Zymbal left", ZYMBAL_1_WAV),
                                   new VDrumElement("Zymbal right", ZYMBAL_2_WAV),
                                   new VDrumElement("Zymbal small", ZYMBAL_SMALL_WAV),
                                   new VDrumElement("Zymbal Din", DIN_1_AIF)
                                 };
    }
}
