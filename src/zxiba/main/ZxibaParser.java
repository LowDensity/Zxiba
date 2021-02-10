package zxiba.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import zxiba.exceptions.ParameterCountNotInRangeException;


/*
 * 最後的完整版Parser，包含多重Keyword等等功能
 * 
 * */
public class ZxibaParser extends ZxibaKeywordParser{

	private HashMap<String,ZxibaKeywordParser> keywords=new HashMap<String,ZxibaKeywordParser>();
	
	public ZxibaParser(String[] args) {
		super(args);
	}
	
	//是否有此option存在，不理會參數數量限制
	public boolean hasKey(String keyName) {
		return hasKeyWithParam(keyName,-1,0);
	}
	
	
	 /* 檢查是否帶有指定keyword以及 value_count ~value_count + range 數量的 value，value_count
	 * value_count 與 range 均為0的時候表示此keyword 不接受任何參數
	 * 主要用來處理0 - n 個參數的狀況
	 * value_count 為 -1的時候表示參數數量沒有限制
	 * 未傳入range時傳入之參數數量必須與value_count相同
	 * value_count=-1 的時候沒有限制參數數量
	 * 注意 value_count==-1 時 range 會被無視
	 * 超出範圍時
	 * */ 
	public boolean hasKeyWithParam(String keyName,int valueCount,int range) {
		int start,end,pValueCount;
		
		if(startEnds.containsKey(keyName)){return startEnds.get(keyName)!=null;}
		start = getOptStartIndex(addPrefix(keyName));
		if(valueCount<0 ) {return start!=-1 ;}
		end = getOptEndIndex(keyName);
		startEnds.put(keyName,null);
		
		if(valueCount==0 && range==0 && end!=start){
			System.err.println(String.format("Incorrect number of parameters , require 0 , received %d", end-start));
			return false;
		}
		
		
		if((pValueCount=end-start) != valueCount  && range==0 ) {
			System.err.println(
					String.format("Incorrect number of parameters , require %d , received %d"
					, valueCount
					, pValueCount));
			return false;
		}
		
		//負數的話先做一次轉換確保下面的邏輯是正確的
		range = range < 0 ? range*-1 : range;
		if( (valueCount-range) < pValueCount || pValueCount > (valueCount+range)) {
			System.err.println(
					String.format("Incorrect number of parameters , maximum %d  minimum %d , received %d"
							, valueCount-range
							,valueCount+range
							, pValueCount));
			return false;
		}
		startEnds.put(keyName, new int[] {start,end});
		return true;
		
	}

	
		

	
}
