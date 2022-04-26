package org.guilhermesilveira;

/**
 * The functional interface for a callback with one integer parameter.
 * 
 * @author Guilherme Silveira
 */
public interface Callback {
	void run(int k);

	Callback DO_NOTHING = (z) -> {
	};
}
