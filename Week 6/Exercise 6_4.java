/*
A simple worker subclass of Thread.
In its run(), sends 1000 inc() messages
to its Pair object.
(1000 may not be big enough to exhibit the bug on uniprocessor --
hardware more like 1000000 may be required).
*/
public class PairWorker extends Thread {
public final int COUNT = 1000;
private Pair pair;
// Ctor takes a pointer to the pair we use
public PairWorker(Pair pair) {
this.pair = pair;
}
// Send many inc() messages to our pair
public void run() {
for (int i=0; i<COUNT; i++) {
pair.inc();
}
}

/*
Test main -- Create a Pair and 3 workers.
Start the 3 workers -- they do their run() --
and wait for the workers to finish.
*/
public static void main(String args[]) {
Pair pair = new Pair();
PairWorker w1 = new PairWorker(pair);
PairWorker w2 = new PairWorker(pair);
PairWorker w3 = new PairWorker(pair);
w1.start();
w2.start();
w3.start();
// the 3 workers are running
// all sending messages to the same object
// we block until the workers complete
try {
w1.join();
w2.join();
w3.join();
}
catch (InterruptedException ignored) {}


System.out.println("Final sum:" + pair.sum()); // should be 6000
/*
If sum()/inc() were not synchronized, the result would
be 6000 in some cases, and other times random values
like 5979 due to the writer/writer conflicts of multiple
threads trying to execute inc() on an object at the same time.
*/
}
}