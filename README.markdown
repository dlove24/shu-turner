# Turner Readme #

The code is split into a `bin` directory, holding the core robot-specific code, and a `lib` directory which should be a bit more generic. Note, though, that 'generic' in this case still means the code makes some assumptions about the layout of the RCX connectors, and the mechanical arrangements of the robot. As configured for Turner, the ports are

 * Output

	A: Steering motor
  B: Taillight
	C: Gearbox motor

* Input

	1: Rear bumper
	2: Light sensor
 	3: Front bumper

# Setting up leJOS #

Follow the commands for installing the RCX version of [leJOS](http://lejos.sourceforge.net/rcx.php). You will also need to manually build and install the `josx` packages in the leJOS source directory before the library will work.

# Loading the Firmware #

We assume the system already has a copy of [leJOS](http://lejos.sourceforge.net/rcx.php) installed. In that case, the `Ant` script in the root folder of this code source should take care of the rest. All you need to do is edit the `Ant` script and make sure the directories point to where you installed leJOS.

**NOTE:** This code has *only* been tested on the Lego RCX controller (the yellow one). If you have a NX controller, this code almost certainly will not work. Make sure the leJOS environment is set-up and configured for the RCX controller as well.

Hook up the Lego Tower controller to the serial port of the computer, and make sure the RCX brick is on, and facing the tower. When everything is ready, you can install the leJOS environment using the command

```
ant load-firmware

```

This will download a copy of the leJOS tinyJVM, and the relevant control code to the RCX unit. From this point onwards the standard Lego RCX firmware will be disabled: only the leJOS code will run on the RCX unit. You can, however, restore the default Lego firmware by removing the batteries from the RCX unit for 30 seconds, and when power is restored the default firmware should be back.

Once the leJOS firmware is loaded, the code can be built using the command

```
ant build

```

and the program loaded onto the RCX unit using the command

```
ant upload

```

If all goes well, you should hear two short beeps. If not, check the error codes.

You don't actually have to compile the code before uploaded: `Ant` will detect if the code needs rebuilding and make sure it is up-to-date before it is transferred to the RCX unit.
