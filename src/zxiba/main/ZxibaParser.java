package zxiba.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import zxiba.exceptions.ParameterCountNotInRangeException;

//抽象Parser，定義共通行為
//這個系列一律建議先檢查再取值(但取值也可同時檢查)
public abstract class ZxibaParser {
	protected List<String> allParams;
	protected HashMap<String,int[]> startEnds;// {keywordName =>[startIndex , endIndex] ， 將結果儲存起來，接下來有需要的時候直接使用
	
	protected abstract String addPrefix(String keyName);	//將沒有任何前綴的名稱format成符合當下需求的樣子，如 abc => -opt 或 --opt
	protected abstract boolean isKey(String param); //是否是當下認定的 pattern key
	
	public abstract String[] getParamValues(String patternKey);//不論數量，有多少取多少
	
	public abstract String[] getParamValues(String patternKey,int valueCount,int range);//參數數量必須 介於value_count 跟 value_count + range，數量不合時回傳null

	
	public ZxibaParser(String[] args) {
		allParams = Arrays.asList(args);
		startEnds= new HashMap<String,int[]>();
	}
	
	
	protected boolean isKeyOrSeparator(String param) {
		return isSeparator(param) || isKey(param);
	}
	
	
	//是否是是分隔符號 "--"
	protected boolean isSeparator(String param) {
		return param.trim().equals("--");
	};
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
		start = getOptStart(addPrefix(keyName));
		if(valueCount<0 ) {return start!=-1 ;}
		end= getOptEndIndex(start);
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
	
	protected int[] getStartEnd(String patternKey) {
		int[] startEnd;
		if(startEnds.containsKey(patternKey)) {
			startEnd=startEnds.get(patternKey);
		}else
		{
			startEnd=new int[] {
					getOptStart(addPrefix(patternKey))
					,getEndIndex(addPrefix(patternKey))
					};	
		}
		return startEnd;
	}
	
	protected int getEndIndex(String keyName) {
		return getOptEndIndex(getOptStart(keyName));
	}
	
	protected int getOptEndIndex(int startIndex) {
		 // 找到下一個 option 或是 --
		int endIndex;
		for(endIndex = startIndex+1 ;!isKeyOrSeparator(allParams.get(endIndex)) ;endIndex++);
		return endIndex;
	}
	
	protected int getOptStart(String keyName) {
		return allParams.indexOf(keyName);
	}
	
	

	
}
