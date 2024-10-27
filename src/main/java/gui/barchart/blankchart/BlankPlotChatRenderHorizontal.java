package gui.barchart.blankchart;

import java.awt.Graphics2D;

public abstract class BlankPlotChatRenderHorizontal {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChartHorizontal chart, Graphics2D g2, SeriesSize size, int index);
}
