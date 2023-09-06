package Server;

public 	class SharedData {
	// It is the shared data according to which the threads are working with
	public static volatile int forGui = 1;
	public static volatile int forMouseClicks;
	public int getForGui(){
		return forGui;
	}

	public void setForGui(int val){
		forGui = val;
	}

	public int getForMouseClicks(){
		forMouseClicks = forGui;
		return forMouseClicks;
	}
}