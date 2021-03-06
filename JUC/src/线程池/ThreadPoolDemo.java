package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadPoolDemo {
	/**
	 * submit(Runnable task)方法提交一个线程
	 * 1）newFixedThreadPool和newSingleThreadExecutor:
	 * 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
	 * 2）newCachedThreadPool和newScheduledThreadPool:
	 * 主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
	 */
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newScheduledThreadPool(5);
		// 信号量
        Semaphore semaphore = new Semaphore(1);
		for(int i=0; i < 10; i++){
			int index = i;
			executorService.submit(() -> {
			    System.out.println("i:" + index + " executorService");
                try {
                    Thread.sleep(2000);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
			semaphore.release();
			
		}
		executorService.shutdown();
	}

}
