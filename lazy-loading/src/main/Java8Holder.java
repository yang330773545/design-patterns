package main;

import java.util.function.Supplier;

/**
 * 
 * @author 50000008
 *
 * 线程安全 且性能好于 {@link HolderThreadSafe}.
 * Java 8 functional interface {@link Supplier} as {@link Heavy} factory.
 */
public class Java8Holder {

	private Supplier<Heavy> heavy = this::createAndCacheHeavy;
	
	public Java8Holder() {
		System.out.println("Java8Holder created");
	}
		
    public Heavy getHeavy() {
        return heavy.get();
    }

    private synchronized Heavy createAndCacheHeavy() {
    	class HeavyFactory implements Supplier<Heavy> {
    		private final Heavy heavyInstance = new Heavy();
		    @Override
		    public Heavy get() {
		    	return heavyInstance;
		    }
    	}

		if (!(heavy instanceof HeavyFactory)) {
			heavy = new HeavyFactory();
		}

		return heavy.get();
    }

}
