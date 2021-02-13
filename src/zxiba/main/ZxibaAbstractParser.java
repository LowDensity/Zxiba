package zxiba.main;

import java.util.Arrays;
import java.util.List;
//抽象Parser，定義共通行為
//這個系列一律建議先檢查再取值(但取值也可同時檢查)
public abstract class ZxibaAbstractParser {
	protected List<String> allParams;
	protected boolean containsKeyName = false;
	private int start;
	private int end;
	protected abstract String addPrefix(String keyName);	//將沒有任何前綴的名稱format成符合當下需求的樣子，如 abc => -opt 或 --opt
	//protected abstract boolean isKey(String param); //取消isKey的設定，需要分開
	

	public ZxibaAbstractParser(String[] args ,String keyName) {
		this(Arrays.asList(args),keyName);
	}
	
	public ZxibaAbstractParser(List<String> args,String keyName) {
		start =-1;
		end = -1;
		allParams = args;
		start = getOptStartIndex(addPrefix(keyName));
		end = getOptEndIndex(start);
		containsKeyName = start !=-1 &&  end !=-1 ;
	}
	
	
	public boolean hasValueCount(int optionCount , int range) {
		return false;
	}
	
	//直接取值
	public String[] getValues() {
		if(!containsKeyName) {return new String[0];}
		//去掉頭
		return allParams.subList(start+1, end).toArray(new String[end-start-1]);
	}
	
	//直接取值，如果不是單一值，串成一串回傳
	public String getValue() {
		return String.join(",", getValues());
	}
	
	//取值同時檢查數量
	public String[] getValues(int valueCount,int range) {
		String[] values= this.getValues();
		range = range < 0 ? range *-1 :range;
		//如果數量不對直接log並丟出Exception
		if(values.length > valueCount+range || values.length < valueCount-range) {
			System.err.println("keyname does not contain required amount of parameters");
		}
		return values;
	}
	
	
	protected boolean isKeyOrSeparator(String param) {
		return isSeparator(param);
	}
	
	
	//是否是是分隔符號 "--"
	protected boolean isSeparator(String param) {
		return param.trim().equals("--");
	};
	
		
	protected int getOptEndIndex(String keyName) {
		return getOptEndIndex(getOptStartIndex(keyName));
	}
	
	protected int getOptEndIndex(int startIndex) {
		 // 找到下一個 option 或是 --
		 if(startIndex==-1){return -1;}
		int endIndex;
		for(endIndex = startIndex+1 ;endIndex < allParams.size() && !isKeyOrSeparator(allParams.get(endIndex)) ;endIndex++);
		return endIndex;
	}
	
	protected int getOptStartIndex(String keyName) {
		return keyName.equals("") ? 0 : allParams.indexOf(keyName);
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
		//注意只在start end的範圍中尋找
		int pValueCount ;

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
							, valueCount+range
							, pValueCount));
			return false;
		}
		return true;
		
	}
	//不論數量，有多少取多少
	public String[] getParamValues(String patternKey) {
		return allParams.subList(start+1, end).toArray(new String[start-end-1]);
	}
	
	//參數數量必須 介於value_count 跟 value_count + range，數量不合時回傳null
	public String[] getParamValues(String patternKey,int valueCount,int range) {
		if(!hasKeyWithParam(patternKey,valueCount,range)) {return new String[0];}
		return getParamValues(patternKey);
	}



}
