package code.model.cam.gadgets.videotracker;


/*
 * Author: Konrad Rzeszutek <konrad AT darnok DOT org>
 *
 *
 *
 *
 *    This file is part of Motion Detection toolkit.
 *
 *    Motion Detection toolkit is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    Motion Detection toolkit is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with Foobar; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */


import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import javax.media.Buffer;
import javax.media.Control;
import javax.media.Effect;
import javax.media.Format;
import javax.media.format.RGBFormat;
/**
 * Motion detection effect.
 *
 * The engine has two steps.
 * First the input image and the
 * reference image (which is the image from the previous frame) is compared.
 * Whenever a pixel has changed consideribly (determined by the
 * <b>threshold</b> variable) a internal black-white-red image is
 * marked (at the same exact location where the change occured). Therefore in
 * the first step, the internal black-white-red image is has lit up clusters
 * in the space where a change has occured.
 * <p>The next step is to eliminate these clusters that are too small, but still
 * appeared in our black-white-red image. Only the big clusters are left (and
 * are colored red). During this process we keep a track of couunt of the big
 * clusters.
 * If the count is greater than <b>blob_threshold</b> then the input frame is
 * determined to have consideribly motion as to the previous frame.
 *
 * <br><br>Many of the ideas have been taken from
 * <ol>
 *  <li><a href="http://gspy.sourceforge.net/">A Gnome Security Camera</a> by
 *  Lawrance P. Glaister.
 *  <li>Digital Image Processing by Kenneth R. Castleman; ISBN 0-13-211467-4
 *  <li>Computer Graphics Principles and Practice by Foley, van Dam, Feiner,
 *  Hughes; ISBN 0-201-84840-6
 *  <li>Java Media Format Sample Programs (mainly the one dealing with
 *     building an Effect plugin)
 * </ol>
 *
 * <br><br>
 * @version: $Id: MotionDetectionEffect.java,v 1.4 2002/04/28 19:14:11 uid500 Exp $
 * @author: Konrad Rzeszutek <konrad@darnok.org>
 *
 */
public class VMotionDetectionEffect implements Effect 
{
    /**
    * Optimization. Anything above 0 turns it on. By default its
    * disabled.
    */
    public int OPTIMIZATION = 0;
    /**
     * Maximum threshold setting. Setting the threshold above this
     * means to get the motion detection to pass the frame you pretty
     * much have to full the whole frame with lots of motions (ie: drop
     * the camera)
     */
    public int THRESHOLD_MAX = 10000;

    /**
     * By what value you should increment.
     */
    public int THRESHOLD_INC = 1000;

    /**
     * The initial threshold setting.
     */
    public int THRESHOLD_INIT = 5000;

    /* UI motion tracker controls */
    private Control[] controls;
    private Format[] inputFormats;
    private Format[] outputFormats;
    private Format inputFormat;
    private Format outputFormat;
    
    private VDetectionEffectHelper detectionHelper;
    private HashMap<Point, VITrackerListener> actionsToLoad;

    /**
     * Our threshold for determinig if the input image has enough motion.
     *
     */
    public int blob_threshold = THRESHOLD_INIT;

    /**
     * Turn debugging on. Slows down the effect but shows how motion
     * detection effect works.
     */
    public boolean debug = false;

    /**
     * Initialize the effect plugin.
     */
    public VMotionDetectionEffect() 
    {
        this.inputFormats = new Format[] 
        {
            new RGBFormat(null,
                          Format.NOT_SPECIFIED,
                          Format.byteArray,
                          Format.NOT_SPECIFIED,
                          24,
                          3, 2, 1,
                          3, Format.NOT_SPECIFIED,
                          Format.TRUE,
                          Format.NOT_SPECIFIED)
        };

        this.outputFormats = new Format[] 
        {
            new RGBFormat(null,
                          Format.NOT_SPECIFIED,
                          Format.byteArray,
                          Format.NOT_SPECIFIED,
                          24,
                          3, 2, 1,
                          3, Format.NOT_SPECIFIED,
                          Format.TRUE,
                          Format.NOT_SPECIFIED)
        };
    }

