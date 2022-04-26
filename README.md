# Timer Library

The timer library is a Processing 4 timer library with support to Java lambda. Less code. Easier to learn and use.

Do you want a function to be invoked every second, 10 times? Ok:

```
void setup() {
	new Timers(this).add(1000, 10, this::hello);
}

void hello(int k) {
	println("Hello " + k);
}
```

Do you want to do it inline? Ok:

```
new Timers(this).add(1000, 10, (k) -> println("Hello " + k));
```

Do you want to generate never ending random colors? Ok:

```
color shapeColor;

void setup() {
	new Timers(this).add(1000, -1, () -> shapeColor = color((int) random(255), (int) random(255), (int) random(255)));
}
```

Don't forget to import Timers. You can use the same Timers instance for multiple timers.

Please, check the examples in the examples folder to see how to use multiple timers and variations of the add method.

Have fun.

## Installing / More information

You can get more information and how to install it at 


## Contributing

The source code can be found at https://github.com/guilhermesilveira/timer/blob/master/distribution/Timer-1/index.html and https://github.com/guilhermesilveira/timer/blob/master/distribution/Timer-1/README.md
