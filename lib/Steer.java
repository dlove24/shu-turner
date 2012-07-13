/*
 * Steer.java
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

import josx.platform.rcx.Motor;

/**
 * Steer object (class) for Turner
 * 
 * Encapsulates all the functionality needed to move the steering motor for
 * Turner, in a single static class. Like the default leJOS API, this class
 * exposes only static methods, and should be used in the same manner as the
 * default classes.
 * 
 * @author David Love
 * @version 0.1.3
 */

public class Steer {
	///
	/// Class Types
	///

	/*
	 * Enumeration of the SteeringState.
	 * 
	 * NOTE: We don't have enum in the embedded Java
	 */
	public static final byte STEERING_HARD_RIGHT = 0;
	public static final byte STEERING_RIGHT = 1;
	public static final byte STEERING_NEUTRAL = 2;
	public static final byte STEERING_LEFT = 3;
	public static final byte STEERING_HARD_LEFT = 4;
	public static final byte STEERING_INVALID = -1;

	/*
	 * Time (in milliseconds) of the motor movements commanding left and right.
	 * These should be adjusted so that a left followed by a right (or vice
	 * versa) leave the motor in the neutral position
	 */
	private static final byte MOTOR_LEFT_COMMAND_TIME = 50;
	private static final byte MOTOR_RIGHT_COMMAND_TIME = 50;

	///
	/// Class Variables
	///

	protected static byte steeringState = STEERING_NEUTRAL;

	///
	/// Private methods
	///

	/** Turn the motor left */
	private static void motorLeft() {
		Motor.A.forward();

		try {
			Thread.sleep(MOTOR_LEFT_COMMAND_TIME);
		} catch (InterruptedException e) {
			/* Not sure where the motor is now !! */
			steeringState = STEERING_INVALID;
		}

		Motor.A.stop();
	}

	/** Turn the motor right */
	private static void motorRight() {
		Motor.A.backward();
		try {
			Thread.sleep(MOTOR_RIGHT_COMMAND_TIME);
		} catch (InterruptedException e) {
			/* Not sure where the motor is now !! */
			steeringState = STEERING_INVALID;
		}
		Motor.A.stop();
	}

	/**
	 * Put the motor in the correct position to steer to hard left, based on the
	 * current, assumed position of the motor
	 */
	private static void steerHardLeft() {

		/*
		 * This is basically a switch statement that keeps acting until the
		 * motor is in the commanded position
		 */
		if (steeringState == STEERING_HARD_RIGHT) {
			motorLeft();
			steeringState = STEERING_RIGHT;
		}

		if (steeringState == STEERING_RIGHT) {
			motorLeft();
			steeringState = STEERING_NEUTRAL;
		}

		if (steeringState == STEERING_NEUTRAL) {
			motorLeft();
			steeringState = STEERING_LEFT;
		}

		if (steeringState == STEERING_LEFT) {
			motorLeft();
			steeringState = STEERING_HARD_LEFT;
		}

		/*
		 * Ignore the command if we are already in the STEERING_HARD_LEFT state
		 */
		if (steeringState == STEERING_HARD_LEFT) {
		}
	}

	/**
	 * Put the motor in the correct position to steer to the left, based on the
	 * current assumed position of the motor
	 */
	private static void steerLeft() {

		/*
		 * This is basically a switch statement that keeps acting until the
		 * motor is in the commanded position
		 */
		if (steeringState == STEERING_HARD_RIGHT) {
			motorLeft();
			steeringState = STEERING_RIGHT;
		}

		if (steeringState == STEERING_RIGHT) {
			motorLeft();
			steeringState = STEERING_NEUTRAL;
		}

		if (steeringState == STEERING_NEUTRAL) {
			motorLeft();
			steeringState = STEERING_LEFT;
		}

		/*
		 * Ignore the command if we are already in the STEERING_LEFT state
		 */
		if (steeringState == STEERING_LEFT) {
		}

		/* Correct the steering if in the STEERING_HARD_LEFT state */
		if (steeringState == STEERING_HARD_LEFT) {
			motorRight();
		}
	}

