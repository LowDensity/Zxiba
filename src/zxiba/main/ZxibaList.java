package zxiba.main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
//主要是用來處理 find in range 之類的需求

public class ZxibaList<E> extends LinkedList<E> {
	

	public boolean containsAllWithinRange(int startIndex,int endIndex, E[] objs) {
		return containsAllWithinRange(startIndex,endIndex,Arrays.asList(objs));
	}
	
	public boolean containsAllWithinRange(int startIndex,int endIndex, Collection<E> objs) {
		Iterator<E> itr=this.iterator();
		int currentIndex=0;
		int matchCount=0;
		for(int i = 0 ; i < endIndex ;i++) {itr.hasNext();}
		while(itr.hasNext() && objs.contains(itr.next())) {
			if(currentIndex>endIndex) {break;}
			currentIndex+=1;
			matchCount+=1;
		}
		return matchCount == objs.size();
	}
	
	
	
	
	
	

}
