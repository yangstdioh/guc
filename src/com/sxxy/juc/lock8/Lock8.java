package com.sxxy.juc.lock8;

import java.util.concurrent.TimeUnit;

public class Lock8 {

	public static void main(String[] args) throws InterruptedException {
		
		Phone phone = new Phone();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				phone.senSms();
			}
		}, "A").start();
		
		// TimeUnit.SECONDS.sleep(1);
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				phone.call();
			}
		}, "B").start();

	}

}

class Phone {
	
	// 锁的调用的对象
	public synchronized void senSms() {
		
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("发短信");
	}
	
	public synchronized void call() {
		System.out.println("打电话");
	}
}
