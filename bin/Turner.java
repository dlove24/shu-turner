import josx.platform.rcx.Sensor;
import josx.platform.rcx.SensorConstants;

/*
 * Turner.java
 *
 * Developed by David Love <d.love@shu.ac.uk>
 * Copyright (c) 2012 Sheffield Hallam University
 * 
 * Permission to use, copy, modify, and/or distribute this 
 * software for any purpose with or without fee is hereby granted, 
 * provided that the above copyright notice and this permission notice 
 * appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * Changelog:
 * 2012-07-11 - created
 *
 */

public class Turner
{
  public static void main (String[] args) throws Exception
  {
	/* Set-up the forward and reverse bump sensors */
	BumpListener frontBumper = new BumpListener();
	BumpListener backBumper = new BumpListener();
	
	Sensor.S1.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_BOOL);
	Sensor.S1.addSensorListener(backBumper);
	Sensor.S1.activate();
	
	Sensor.S3.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH, SensorConstants.SENSOR_MODE_BOOL);
	Sensor.S3.addSensorListener(frontBumper);
	Sensor.S3.activate();
	  
	/* Enter the main movement loop */
	  
    See.sense();
  }
}
