package com.sxxy.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		MyThead myThead = new MyThead();
		
		FutureTask<Integer> futureTask = new FutureTask<>(myThead);
		
        new Thread(futureTask).start();
        
        System.out.println(futureTask.get());
	}

}

class MyThead implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("call()");
		return 1024;
	}
	
}
