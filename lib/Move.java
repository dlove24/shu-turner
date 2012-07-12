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

import josx.platform.rcx.*;

/**
 * Move object (class) for Turner
 * 
 * Encapsulates all the functionality needed to move Turner, in a single
 * static class. Like the default leJOS API, this class exposes only static
 * methods, and should be used in the same manner as the default classes.
 * 
 * @author David Love
 * @version 0.0.1
 */

public class Move {
	/**
	 ** Class Types
	 */

	/**
	 ** Class Variables
	 */

	/**
	 ** Private methods
	 */

	/* Move Forward */
	private static void motorForward() {
		Motor.C.backward();
	}

	private static void motorStop() {
		Motor.C.stop();
	}

	/* Move Backward */
	private static void motorBackward() {
		Motor.C.forward();
	}

	/**
	 ** Public methods
	 */

	/* Move forward for 'seconds' */
	public static void forward(int seconds) {
		motorForward();

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			/* Not sure where the motor is now !! */
		}

		motorStop();
	}

	/* Turn hard left for 'seconds' */
	public static void backward(int seconds) {
		motorBackward();

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			/* Not sure where the motor is now !! */
		}

		motorStop();
	}

}

/*
 * Modeline for ViM {{{ vim: set ts=4: vim600: fdm=marker fdl=0 fdc=3: }}}
 */

