package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;

public class AsynchronousService {
	
	// 提供方法来管理终端和方法，可以产生Future为跟踪一个或多个异步任务执行
	private final ExecutorService service;
	
	public AsynchronousService(BlockingQueue<Runnable> workQueue) {
		service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
	}
	
	// 一种非阻塞方法，它执行后台提供的任务并立即返回。
	public <T> void execute(final AsyncTask<T> task) {
		try {
			task.onPreCall();
		} catch(Exception e) {
			task.onError(e);
			return;
		}
		
		service.submit(new FutureTask<T>(task) {
			@Override
			protected void done() {
				super.done();
				try {
					task.onPostCall(get());
				} catch (InterruptedException e) {
			          // should not occur
		        } catch (ExecutionException e) {
		          task.onError(e.getCause());
		        }
			}
		});
	}
	
	public void close() {
		service.shutdown();
		try {
			// 阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch(InterruptedException ie) {
			System.out.println("Error waiting for executor service shutdown!");
		}
	}
}
