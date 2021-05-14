package com.sxxy.juc.colletion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

	public static void main(String[] args) {
		
		// Set<String> list = new HashSet<>();
		// List<String> list = new Vector<>();
	   // Set<String> list = Collections.synchronizedSet(new HashSet<>());
		Set<String> list = new CopyOnWriteArraySet<>();
		
		for (int i = 0; i < 100; i++) {
			
			new Thread(()-> {
			  list.add(UUID.randomUUID().toString().substring(0,5));
			  System.out.println(list);
			},String.valueOf(i)).start();
			
		}
		
		

	}

}
