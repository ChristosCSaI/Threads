
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadParArray3
{
	//Αρχικοποίηση των Sum με 0
	public static int pSum = 0;
	public static int nSum = 0;
	public static Lock lock = new ReentrantLock();

	public static void main(String[] args)
	{
		int size = 0;
		int numThreads = 0;
			
		if (args.length != 2) {
				System.out.println("Usage: java ThreadParSqrt <vector size> <number of threads>");
				System.exit(1);
		}

		try {
			size = Integer.parseInt(args[0]);
				numThreads = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
				System.exit(1);
		}
		if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();

	α
		Random r = new Random(); 
		int[] a = new int[size];
		for(int i = 0; i < size; i++)
			a[i] = r.nextInt();
	 
	
		long start = System.currentTimeMillis();

		
		VecSumThread3 threads[] = new VecSumThread3[numThreads];
		 
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new VecSumThread2(i, numThreads, size, a);
			threads[i].start();
		}

		// wait for threads to terminate			
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
		   		} catch (InterruptedException e) {}
		}

		// get current time and calculate elapsed time
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		System.out.println("time in ms = "+ elapsedTimeMillis);

		System.out.println("Positive Sum: " + pSum);
		System.out.println("Negative Sum: " + nSum);
	}
}