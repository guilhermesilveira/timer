import org.guilhermesilveira.Timers;

Timers timers = new Timers(this);

void every5Seconds() {
  println("every 5 seconds");
}

void setup() {
  // runs every second, 10 times a lambda
  timers.add(1000, 10, () -> println("every 1 second"));
  
  // runs 10 times every 10 seconds, a lambda with a counter
  timers.add(10 * 1000, 10, (k) -> println("#" + k + " invocation on 10 seconds"));

  // runs 10 times every 5 seconds a method by its reference
  timers.add(5 * 1000, 10, this::every5Seconds);

    // runs 2 times every 3 seconds a method with the counter
  timers.add(3 * 1000, 2, () -> println("every 3 seconds"),
                          (k) -> println("Finished the 3 seconds counter after " + k + " calls"));
}

void draw() {
}
