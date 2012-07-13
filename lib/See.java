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
 * 2012-09-11 - created
 *
 */

import josx.platform.rcx.Sensor;
import josx.platform.rcx.Sound;
import josx.platform.rcx.TextLCD;

/**
 * Attempts to detect the direction of a lighter area.
 * 
 * Assuming a light sensor on Port 2 of the RCX unit, this class attempts to
 * build a very simple 'eye' that searches for a lighter area in front of the
 * robot. This direction is recorded, and can then be used to direct the future
 * movement of the robot.
 * 
 * @author David Love
 * @version 0.0.1
 */
public class See {

	// /
	// / Class Variables
	// /

	/* Array holding the sensor values */
	protected static int[] lightReading = new int[5];

	// /
	// / Public methods
	// /

	/**
	 * Move the front wheels of the robot, taking reading from the light-field
	 * in front of the robot as we do so. These readings are then compared to
	 * previous values, to determine the 'lightest' areas.
	 */
	public static void sense() {

		/* Discard the first value */
		Sensor.S2.setPreviousValue(0);
		Sensor.S2.readRawValue();

		/* Take the readings */
		for (int i = 0; i < 5; i++) {
			Steer.absPosition(i);

			Sensor.S2.setPreviousValue(0);
			lightReading[i] = 1023 - Sensor.S2.readRawValue();

			/* Feedback */
			TextLCD.print(Integer.toString(lightReading[i]));
			Sound.playTone(lightReading[i], 10);

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				/* Indicate something went wrong */
				Sound.systemSound(false, 4);
			}
		}

		Steer.reset();
	}

	/**
	 * Return the absolute steering position needed to move to lightest point in
	 * the the last sensed light-field.
	 */
	public static int moveTo() {
		int position = Steer.STEERING_NEUTRAL;
		int maxValue = 0;

		for (int i = 0; i < 5; i++) {
			if (lightReading[i] > maxValue) {
				position = i;
				maxValue = lightReading[i];

			}
		}

		TextLCD.print(Integer.toString(position));
		return position;
	}
	
	/** Return an integer representing the brighest spot found in the
	 *  last light-field
	 */
	public static int brightSpot() {
		int maxValue = 0;

		for (int i = 0; i < 5; i++) {
			if (lightReading[i] > maxValue) {
				maxValue = lightReading[i];

			}
		}

		return maxValue;
	}

}
