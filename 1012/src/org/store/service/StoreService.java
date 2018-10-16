package org.store.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.store.domain.StoreVO;

public class StoreService {
	// 0-9 일식 10-19 양식 20-29 한식
	private List<StoreVO> storeList;
	
	public StoreService()throws Exception {
		
		String[] fileArr = {"C:\\zzz\\store1.txt",
				"C:\\zzz\\store2.txt",
				"C:\\zzz\\store3.txt"};
		
		storeList = new ArrayList<>();
		
		for (String fileName : fileArr) {
			
			Scanner scanner =
					new Scanner(new FileInputStream(fileName),"UTF-8");
			
			scanner.nextLine();
			
			for(int i = 0; i < 10; i++) {
				String line = scanner.nextLine();
				
				String[] arr = line.split(" ");
				
				System.out.println(Arrays.toString(arr));
				
				try {
				storeList.add(new StoreVO(arr[2],
						Double.parseDouble(arr[0])
						, Double.parseDouble(arr[1]),
						arr[3])
						);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			scanner.close();
			
		}//end for
		
		System.out.println(storeList.size());
		
	}
	
	public List<StoreVO> getNearKind(String[] arr, double lat, double lng){
		
		List<StoreVO> result = new ArrayList<>();
		
		List<StoreVO> kList = storeList.subList(20, 29);
		List<StoreVO> wList = storeList.subList(10, 19);
		List<StoreVO> jList = storeList.subList(0, 9);
		
		for(int i = 0; i < arr.length; i++) {
			String type = arr[i];
			
			if(type.equals("k")) {
				result.addAll(getNear(kList,lat,lng));
			}else if(type.equals("j")) {
				result.addAll(getNear(jList,lat,lng));
			}else {
				result.addAll(getNear(wList,lat,lng));
			}
		}
		
		return result;
		
	}
	
	public List<StoreVO> getNear(List<StoreVO> list,double lat, double lng){
		
		Collections.sort(list, (s1, s2) ->{
			
			double d1 = s1.calc(lat, lng);
			double d2 = s2.calc(lat, lng);
			if(d1 > d2) {
				return 1;
			}else if(d1 < d2) {
				return -1;
			}else {
				return 0;
			}
			
		});
		
		return list.subList(0, 5);
	}
	
	public static void main(String[] args)throws Exception {
		
		StoreService obj = new StoreService();
		System.out.println(obj.getNearKind(new String[] {"k","j"},37.572547, 126.974358));
		
	}

}
