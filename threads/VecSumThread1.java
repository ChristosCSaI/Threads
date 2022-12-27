
class VecSumThread1 extends Thread
{
	private int [] pSums;
	private int [] nSums;
	private int [] table;
	private int myId;
	private int myStart;
	private int myStop;
	   

	// constructor
	public VecSumThread1(int id, int numThreads, int[] pos, int[] neg, int size, int[] array)
	{
		pSums = pos;
		nSums = neg;
		table = array;
		myId = id;
		myStart = myId * (size / numThreads);
		myStop = myStart + (size / numThreads);
		if (myId == (numThreads - 1)) myStop = size;
	}

	// thread code
	public void run()
	{
		for(int i = myStart; i < myStop; i++) {
			if(table[i] >= 0){
			   	pSums[myId] = pSums[myId] + table[i];
			}
			else{
				nSums[myId] = nSums[myId] + table[i];
			}
		}
	}
}
