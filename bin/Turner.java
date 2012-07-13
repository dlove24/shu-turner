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

import java.util.Random;

import josx.platform.rcx.Motor;
import josx.platform.rcx.Sensor;
import josx.platform.rcx.SensorConstants;
import josx.platform.rcx.TextLCD;

public class Turner {
	public static void main(String[] args) throws Exception {
		/* Last brightest spot in the light-field */
		int lastHigh = 0;

		/* Current steering position */
		int position = Steer.STEERING_NEUTRAL;

		/* Initalise the random number generator */
		Random generator = new Random();

		/* Set-up the forward and reverse bump sensors */
		BumpListener frontBumper = new BumpListener();
		BumpListener backBumper = new BumpListener();

		Sensor.S1.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH,
				SensorConstants.SENSOR_MODE_BOOL);
		Sensor.S1.addSensorListener(backBumper);
		Sensor.S1.activate();

		Sensor.S3.setTypeAndMode(SensorConstants.SENSOR_TYPE_TOUCH,
				SensorConstants.SENSOR_MODE_BOOL);
		Sensor.S3.addSensorListener(frontBumper);
		Sensor.S3.activate();

		/* Activate the 'eye' */
		Sensor.S2.setTypeAndMode(SensorConstants.SENSOR_TYPE_LIGHT,
				SensorConstants.SENSOR_MODE_RAW);
		Sensor.S2.activate();

		/* Activate the tail light */
		Motor.B.forward();

		/* Enter the main movement loop */
		for (int i = 0; i < 30; i++) {
			See.sense();

			/* Is this bright spot brighter than the last? */
			if (See.brightSpot() > (lastHigh - 100)) {
				/* Yes, so move towards it */
				lastHigh = See.brightSpot();

				position = See.moveTo();
				TextLCD.print(Integer.toString(position));
				Steer.absPosition(position);

				int time = generator.nextInt(1000);
				Move.forward(time);
			} else {
				/*
				 * No, move backwards to see if we can find a new bright spot
				 */
				position = See.moveTo();
				TextLCD.print(Integer.toString(position));
				Steer.absPosition(position);

				int time = generator.nextInt(1000);
				Move.backward(time);
			}

		}

		/* Reset at the end */
		Steer.reset();

		Motor.B.stop();

		Sensor.S1.passivate();
		Sensor.S2.passivate();
		Sensor.S3.passivate();
	}
}
