package code.model.cam.gadgets.videotracker;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.media.Buffer;
import javax.media.PlugIn;
import javax.media.format.RGBFormat;

/**
 *
 * @author Jose Carlos
 */
/**
 * Utility class to process motion detection
 */
public class VDetectionEffectHelper 
{
    /**
     * The threshold for determing if the pixel at a certain location has
     * changed consideribly.
     */
    private int threshold = 40;
    private int pixStrideIn;
    private int lineStrideIn;
    private int sectorsOnX;
    private int sectorsOnY;
    private Dimension sizeIn;
    private byte[] inData;
    private byte[] outData;
    private byte[] refData;
    private byte[] detectionScreen;
    private HashMap<VTrackSector, VITrackerListener> trackActions;
    
    public VDetectionEffectHelper(Buffer inBuffer, Buffer outBuffer) 
    {
        this.sectorsOnX = 640;
        this.sectorsOnY = 480;
        prepareHelper(inBuffer, outBuffer);
    }
    
    public VDetectionEffectHelper(Buffer inBuffer, Buffer outBuffer, int sectorsOnX, int sectorsOnY) 
    {
        this.sectorsOnX = sectorsOnX;
        this.sectorsOnY = sectorsOnY;
        prepareHelper(inBuffer, outBuffer);
    }
    
    public void loadActions(HashMap<Point, VITrackerListener> actionsToLoad) 
    {
        for (Entry<Point, VITrackerListener> entry : actionsToLoad.entrySet())
        {
            addActionListener(entry.getKey().x, entry.getKey().y, entry.getValue());
        }
    }
    
    public boolean addActionListener(int xSector, int ySector, VITrackerListener listener)
    {
        boolean isValidSector = isValidSectorCoord(xSector, ySector);
        if (isValidSector)
        {
            VTrackSector sector = generateSector(xSector, ySector);
            trackActions.put(sector, listener);
        }
        return isValidSector;
    }

    public int display(boolean isTracking)
    {
        if (isTracking) 
        {
            compareFrames();
            System.arraycopy(this.detectionScreen, 0, this.outData, 0, this.inData.length);
        } 
        else 
        {
            System.arraycopy(this.inData, 0, this.outData, 0, this.inData.length);
        }
        System.arraycopy(inData, 0, refData, 0, inData.length);
        return PlugIn.BUFFER_PROCESSED_OK;
    }
    
    private void prepareHelper(Buffer inBuffer, Buffer outBuffer)
    {
        initDimensions(inBuffer);
        initCollections(inBuffer, outBuffer);
    }

    private void initDimensions(Buffer inBuffer) 
    {
        RGBFormat vfIn = (RGBFormat) inBuffer.getFormat();
        this.pixStrideIn = vfIn.getPixelStride();
        this.lineStrideIn = vfIn.getLineStride();
        this.sizeIn = vfIn.getSize();
        System.out.println("Height: " + this.sizeIn.height + "\nWidth: " + this.sizeIn.width);
    }

    private void initCollections(Buffer inBuffer, Buffer outBuffer) 
    {
        int bufferLength = this.sizeIn.height * this.sizeIn.width * 3;
        this.inData = (byte[]) inBuffer.getData();
        this.outData = new byte[bufferLength];
        this.refData = new byte[bufferLength];
        this.detectionScreen = new byte[bufferLength];
        this.trackActions = new HashMap<VTrackSector, VITrackerListener>();
        outBuffer.setData(this.outData);
        System.arraycopy(this.inData, 0, this.refData, 0, this.inData.length);
    }
    
    private VTrackSector generateSector(int xSector, int ySector) 
    {
        int sectorWidth = this.sizeIn.width / this.sectorsOnX,
            sectorHeight = this.sizeIn.height / this.sectorsOnY,
            xRangeIni = xSector * sectorWidth,
            xRangeEnd = xRangeIni + sectorWidth,
            yRangeIni = ySector * sectorHeight,
            yRangeEnd = yRangeIni + sectorHeight;
        return new VTrackSector(xRangeIni, xRangeEnd, yRangeIni, yRangeEnd);
    }

    /**
     * This is comparing the last frame with the new frame.
     * We lit up only the pixels which changed, the rest is discarded (on our
     * b/w image - used for determing the motion
     */
    private void compareFrames() 
    {
        int r, g, b,
            refDataInt, inDataInt,
            ip = 0, op = 0,
            x = 0, y = 0;
        byte result;
        while (ip < refData.length) 
        {
            refDataInt = (int) refData[ip] & 255;
            inDataInt = (int) this.inData[ip++] & 255;
            r = refDataInt - inDataInt;
            
            refDataInt = (int) refData[ip] & 255;
            inDataInt = (int) this.inData[ip++] & 255;
            g = refDataInt - inDataInt;
            
            refDataInt = (int) refData[ip] & 255;
            inDataInt = (int) this.inData[ip++] & 255;
            b = refDataInt - inDataInt;
            
            result = (byte) (Math.sqrt((double) ((r * r) + (g * g) + (b * b)) / 3.0));
            //black/white image now.
            if (result > (byte) this.threshold) 
            {
                if (validateActionSection(x, y))
                {
                    detectionScreen[op++] = (byte) 255;
                    detectionScreen[op++] = (byte) 0;
                    detectionScreen[op++] = (byte) 0;
                }
                else
                {
                    
                    detectionScreen[op++] = (byte) 0;
                    detectionScreen[op++] = (byte) 255;
                    detectionScreen[op++] = (byte) 0;
                }
            } 
            else 
            {
                detectionScreen[op++] = (byte) result;
                detectionScreen[op++] = (byte) result;
                detectionScreen[op++] = (byte) result;
            }
            
            y = x == this.sizeIn.width ? y + 1 : y;
            x = x == this.sizeIn.width ? 0 : x + 1;
        }
    }

    private boolean isValidSectorCoord(int xSector, int ySector) 
    {
        return 0 <= xSector && xSector < this.sectorsOnX &&
               0 <= ySector && ySector < this.sectorsOnY;
    }

    private boolean validateActionSection(int x, int y) 
    {
        Set<VTrackSector> sectors = this.trackActions.keySet();
        VTrackSector matched = null;
        for (VTrackSector sector: sectors)
        {
            if (sector.isInside(x, y))
            {
                matched = sector;
                break;
            }
        }
        if (matched != null)
        {
            ((VITrackerListener)this.trackActions.get(matched)).runAction();
        }
        return matched != null;
    }
}
