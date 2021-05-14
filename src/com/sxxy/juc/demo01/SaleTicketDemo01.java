package com.sxxy.juc.demo01;

public class SaleTicketDemo01 {
	public static void main(String[] args) {
				
		Ticket ticket = new Ticket();
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

class Ticket {
	
	private int ticket = 50;
	
	public synchronized void sale() {
		String name = Thread.currentThread().getName();
		
		if (ticket > 0) {
			System.out.println("当前剩余票数: " + ticket+ "当前线程" + name );
			ticket -- ;
		} else {
			System.out.println("票已卖完");
			return;
		}
	}
}