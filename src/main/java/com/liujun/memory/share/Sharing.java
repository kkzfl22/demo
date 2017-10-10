package com.liujun.memory.share;

public final class Sharing implements Runnable {

	public final static int NUM_THREADS = 4; // change

	public final static long ITERATIONS = 10000L * 1000L * 1000L;

	private final int arrayIndex;

	private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

	static {
		for (int i = 0; i < longs.length; i++) {
			longs[i] = new VolatileLong();
		}
	}

	public Sharing(final int arrayIndex) {
		this.arrayIndex = arrayIndex;

	}

	public static void main(final String[] args) throws Exception {
		final long start = System.nanoTime();

		runTest();
		System.out.println("nosharing duration = " + (System.nanoTime() - start));

		final long startshard = System.nanoTime();
		Sharing.runTest();
		System.out.println("  sharing duration = " + (System.nanoTime() - startshard));

	}

	public static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Sharing(i));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}

	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {
		public volatile long value = 0L;
	}

}