	/**
	 * Put the motor in the neutral steering position based on the current
	 * assumed position of the motor
	 */
	private static void steerReset() {

		/*
		 * This is basically a switch statement that keeps acting until the
		 * motor is in the commanded position
		 */
		if (steeringState == STEERING_HARD_RIGHT) {
			motorLeft();
			steeringState = STEERING_RIGHT;
		}

		if (steeringState == STEERING_RIGHT) {
			motorLeft();
			steeringState = STEERING_NEUTRAL;
		}

		if (steeringState == STEERING_HARD_LEFT) {
			motorRight();
			steeringState = STEERING_LEFT;
		}

		if (steeringState == STEERING_LEFT) {
			motorRight();
			steeringState = STEERING_NEUTRAL;
		}

		/*
		 * Ignore the command if we are in the STEERING_NEUTRAL state
		 */
		if (steeringState == STEERING_NEUTRAL) {
		}
	}

	/**
	 * Put the motor in the correct position to steer to the right, based on the
	 * current assumed position of the motor
	 */
	private static void steerRight() {

		/*
		 * This is basically a switch statement that keeps acting until the
		 * motor is in the commanded position
		 */
		if (steeringState == STEERING_HARD_LEFT) {
			motorRight();
			steeringState = STEERING_LEFT;
		}

		if (steeringState == STEERING_LEFT) {
			motorRight();
			steeringState = STEERING_NEUTRAL;
		}

		if (steeringState == STEERING_NEUTRAL) {
			motorRight();
			steeringState = STEERING_RIGHT;
		}

		/*
		 * Ignore the command if we are already in the STEERING_RIGHT state
		 */
		if (steeringState == STEERING_RIGHT) {
		}

		/* Correct the steering if in the STEERING_HARD_RIGHT state */
		if (steeringState == STEERING_HARD_RIGHT) {
			motorLeft();
		}
	}

	/**
	 * Put the motor in the correct position to steer to hard right, based on
	 * the current assumed position of the motor
	 */
	private static void steerHardRight() {

		/*
		 * This is basically a switch statement that keeps acting until the
		 * motor is in the commanded position
		 */
		if (steeringState == STEERING_HARD_LEFT) {
			motorRight();
			steeringState = STEERING_LEFT;
		}

		if (steeringState == STEERING_LEFT) {
			motorRight();
			steeringState = STEERING_NEUTRAL;
		}

		if (steeringState == STEERING_NEUTRAL) {
			motorRight();
			steeringState = STEERING_RIGHT;
		}

		/*
		 * STEERING_RIGHT state
		 */
		if (steeringState == STEERING_RIGHT) {
			motorRight();
			steeringState = STEERING_HARD_RIGHT;
		}

		/*
		 * Ignore the command if we are already in the STEERING_HARD_RIGHT state
		 */
		if (steeringState == STEERING_HARD_RIGHT) {
		}
	}

	///
	/// Public methods
	///

	/** Turn hard left */
	public static void hardLeft() {
		steerHardLeft();
	}

	/** Turn left */
	public static void left() {
		steerLeft();
	}

	/** Put the steering motor in the neutral position */
	public static void reset() {
		steerReset();
	}

	/** Turn right */
	public static void right() {
		steerRight();
	}

	/** Turn hard right */
	public static void hardRight() {
		steerHardRight();
	}

	/** Move the wheels to the stated position */
	public static void absPosition(int position) {

		if (position == STEERING_HARD_RIGHT) {
			steerHardRight();
		} else if (position == STEERING_RIGHT) {
			steerRight();
		} else if (position == STEERING_NEUTRAL) {
			steerReset();
		} else if (position == STEERING_LEFT) {
			steerLeft();
		} else if (position == STEERING_HARD_LEFT) {
			steerHardLeft();
		}
	}

}
