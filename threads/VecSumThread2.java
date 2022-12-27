
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

	
	public void run()
	{
		for(int i = myStart; i < myStop; i++) {
			if(table[i] >= 0){
				ThreadParArray2.lock.lock();
				try {
					ThreadParArray2.pSum = ThreadParArray2.pSum + table[i];
				} finally {
					ThreadParArray2.lock.unlock();
				}					
			}
			else{			
				ThreadParArray2.lock.lock();
				try {
					ThreadParArray2.nSum = ThreadParArray2.nSum + table[i];
				} finally {
					ThreadParArray2.lock.unlock();
				}
			}
		}
	}
}
