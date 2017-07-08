package kd.mmi.curvechart.figs;

import java.text.DecimalFormat;
import java.text.Format;

import kd.mmi.curvechart.enums.FormatEnum;


/**
 * Êý×Ö×ø±êÖá.
 * 
 * @author LRY
 * 
 */
public class NumberAxis extends Axis {

	private static final long serialVersionUID = 1L;

	public NumberAxis() {
		this(AxisPosition.LEFT);
	}
	
	public NumberAxis(AxisPosition position) {
		super(position);
	}

	@Override
	protected void setupAxisInfo() {
		if(axisInfo != null) {
			super.setupAxisInfo();
			int decimal = axisInfo.getDecimal();
			Format format = FormatEnum.NUMBER_FORMAT.getFormat();
			if(format.getClass().getName().equals(DecimalFormat.class.getName())) {
				((DecimalFormat)format).setMinimumFractionDigits(decimal);
				((DecimalFormat)format).setMaximumFractionDigits(decimal);
			}
			setFormat(format);
		}
	}
	
}
