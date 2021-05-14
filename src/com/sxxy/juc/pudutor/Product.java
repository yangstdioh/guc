package com.sxxy.juc.pudutor;

public class Product {

	public static void main(String[] args) {
		
		Pro pro = new Pro();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					pro.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();
		
        new Thread(() -> {
        	for (int i = 0; i <= 10; i++) {
				try {
					pro.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "B").start();
        
        new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					pro.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
		
        new Thread(() -> {
        	for (int i = 0; i <= 10; i++) {
				try {
					pro.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "D").start();
	}
}


class Pro {
	
	private int number = 0;
	
	public synchronized void increment() throws InterruptedException {
		while (number != 0) {
			this.wait();
		}
		System.out.println("��ǰ�߳�:"+ Thread.currentThread().getName()+ ": "+number);
		number ++;
		
		// ֪ͨ�����߳�
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException {
		while (number == 0) {
			this.wait();
		}
		System.out.println("��ǰ�߳�:"+ Thread.currentThread().getName() + ": "+number);
		number --;
		
		// ֪ͨ�����߳�
		this.notifyAll();
	}
}
