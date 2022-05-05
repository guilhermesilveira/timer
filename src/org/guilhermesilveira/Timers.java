package org.guilhermesilveira;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import processing.core.PApplet;

/**
 * Timers contains a list of callbacks to be invoked in specified time
 * intervals. Each running timer inside a timers instance contains a minimum
 * delay in milliseconds, the amount of times it is supposed to fire and the
 * callback function to be invoked every time the timer ticks.
 * 
 * Optionally, a callback can be provided that will be invoked right after the
 * last tick, which is a somehow common situation.
 * 
 * Timers runs within the drawing function which means that you can draw during
 * the callbacks, although the library author does not recommend doing so. It is
 * more appropriate to make changes to variables during the callback and reflect
 * those changes in your drawing loop.
 *
 * @author Guilherme Silveira
 */
public class Timers {

	public final static String VERSION = "##library.prettyVersion##";

	/**
	 * Initializes the timers
	 * 
	 * @param sketch the parent PApplet
	 */
	public Timers(PApplet sketch) {
		sketch.registerMethod("pre", this);
	}

	/**
	 * Even though sketches will probably have only a few timers, but LinkedList
	 * will be faster to remove dead items than an ArrayList.
	 */
	private final List<Timer> timers = new LinkedList<Timer>();

	/**
	 * Pre invocation that checks all timers. You do not need to invoke this method
	 * as Processing will do it for us.
	 */
	public void pre() {
		ListIterator<Timer> iter = timers.listIterator();
		while (iter.hasNext()) {
			Timer item = iter.next();
			item.check();
			if (item.isDead()) {
				item.dispose();
				iter.remove();
			}
		}
	}

	/**
	 * return the version of the Library.
	 * 
	 * @return String the current version
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 * Adds a new timer to tick at least every deltaMillis for k times, invoking the
	 * specified callback function each tick.
	 * 
	 * @param deltaMillis the minimum interval in milliseconds
	 * @param k           the amount of times to invoke the callback
	 * @param callback    the function will no parameters to be invoked
	 */
	public void add(int deltaMillis, int k, Runnable callback) {
		timers.add(new Timer(deltaMillis, k, (z) -> callback.run()));
	}

	/**
	 * Adds a new timer to tick at least every deltaMillis for k times, invoking the
	 * specified callback function each tick.
	 * 
	 * @param deltaMillis  the minimum interval in milliseconds
	 * @param k            the amount of times to invoke the callback
	 * @param callback     the function will no parameters to be invoked
	 * @param exitCallback the exit callback that will be invoked right after the
	 *                     last invocation to the callback
	 */
	public void add(int deltaMillis, int k, Runnable callback, Callback exitCallback) {
		timers.add(new Timer(deltaMillis, k, (z) -> callback.run(), exitCallback));
	}

	/**
	 * Adds a new timer to tick at least every deltaMillis for k times, invoking the
	 * specified callback function each tick.
	 * 
	 * @param deltaMillis the minimum interval in milliseconds
	 * @param k           the amount of times to invoke the callback
	 * @param callback    the function that receives one parameter with the current
	 *                    tick for this timer
	 */
	public void add(int deltaMillis, int k, Callback callback) {
		timers.add(new Timer(deltaMillis, k, callback, Callback.DO_NOTHING));
	}

	/**
	 * Adds a new timer to tick at least every deltaMillis for k times, invoking the
	 * specified callback function each tick.
	 * 
	 * @param deltaMillis  the minimum interval in milliseconds
	 * @param k            the amount of times to invoke the callback
	 * @param callback     the function that receives one parameter with the current
	 *                     tick for this timer
	 * @param exitCallback the exit callback that will be invoked right after the
	 *                     last invocation to the callback
	 */
	public void add(int deltaMillis, int k, Callback callback, Callback exitCallback) {
		timers.add(new Timer(deltaMillis, k, callback, exitCallback));
	}

}
