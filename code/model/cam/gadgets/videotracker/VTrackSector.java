package code.model.cam.gadgets.videotracker;

/**
 *
 * @author Jose Carlos
 */
public class VTrackSector 
{
    private int xRangeIni;
    private int xRangeEnd;
    private int yRangeIni;
    private int yRangeEnd;

    public VTrackSector(int xRangeIni, int xRangeEnd, int yRangeIni, int yRangeEnd) 
    {
        this.xRangeIni = xRangeIni;
        this.xRangeEnd = xRangeEnd;
        this.yRangeIni = yRangeIni;
        this.yRangeEnd = yRangeEnd;
    }

    public int getxRangeEnd() 
    {
        return xRangeEnd;
    }

    public int getxRangeIni() 
    {
        return xRangeIni;
    }

    public int getyRangeEnd() 
    {
        return yRangeEnd;
    }

    public int getyRangeIni() 
    {
        return yRangeIni;
    }
    
    public boolean isInside(int x, int y)
    {
        return this.xRangeIni <= x && x < this.xRangeEnd &&
               this.yRangeIni <= y && y < this.yRangeEnd;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if (obj instanceof VTrackSector)
        {
            VTrackSector sector = (VTrackSector)obj;
            isEqual = this.xRangeIni == sector.xRangeIni &&
                      this.xRangeEnd == sector.xRangeEnd &&
                      this.yRangeIni == sector.yRangeIni &&
                      this.yRangeEnd == sector.yRangeEnd;
        }
        return isEqual;
    }
    
    @Override
    public int hashCode()
    {
        return 0;
    }
}
