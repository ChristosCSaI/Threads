
class VecSumThread2 extends Thread
{
	private int [] table;
	private int myId;
	private int myStart;
	private int myStop;
	   

	// constructor
	public VecSumThread2(int id, int numThreads, int size, int[] array)
	{
		table = array;
		myId = id;
		myStart = myId * (size / numThreads);
		myStop = myStart + (size / numThreads);
		if (myId == (numThreads - 1)) myStop = size;
	}

	// thread code
	public void run()
	{
		int pSum = 0;
		int nSum = 0;
		for(int i = myStart; i < myStop; i++) {
			if(table[i] >= 0){
				pSum = pSum + table[i];
			}
			else{			
				nSum = nSum + table[i];
			}
		}
			ThreadParArray3.lock.lock();
			try {
				ThreadParArray3.pSum = ThreadParArray3.pSum + table[i];
				ThreadParArray3.nSum = ThreadParArray3.nSum + table[i];
			} finally {
				ThreadParArray3.lock.unlock();
			}	
	}
}
