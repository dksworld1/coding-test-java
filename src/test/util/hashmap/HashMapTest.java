package test.util.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	
	public void cloneTest() {
		Map<String,Map<String,String>> map = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		map2.put("key1", "val1");
		map2.put("key2", "val2");
		
		map.put("key1", map2);
		
		Map<String,Map<String,String>> map3 = (Map<String, Map<String, String>>) ((HashMap)map).clone();
		
		String val1 = map3.get("key1").get("key1");
		map3.get("key1").put("key1", "val1-1");
		System.out.println(val1 + "==" + map2.get("key1") + " ? true");
	}
	
	public void cloneTest2() {
		HashMap<String,Object> map = new HashMap<>();
		map.put("key1", "val1");
		
		HashMap<String,Object> map2 = (HashMap<String, Object>) map.clone();
		map2.put("key1", "val2");
		
		System.out.println(map.get("key1") + "==?" + map2.get("key1"));
	}
	
	public void putVsContainsKeyTest() {
		long start, end;
		
		int size = 10000000;
		String key = "key";
		
		start = System.currentTimeMillis();
		Map<String,Object> map = new HashMap<>();
		for(int i=0; i<size; i++) {
			map.put(key, i);
		}
		end = System.currentTimeMillis();
		System.out.println("put time: " + (end-start) + "ms");
		
		start = System.currentTimeMillis();
		map = new HashMap<>();
		for(int i=0; i<size; i++) {
			if(!map.containsKey(key)) {
				map.put(key, i);	
			}
		}
		end = System.currentTimeMillis();
		System.out.println("containsKey time: " + (end-start) + "ms");
		
		
	}
	
	public void putVsContainsKeyTest2() {
		long start, end;
		
		int size = 100000000;
		String[] keys = new String[20];
		for(int i=0; i<keys.length; i++) {
			keys[i] = String.valueOf(i);
		}
		
		start = System.currentTimeMillis();
		Map<String,Object> map = new HashMap<>();
		for(int i=0; i<size; i++) {
			map.put(keys[i%20], i);
		}
		end = System.currentTimeMillis();
		System.out.println("put time: " + (end-start) + "ms");
		
		start = System.currentTimeMillis();
		map = new HashMap<>();
		for(int i=0; i<size; i++) {
			String key = keys[i%20];
			if(!map.containsKey(key)) {
				map.put(key, i);	
			}
		}
		end = System.currentTimeMillis();
		System.out.println("containsKey time: " + (end-start) + "ms");
		
		
	}
	
	
	public static void main(String[] args) {
		HashMapTest test = new HashMapTest();
		test.cloneTest();
		test.cloneTest2();
		test.putVsContainsKeyTest();
		test.putVsContainsKeyTest2();
	}
}
