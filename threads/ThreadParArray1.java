
import java.util.Random;

class ThreadParArray1
{
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
	
		Random r = new Random(); 
		int[] a = new int[size];
		for(int i = 0; i < size; i++)
			a[i] = r.nextInt();

	
		int[] pSum = new int[numThreads];
		int[] nSum = new int[numThreads];

		for(int i = 0; i < numThreads; i++){
			pSum[i] = 0;
			nSum[i] = 0;
		}
	 
		long start = System.currentTimeMillis();

		// create threads
		VecSumThread1 threads[] = new VecSumThread1[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			
			threads[i] = new VecSumThread1(i, numThreads, pSum, nSum, size, a);
			threads[i].start();
		}

		// wait for threads to terminate			
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
		   		} catch (InterruptedException e) {}
		}

	//Υπολογίζω τα δύο αθροίσματα μετά τον τερματισμό των νημάτων
		int positive = 0;
		int negative = 0;
		for (int i = 0; i < numThreads; i++) {
			positive = positive + pSum[i];
			negative = negative + nSum[i];
		}
		
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		System.out.println("time in ms = "+ elapsedTimeMillis);

		System.out.println("Positive Sum: " + positive);
		System.out.println("Negative Sum: " + negative);

	}
}