/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2011, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------
 * XYPlot.java
 * -----------
 * (C) Copyright 2000-2011, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Craig MacFarlane;
 *                   Mark Watson (www.markwatson.com);
 *                   Jonathan Nash;
 *                   Gideon Krause;
 *                   Klaus Rheinwald;
 *                   Xavier Poinsard;
 *                   Richard Atkinson;
 *                   Arnaud Lelievre;
 *                   Nicolas Brodu;
 *                   Eduardo Ramalho;
 *                   Sergei Ivanov;
 *                   Richard West, Advanced Micro Devices, Inc.;
 *                   Ulrich Voigt - patches 1997549 and 2686040;
 *                   Peter Kolb - patches 1934255, 2603321 and 2809117;
 *                   Andrew Mickish - patch 1868749;
 *
 * Changes (from 21-Jun-2001)
 * --------------------------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 18-Sep-2001 : Updated header and fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Data source classes moved to com.jrefinery.data.* (DG);
 * 19-Oct-2001 : Removed the code for drawing the visual representation of each
 *               data point into a separate class StandardXYItemRenderer.
 *               This will make it easier to add variations to the way the
 *               charts are drawn.  Based on code contributed by Mark
 *               Watson (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 20-Nov-2001 : Fixed clipping bug that shows up when chart is displayed
 *               inside JScrollPane (DG);
 * 12-Dec-2001 : Removed unnecessary 'throws' clauses from constructor (DG);
 * 13-Dec-2001 : Added skeleton code for tooltips.  Added new constructor. (DG);
 * 16-Jan-2002 : Renamed the tooltips class (DG);
 * 22-Jan-2002 : Added DrawInfo class, incorporating tooltips and crosshairs.
 *               Crosshairs based on code by Jonathan Nash (DG);
 * 05-Feb-2002 : Added alpha-transparency setting based on code by Sylvain
 *               Vieujot (DG);
 * 26-Feb-2002 : Updated getMinimumXXX() and getMaximumXXX() methods to handle
 *               special case when chart is null (DG);
 * 28-Feb-2002 : Renamed Datasets.java --> DatasetUtilities.java (DG);
 * 28-Mar-2002 : The plot now registers with the renderer as a property change
 *               listener.  Also added a new constructor (DG);
 * 09-Apr-2002 : Removed the transRangeZero from the renderer.drawItem()
 *               method.  Moved the tooltip generator into the renderer (DG);
 * 23-Apr-2002 : Fixed bug in methods for drawing horizontal and vertical
 *               lines (DG);
 * 13-May-2002 : Small change to the draw() method so that it works for
 *               OverlaidXYPlot also (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 20-Aug-2002 : Renamed getItemRenderer() --> getRenderer(), and
 *               setXYItemRenderer() --> setRenderer() (DG);
 * 28-Aug-2002 : Added mechanism for (optional) plot annotations (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Nov-2002 : Added grid settings for both domain and range axis (previously
 *               these were set in the axes) (DG);
 * 09-Jan-2003 : Further additions to the grid settings, plus integrated plot
 *               border bug fix contributed by Gideon Krause (DG);
 * 22-Jan-2003 : Removed monolithic constructor (DG);
 * 04-Mar-2003 : Added 'no data' message, see bug report 691634.  Added
 *               secondary range markers using code contributed by Klaus
 *               Rheinwald (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 03-Apr-2003 : Added setDomainAxisLocation() method (DG);
 * 30-Apr-2003 : Moved annotation drawing into a separate method (DG);
 * 01-May-2003 : Added multi-pass mechanism for renderers (DG);
 * 02-May-2003 : Changed axis locations from int to AxisLocation (DG);
 * 15-May-2003 : Added an orientation attribute (DG);
 * 02-Jun-2003 : Removed range axis compatibility test (DG);
 * 05-Jun-2003 : Added domain and range grid bands (sponsored by Focus Computer
 *               Services Ltd) (DG);
 * 26-Jun-2003 : Fixed bug (757303) in getDataRange() method (DG);
 * 02-Jul-2003 : Added patch from bug report 698646 (secondary axes for
 *               overlaid plots) (DG);
 * 23-Jul-2003 : Added support for multiple secondary datasets, axes and
 *               renderers (DG);
 * 27-Jul-2003 : Added support for stacked XY area charts (RA);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 01-Sep-2003 : Fixed bug where change to secondary datasets didn't generate
 *               change event (797466) (DG)
 * 08-Sep-2003 : Added internationalization via use of properties
 *               resourceBundle (RFE 690236) (AL);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 08-Sep-2003 : Fixes for serialization (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Fixed zooming to include secondary domain axes (DG);
 * 18-Sep-2003 : Added getSecondaryDomainAxisCount() and
 *               getSecondaryRangeAxisCount() methods suggested by Eduardo
 *               Ramalho (RFE 808548) (DG);
 * 23-Sep-2003 : Split domain and range markers into foreground and
 *               background (DG);
 * 06-Oct-2003 : Fixed bug in clearDomainMarkers() and clearRangeMarkers()
 *               methods.  Fixed bug (815876) in addSecondaryRangeMarker()
 *               method.  Added new addSecondaryDomainMarker methods (see bug
 *               id 815869) (DG);
 * 10-Nov-2003 : Added getSecondaryDomain/RangeAxisMappedToDataset() methods
 *               requested by Eduardo Ramalho (DG);
 * 24-Nov-2003 : Removed unnecessary notification when updating axis anchor
 *               values (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 12-Mar-2004 : Fixed bug where primary renderer is always used to determine
 *               range type (DG);
 * 22-Mar-2004 : Fixed cloning bug (DG);
 * 23-Mar-2004 : Fixed more cloning bugs (DG);
 * 07-Apr-2004 : Fixed problem with axis range when the secondary renderer is
 *               stacked, see this post in the forum:
 *               http://www.jfree.org/phpBB2/viewtopic.php?t=8204 (DG);
 * 07-Apr-2004 : Added get/setDatasetRenderingOrder() methods (DG);
 * 26-Apr-2004 : Added option to fill quadrant areas in the background of the
 *               plot (DG);
 * 27-Apr-2004 : Removed major distinction between primary and secondary
 *               datasets, renderers and axes (DG);
 * 30-Apr-2004 : Modified to make use of the new getRangeExtent() method in the
 *               renderer interface (DG);
 * 13-May-2004 : Added optional fixedLegendItems attribute (DG);
 * 19-May-2004 : Added indexOf() method (DG);
 * 03-Jun-2004 : Fixed zooming bug (DG);
 * 18-Aug-2004 : Added removedAnnotation() method (by tkram01) (DG);
 * 05-Oct-2004 : Modified storage type for dataset-to-axis maps (DG);
 * 06-Oct-2004 : Modified getDataRange() method to use renderer to determine
 *               the x-value range (now matches behaviour for y-values).  Added
 *               getDomainAxisIndex() method (DG);
 * 12-Nov-2004 : Implemented new Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 22-Feb-2005 : Changed axis offsets from Spacer --> RectangleInsets (DG);
 * 24-Feb-2005 : Added indexOf(XYItemRenderer) method (DG);
 * 21-Mar-2005 : Register plot as change listener in setRenderer() method (DG);
 * 21-Apr-2005 : Added get/setSeriesRenderingOrder() methods (ET);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 04-May-2005 : Fixed serialization of domain and range markers (DG);
 * 05-May-2005 : Removed unused draw() method (DG);
 * 20-May-2005 : Added setDomainAxes() and setRangeAxes() methods, as per
 *               RFE 1183100 (DG);
 * 01-Jun-2005 : Upon deserialization, register plot as a listener with its
 *               axes, dataset(s) and renderer(s) - see patch 1209475 (DG);
 * 01-Jun-2005 : Added clearDomainMarkers(int) method to match
 *               clearRangeMarkers(int) (DG);
 * 06-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 09-Jun-2005 : Added setRenderers(), as per RFE 1183100 (DG);
 * 06-Jul-2005 : Fixed crosshair bug (id = 1233336) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Added getAnnotations() method (DG);
 * 05-Sep-2006 : Added MarkerChangeEvent support (DG);
 * 13-Oct-2006 : Fixed initialisation of CrosshairState - see bug report
 *               1565168 (DG);
 * 22-Nov-2006 : Fixed equals() and cloning() for quadrant attributes, plus
 *               API doc updates (DG);
 * 29-Nov-2006 : Added argument checks (DG);
 * 15-Jan-2007 : Fixed bug in drawRangeMarkers() (DG);
 * 07-Feb-2007 : Fixed bug 1654215, renderer with no dataset (DG);
 * 26-Feb-2007 : Added missing setDomainAxisLocation() and
 *               setRangeAxisLocation() methods (DG);
 * 02-Mar-2007 : Fix for crosshair positioning with horizontal orientation
 *               (see patch 1671648 by Sergei Ivanov) (DG);
 * 13-Mar-2007 : Added null argument checks for crosshair attributes (DG);
 * 23-Mar-2007 : Added domain zero base line facility (DG);
 * 04-May-2007 : Render only visible data items if possible (DG);
 * 24-May-2007 : Fixed bug in render method for an empty series (DG);
 * 07-Jun-2007 : Modified drawBackground() to pass orientation to
 *               fillBackground() for handling GradientPaint (DG);
 * 24-Sep-2007 : Added new zoom methods (DG);
 * 26-Sep-2007 : Include index value in IllegalArgumentExceptions (DG);
 * 05-Nov-2007 : Applied patch 1823697, by Richard West, for removal of domain
 *               and range markers (DG);
 * 12-Nov-2007 : Fixed bug in equals() method for domain and range tick
 *               band paint attributes (DG);
 * 27-Nov-2007 : Added new setFixedDomain/RangeAxisSpace() methods (DG);
 * 04-Jan-2008 : Fix for quadrant painting error - see patch 1849564 (DG);
 * 25-Mar-2008 : Added new methods with optional notification - see patch
 *               1913751 (DG);
 * 07-Apr-2008 : Fixed NPE in removeDomainMarker() and
 *               removeRangeMarker() (DG);
 * 22-May-2008 : Modified calculateAxisSpace() to process range axes first,
 *               then adjust the plot area before calculating the space
 *               for the domain axes (DG);
 * 09-Jul-2008 : Added renderer state notification when series pass begins
 *               and ends - see patch 1997549 by Ulrich Voigt (DG);
 * 25-Jul-2008 : Fixed NullPointerException for plots with no axes (DG);
 * 15-Aug-2008 : Added getRendererCount() method (DG);
 * 25-Sep-2008 : Added minor tick support, see patch 1934255 by Peter Kolb (DG);
 * 25-Nov-2008 : Allow datasets to be mapped to multiple axes - based on patch
 *               1868749 by Andrew Mickish (DG);
 * 18-Dec-2008 : Use ResourceBundleWrapper - see patch 1607918 by
 *               Jess Thrysoee (DG);
 * 10-Mar-2009 : Allow some annotations to contribute to axis autoRange (DG);
 * 18-Mar-2009 : Modified anchored zoom behaviour and fixed bug in
 *               "process visible range" rendering (DG);
 * 19-Mar-2009 : Added panning support based on patch 2686040 by Ulrich
 *               Voigt (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 * 30-Mar-2009 : Delegate panning to axes (DG);
 * 10-May-2009 : Added check for fixedLegendItems in equals(), and code to
 *               handle cloning (DG);
 * 24-Jun-2009 : Added support for annotation events - see patch 2809117
 *               by PK (DG);
 * 06-Jul-2009 : Fix for cloning of renderers - see bug 2817504 (DG)
 * 10-Jul-2009 : Added optional drop shadow generator (DG);
 * 18-Oct-2011 : Fix tooltip offset with shadow renderer (DG);
 *
 */

package org.jfree.chart.custom.plot;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.custom.axis.CustomDateAxis;
import org.jfree.chart.custom.renderer.CustomRendererUtilities;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.Pannable;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.Zoomable;
import org.jfree.chart.renderer.RendererUtilities;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.custom.CustomTimeSeries;
import org.jfree.util.PublicCloneable;

/**
 * A general class for plotting data in the form of (x, y) pairs.  This plot can
 * use data from any class that implements the {@link XYDataset} interface.
 * <P>
 * <code>XYPlot</code> makes use of an {@link XYItemRenderer} to draw each point
 * on the plot.  By using different renderers, various chart types can be
 * produced.
 * <p>
 * The {@link org.jfree.chart.ChartFactory} class contains static methods for
 * creating pre-configured charts.
 */
public class CustomXYPlot extends XYPlot implements ValueAxisPlot, Pannable, Zoomable,
        RendererChangeListener, Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 7044148245716569264L;

   
    /**
     * Creates a new <code>XYPlot</code> instance with no dataset, no axes and
     * no renderer.  You should specify these items before using the plot.
     */
    public CustomXYPlot() {
        this(null, null, null, null);
    }

    /**
     * Creates a new plot with the specified dataset, axes and renderer.  Any
     * of the arguments can be <code>null</code>, but in that case you should
     * take care to specify the value before using the plot (otherwise a
     * <code>NullPointerException</code> may be thrown).
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param domainAxis  the domain axis (<code>null</code> permitted).
     * @param rangeAxis  the range axis (<code>null</code> permitted).
     * @param renderer  the renderer (<code>null</code> permitted).
     */
    public CustomXYPlot(XYDataset dataset,
                  ValueAxis domainAxis,
                  ValueAxis rangeAxis,
                  XYItemRenderer renderer) {

        super(dataset, domainAxis, rangeAxis, renderer);
    }

    /**
     * Draws a representation of the data within the dataArea region, using the
     * current renderer.
     * <P>
     * The <code>info</code> and <code>crosshairState</code> arguments may be
     * <code>null</code>.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param index  the dataset index.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  collects crosshair information
     *                        (<code>null</code> permitted).
     *
     * @return A flag that indicates whether any data was actually rendered.
     */
    public boolean render(Graphics2D g2, Rectangle2D dataArea, int index,
            PlotRenderingInfo info, CrosshairState crosshairState) {

        boolean foundData = false;
        XYDataset dataset = getDataset(index);
        if (!DatasetUtilities.isEmptyOrNull(dataset)) {
            foundData = true;
            ValueAxis xAxis = getDomainAxisForDataset(index);
            ValueAxis yAxis = getRangeAxisForDataset(index);
            if (xAxis == null || yAxis == null) {
                return foundData;  // can't render anything without axes
            }
            XYItemRenderer renderer = getRenderer(index);
            if (renderer == null) {
                renderer = getRenderer();
                if (renderer == null) { // no default renderer available
                    return foundData;
                }
            }

            XYItemRendererState state = renderer.initialise(g2, dataArea, this,
                    dataset, info);
            int passCount = renderer.getPassCount();

            SeriesRenderingOrder seriesOrder = getSeriesRenderingOrder();
            if (seriesOrder == SeriesRenderingOrder.REVERSE) {
                //render series in reverse order
                for (int pass = 0; pass < passCount; pass++) {
                    int seriesCount = dataset.getSeriesCount();
                    for (int series = seriesCount - 1; series >= 0; series--) {
                        int firstItem = 0;
                        int lastItem = dataset.getItemCount(series) - 1;
                        if (lastItem == -1) {
                            continue;
                        }
                        if (state.getProcessVisibleItemsOnly()) {
                        	int[] itemBounds = new int[2];
                        	if (xAxis instanceof CustomDateAxis) {
                        		DateRange range = (DateRange) xAxis.getRange();
                            	double lowBound = ((CustomDateAxis)xAxis).getInitDateRange().getLowerBound();
                            	double length = ((CustomDateAxis)xAxis).getInitDateRange().getLength();
                            	double startTime = ((CustomTimeSeries)((TimeSeriesCollection)dataset).getSeries(series)).getInitStartXValue();
                            	double seriesBegin = range.getLowerBound();
                            	double seriesEnd = range.getUpperBound();
                            	long lowDiff = (long) (lowBound - startTime);
                        		if (lowDiff > 0) {
                        			long seriesLength = (long) length;
                        			seriesBegin = range.getLowerMillis() - lowDiff;
                        			seriesEnd = seriesBegin + seriesLength;
//                        			System.out.println("begin:" + new Date(seriesBegin).toLocaleString() + "    end:" + new Date(seriesEnd).toLocaleString());
                        		}
//                        		if (lowBound!= startTime) {
//                        			double lowDiff = range.getLowerMillis() - lowBound;
//                        			double seriesLength = (long) range.getLength();
//                        			seriesBegin = startTime +lowDiff;
//                        			seriesEnd = seriesBegin + seriesLength;
//                        		}
                        		itemBounds = CustomRendererUtilities.findLiveItems(
                                        dataset, series, seriesBegin,
                                        seriesEnd);
                        	} else {
                        		itemBounds = RendererUtilities.findLiveItems(
                                        dataset, series, xAxis.getLowerBound(),
                                        xAxis.getUpperBound());
                        	}
                    	
                            firstItem = Math.max(itemBounds[0] - 1, 0);
                            lastItem = Math.min(itemBounds[1] + 1, lastItem);
                        }
                        state.startSeriesPass(dataset, series, firstItem,
                                lastItem, pass, passCount);
                        for (int item = firstItem; item <= lastItem; item++) {
                            renderer.drawItem(g2, state, dataArea, info,
                                    this, xAxis, yAxis, dataset, series, item,
                                    crosshairState, pass);
                        }
                        state.endSeriesPass(dataset, series, firstItem,
                                lastItem, pass, passCount);
                    }
                }
            }
            else {
                //render series in forward order
                for (int pass = 0; pass < passCount; pass++) {
                    int seriesCount = dataset.getSeriesCount();
                    for (int series = 0; series < seriesCount; series++) {
                        int firstItem = 0;
                        int lastItem = dataset.getItemCount(series) - 1;
                        if (state.getProcessVisibleItemsOnly()) {
                            int[] itemBounds = CustomRendererUtilities.findLiveItems(
                                    dataset, series, xAxis.getLowerBound(),
                                    xAxis.getUpperBound());
                            firstItem = Math.max(itemBounds[0] - 1, 0);
                            lastItem = Math.min(itemBounds[1] + 1, lastItem);
                        }
                        state.startSeriesPass(dataset, series, firstItem,
                                lastItem, pass, passCount);
                        for (int item = firstItem; item <= lastItem; item++) {
                            renderer.drawItem(g2, state, dataArea, info,
                                    this, xAxis, yAxis, dataset, series, item,
                                    crosshairState, pass);
                        }
                        state.endSeriesPass(dataset, series, firstItem,
                                lastItem, pass, passCount);
                    }
                }
            }
        }
        return foundData;
    }

    
    /**
     * Returns the legend items for the plot.  Each legend item is generated by
     * the plot's renderer, since the renderer is responsible for the visual
     * representation of the data.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
        if (getFixedLegendItems() != null) {
            return getFixedLegendItems();
        }
        LegendItemCollection result = new LegendItemCollection();
        int count = super.getDatasetCount();
        for (int datasetIndex = 0; datasetIndex < count; datasetIndex++) {
            XYDataset dataset = getDataset(datasetIndex);
            if (dataset != null) {
                XYItemRenderer renderer = getRenderer();
                if (renderer == null) {
                    renderer = getRenderer(0);
                }
                if (renderer != null) {
                    int seriesCount = dataset.getSeriesCount();
                    for (int i = 0; i < seriesCount; i++) {
                        if (renderer.isSeriesVisible(i)
                                || renderer.isSeriesVisibleInLegend(i)) {
                            LegendItem item = renderer.getLegendItem(
                                    datasetIndex, i);
                            if (item != null) {
                                result.add(item);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


}
