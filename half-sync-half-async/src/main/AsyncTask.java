package main;

import java.util.concurrent.Callable;

public interface AsyncTask<V> extends Callable<V> {

	// 调用call方法之前调用，耗时任务不该再此处执行因其会阻塞
	void onPreCall();
	
	// call方法执行结束之后的回调方法。
	void onPostCall(V result);
	
	// 任务异常
	void onError(Throwable throwable);
	
	@Override
	V call() throws Exception;
}
