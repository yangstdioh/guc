package com.sxxy.juc.colletion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

	public static void main(String[] args) {
		
		//List<String> list = new ArrayList<>();
		// List<String> list = new Vector<>();
		// List<String> list = Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			
			new Thread(()-> {
			  list.add(UUID.randomUUID().toString().substring(0,5));
			  System.out.println(list);
			},String.valueOf(i)).start();
			
		}
		
		

	}

}
