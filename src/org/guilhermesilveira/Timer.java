package org.guilhermesilveira;

/**
 * The default implementation of a Timer. There is no need for you to access it
 * directly.
 * 
 * As with any timer in Java, it does not guarantee the precise invocation
 * timing. It places each invocation at least deltaMillis apart from each other.
 * Because of the thread scheduler, they might be more than deltaMillis apart.
 * 
 * @author Guilherme Silveira
 */
public class Timer {

	public static final int FOREVER = -1;

	final int deltaMillis;

	final int invocationsToMake;

	final Callback callback, exitCallback;

	int invocationsSoFar = 0;

	long lastCall = System.currentTimeMillis();

	/**
	 * Creates the timer with no exit callback.
	 */
	Timer(int deltaMillis, int k, Callback callback) {
		this(deltaMillis, k, callback, Callback.DO_NOTHING);
	}

	/**
	 * Creates the timer with no exit callback with an exit callback.
	 */
	Timer(int deltaMillis, int k, Callback callback, Callback exitCallback) {
		this.deltaMillis = deltaMillis;
		this.invocationsToMake = k;
		this.callback = callback;
		this.exitCallback = exitCallback;
	}

	/**
	 * Checks for a tick and - if proper - invokes it. If the time elapsed since the
	 * last check would allow for two ticks, only one tick will be run, as expected
	 * with most timeout functions in different language implementations.
	 */
	void check() {
		long delta = System.currentTimeMillis() - lastCall;
		if (delta > deltaMillis) {
			invocationsSoFar++;
			callback.run(invocationsSoFar);
			lastCall = System.currentTimeMillis();
		}
	}

	/**
	 * Invokes the exit callback. Does not keep state.
	 */
	void dispose() {
		this.exitCallback.run(this.invocationsSoFar);
	}

	/**
	 * Returns if the amount of ticks configured have been produced or not. If they
	 * have, then returns true.
	 */
	boolean isDead() {
		return invocationsToMake != FOREVER && invocationsSoFar >= invocationsToMake;
	}

	@Override
	public String toString() {
		return "Timer [deltaMillis=" + deltaMillis + ", invocationsToMake=" + invocationsToMake + ", callback="
				+ callback + ", exitCallback=" + exitCallback + ", invocationsSoFar=" + invocationsSoFar + ", lastCall="
				+ lastCall + "]";
	}

}
