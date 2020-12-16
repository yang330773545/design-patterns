package main;

// 偷资源的货
public class Thief extends Thread {
  
	private final String name;

    /**
     * The jar.
     */
    private final Jar jar;

    public Thief(String name, Jar jar) {
        this.name = name;
        this.jar = jar;
    }

    /**
     * In the run method the thief repeatedly tries to take a bean until none are left.
     */
    @Override
    public void run() {
    	int beans = 0;

        while (jar.takeBean()) {
            beans = beans + 1;
            System.out.println(name+" took a bean.");
        }

        System.out.println(name+" took "+ beans + " bean.");
    }
}
