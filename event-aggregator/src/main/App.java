package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 
 * @author Yang
 * 
 * 事件汇总器
 * KingsHand 汇总其他对象的其他事件 最后报告给KingJoffrey  KingJoffrey只需要监听KingsHand
 * 
 */
public class App {

	public static void main(String[] args) {
		KingJoffrey kingJoffrey = new KingJoffrey();
		KingsHand kingsHand = new KingsHand(kingJoffrey);

		List<EventEmitter> emitters = new ArrayList<>();
		emitters.add(kingsHand);
		emitters.add(new LordBaelish(kingsHand));
		emitters.add(new LordVarys(kingsHand));
		emitters.add(new Scout(kingsHand));
		

	    Arrays.stream(Weekday.values())
	        .<Consumer<? super EventEmitter>>map(day -> emitter -> emitter.timePasses(day))
	        .forEachOrdered(emitters::forEach);
	  }
}
