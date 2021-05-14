package com.sxxy.juc.pudutor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {

	public static void main(String[] args) {
		
		Data data = new Data();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					data.increment();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}, "A").start();
		
        new Thread(() -> {
        	for (int i = 0; i <= 10; i++) {
				try {
					data.decrement();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}, "B").start();
        
        new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					data.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
		
        new Thread(() -> {
        	for (int i = 0; i <= 20; i++) {
				try {
					data.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "D").start();
	}

}

class Data {
	
	private int number = 0;
	
	// 创建锁
	Lock lock = new ReentrantLock();
	
	Condition condition = lock.newCondition();
	
	public  void increment() throws InterruptedException {
		
		
		// 加锁
		lock.lock();
		
		try {
			while (number != 0) {
				// 等待
				condition.await();
			}
			System.out.println("当前线程:"+ Thread.currentThread().getName()+ ": "+number);
			number ++;
			
			// 通知其他线程
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// 释放锁
			lock.unlock();
		}
		
	}
	
	public void decrement() throws InterruptedException {
		
		// 加锁
		lock.lock();
		
		try {
			while (number == 0) {
				// 等待
				condition.await();
			}
			System.out.println("当前线程:"+ Thread.currentThread().getName() + ": "+number);
			number --;
			
			// 通知其他线程
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
	
}