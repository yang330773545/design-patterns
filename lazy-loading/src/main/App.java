package main;

public class App {

	public static void main(String[] args) {
		HolderNaive holderNaive = new HolderNaive();
		Heavy heavy = holderNaive.getHeavy();
	    
		// Thread safe lazy loader, but with heavy synchronization on each access
		HolderThreadSafe holderThreadSafe = new HolderThreadSafe();
		Heavy another = holderThreadSafe.getHeavy();

	    // The most efficient lazy loader utilizing Java 8 features
	    Java8Holder java8Holder = new Java8Holder();
	    Heavy next = java8Holder.getHeavy();

	}
}
