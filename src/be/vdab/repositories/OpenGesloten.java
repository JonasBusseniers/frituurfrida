package be.vdab.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OpenGesloten {
	private static final Map<Long,String> deuren = new ConcurrentHashMap<>();
	
		
	
public OpenGesloten(int aantalDeuren){
	for (Long i =1L;i <= aantalDeuren; i++){
		deuren.put(i, "deurtoe");
	}
}


public Map<Long, String> getDeuren() {
	return deuren;
}





}
