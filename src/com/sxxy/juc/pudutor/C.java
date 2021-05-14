package com.sxxy.juc.pudutor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {

	public static void main(String[] args) {
		
		Data1 data = new Data1();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				data.printA();
			}
		}, "A").start();
		
        new Thread(() -> {
        	for (int i = 0; i <= 10; i++) {
        		data.printB();
			}
		}, "B").start();
        
        new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				data.printC();
			}
		}, "C").start();
	}

}

class Data1 {
	
	private int number = 1;
	
	// 创建锁
	private Lock lock = new ReentrantLock();
	
	// 创建监视器
	private Condition condition1 = lock.newCondition();
	
	private Condition condition2 = lock.newCondition();
	
	private Condition condition3 = lock.newCondition();
	
	public void printA() {
		
		lock.lock();
		
		try {
			
			while(number != 1) {
				condition1.await();
			}
			
			System.out.println(Thread.currentThread().getName()+ "=>AAAAA");
			
			number = 2;
			
			// 唤醒B线程
			condition2.signal();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	
    public void printB() {
    	
        lock.lock();
		
		try {
			
			while(number != 2) {
				condition2.await();
			}
			
			System.out.println(Thread.currentThread().getName()+ "=>BBBBBB");
			
			number = 3;
			
			// 唤醒C线程
			condition3.signal();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
    
    public void printC() {
        lock.lock();
		
		try {
			
			while(number != 3) {
				condition3.await();
			}
			
			System.out.println(Thread.currentThread().getName()+ "=>CCCCCCC");
			
			number = 1;
			
			// 唤醒A线程
			condition1.signal();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}