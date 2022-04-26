import org.guilhermesilveira.Timers;

void setup() {
  // runs every second, 10 times, invokes a lambda
  new Timers(this).add(1000, 10, () -> println("every 1 second"));
}

void draw() {
}
