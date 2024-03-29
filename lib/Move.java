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
 * 2012-07-11 - Created
 * 2012-07-12 - Change the forward() and backward() time values to milliseconds
 *
 */

import josx.platform.rcx.Motor;
import josx.platform.rcx.Sound;

/**
 * Move object (class) for Turner
 * 
 * Encapsulates all the functionality needed to move Turner, in a single
 * static class. Like the default leJOS API, this class exposes only static
 * methods, and should be used in the same manner as the default classes.
 * 
 * @author David Love
 * @version 0.1.2
 */

public class Move {
	///
	/// Class Types
	///

	///
	/// Class Variables
	///

	///
	/// Private methods
	///

	/** Move Forward */
	private static void motorForward() {
		Motor.C.backward();
	}

	/** Stop all movement */
	private static void motorStop() {
		Motor.C.stop();
	}

	/** Move Backward */
	private static void motorBackward() {
		Motor.C.forward();
	}

	///
	/// Public methods
	//

	/** Move forward for 'seconds' */
	public static void forward(int milliseconds) {
		motorForward();

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			/* Indicate something went wrong */
			Sound.systemSound(false, 4);
		}

		motorStop();
	}

	/** Turn hard left for 'seconds' */
	public static void backward(int milliseconds) {
		motorBackward();

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			/* Indicate something went wrong */
			Sound.systemSound(false, 4);
		}

		motorStop();
	}
	
	/** Stop all motor activity */
	public static void stop() {
		motorStop();
	}
	
	/** Command a full, immediate, stop and abort any thread waits */
	public static void fullStop() throws InterruptedException {
		motorStop();
		throw new InterruptedException();
	}

}