    /**
     * Get the format that we support
     *
     * @return  instanceof RGBFormat
     */
    @Override
    public Format[] getSupportedInputFormats() 
    {
	return inputFormats;
    }

    /**
     * Get the format that we support
     *
     * @return  instanceof RGBFormat
     */
    @Override
    public Format [] getSupportedOutputFormats(Format input) 
    {
        if (input == null) 
        {
            return outputFormats;
        }

        if (matches(input, inputFormats) != null) 
        {
            return new Format[] { outputFormats[0].intersects(input) };
        }
        else 
        {
            return new Format[0];
        }
    }

    /**
     * Set our input format.
     */
    @Override
    public Format setInputFormat(Format input) 
    {
        inputFormat = input;
	return input;
    }

    /**
     * Set our output format.
     *
     */
    @Override
    public Format setOutputFormat(Format output) 
    {
        if (output == null || matches(output, outputFormats) == null)
        {
            return null;
        }

        RGBFormat incoming = (RGBFormat) output;

        Dimension size = incoming.getSize();
        int maxDataLength = incoming.getMaxDataLength();
        int lineStride = incoming.getLineStride();
        float frameRate = incoming.getFrameRate();

        if (size == null)
        {
            return null;
        }
        if (maxDataLength < size.width * size.height * 3)
        {
            maxDataLength = size.width * size.height * 3;
        }
        if (lineStride < size.width * 3)
        {
            lineStride = size.width * 3;
        }
        this.outputFormat = outputFormats[0].intersects(new RGBFormat(
                                                        size,
                                                        maxDataLength,
                                                        null,
                                                        frameRate,
                                                        Format.NOT_SPECIFIED,
                                                        Format.NOT_SPECIFIED,
                                                        Format.NOT_SPECIFIED,
                                                        Format.NOT_SPECIFIED,
                                                        Format.NOT_SPECIFIED,
                                                        lineStride,
                                                        Format.NOT_SPECIFIED,
                                                        Format.NOT_SPECIFIED));
        return outputFormat;
    }

    /*  optimization ideas:
        first scale down the image.
        convert the image to an int[][] array (instead of using byte[][])

        then do all the calculation on int[][] array instead of
        masking the bit.

        furthermore, only do the comparison every 5 frames instead of every frame.
    */
    @Override
    public int process(Buffer inBuffer, Buffer outBuffer)
    {
        if (this.detectionHelper == null) 
        {
            this.detectionHelper = new VDetectionEffectHelper(inBuffer, outBuffer, 640, 480);
            this.detectionHelper.loadActions(this.actionsToLoad);
            return BUFFER_PROCESSED_OK;
        }
        else
        {
            return detectionHelper.display(this.debug);
        }
    }
    
    // methods for interface javax.media.Controls
    @Override
    public Object getControl(String controlType) 
    {
        System.out.println(controlType);
	return null;
    }

    @Override
    public Object[] getControls() 
    {
      if (controls == null) 
      {
        controls = new Control[1];
        controls[0] = new MotionDetectionControl(this);
      }
      return (Object[])controls;
    }

    // methods for interface PlugIn
    @Override
    public String getName() 
    {
        return "Motion Detection Codec";
    }

    @Override
    public void open() {}

    @Override
    public void close() {}

    @Override
    public void reset() {}
    
    public boolean addActionSection(int x, int y, VITrackerListener action)
    {
        boolean loaded;
        if (this.detectionHelper != null)
        {
            loaded = this.detectionHelper.addActionListener(x, y, action);
        }
        else
        {
            if (this.actionsToLoad == null)
            {
                this.actionsToLoad = new HashMap<Point, VITrackerListener>();
            }
            this.actionsToLoad.put(new Point(x, y), action);
            loaded = true;
        }
        return loaded;
    }
    
    private Format matches(Format in, Format outs[]) 
    {
        for (int i = 0; i < outs.length; i++)
        {
            if (in.matches(outs[i]))
            {
                return outs[i];
            }
        }
        return null;
    }
}
