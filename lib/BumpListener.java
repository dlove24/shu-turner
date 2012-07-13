/*
 * Move.java
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

import josx.platform.rcx.Sensor;
import josx.platform.rcx.SensorListener;

/**
 * Simple bump sensor listener, commanding a motor full stop.
 * 
 * Creates a simple event handler for the SensorListener interface, which
 * commands a full (movement) motor stop when fired. Also takes care of sensor
 * noise and de-bounce routines.
 * 
 * @author David Love
 * @version 0.0.1
 */

public class BumpListener implements SensorListener {

	public void stateChanged(Sensor source, int oldValue, int newValue) {
		/* Stop the motor */
		Move.stop();

		/* Wait 0.025 seconds for the sensor to settle */
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			/* Not critical if this interrupt fails */
		}
	}

}
