package com.sxxy.juc.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {

	public static void main(String[] args) {
				
		Ticket1 ticket = new Ticket1();
		new Thread(()-> {
			for(int i = 0 ; i<=20 ; i++) {
				ticket.sale();
			}
		}, "AA").start();;
		
		new Thread(()-> {
			for(int i = 0 ; i<=20 ; i++) {
				ticket.sale();
			}
		}, "BB").start();
		
		new Thread(()-> {
			for(int i = 0 ; i<=20 ; i++) {
				ticket.sale();
			}
		}, "CC").start();
		
		
	}

}

class Ticket1 {
	
	private int ticket = 50;
	
	// ��
	Lock lock = new ReentrantLock();
	
	public void sale() {
		String name = Thread.currentThread().getName();
		
		lock.lock();//���� ����ƽ��
		
		try {
			
			if (ticket > 0) {
				System.out.println("��ǰʣ��Ʊ��: " + ticket+ "��ǰ�߳�" + name );
				ticket -- ;
			} else {
				System.out.println("Ʊ������");
				return;
			}
		} catch (Exception e) {
			
		} finally {
			// �ͷ���
			lock.unlock();
		}
		
	}
}
