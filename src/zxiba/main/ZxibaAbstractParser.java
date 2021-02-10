package zxiba.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//抽象Parser，定義共通行為
//這個系列一律建議先檢查再取值(但取值也可同時檢查)
public abstract class ZxibaAbstractParser {
	protected List<String> allParams;
	protected HashMap<String,int[]> startEnds;// {keywordName =>[startIndex , endIndex] ， 將結果儲存起來，接下來有需要的時候直接使用
	private boolean containsKeyName = false;
	private String keyName;
	private int start,end;
	protected abstract String addPrefix(String keyName);	//將沒有任何前綴的名稱format成符合當下需求的樣子，如 abc => -opt 或 --opt
	protected abstract boolean isKey(String param); //是否是當下認定的 pattern key
	
	public abstract String[] getParamValues(String patternKey);//不論數量，有多少取多少
	
	public abstract String[] getParamValues(String patternKey,int valueCount,int range);//參數數量必須 介於value_count 跟 value_count + range，數量不合時回傳null

	public ZxibaAbstractParser(String[] args ,String keyName) {
		this(Arrays.asList(args),keyName);
	}
	
	public ZxibaAbstractParser(List<String> args,String keyName) {
		this.keyName=keyName;
		allParams = args;
		int[] startEnd = getStartEnd(keyName);
		if(startEnd[0]== -1 ) {return ;}
		start = startEnd[0];
		end = startEnd[1];
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
		return isSeparator(param) || isKey(param);
	}
	
	
	//是否是是分隔符號 "--"
	protected boolean isSeparator(String param) {
		return param.trim().equals("--");
	};

	
	
	protected int[] getStartEnd(String patternKey) {
		int[] startEnd;
		if(startEnds.containsKey(patternKey)) {
			startEnd=startEnds.get(patternKey);
		}else
		{
			startEnd=new int[] {
					getOptStartIndex(addPrefix(patternKey))
					,getOptEndIndex(addPrefix(patternKey))
					};	
		}
		return startEnd;
	}
	
		
	protected int getOptEndIndex(String keyName) {
		return getOptEndIndex(getOptStartIndex(keyName));
	}
	
	protected int getOptEndIndex(int startIndex) {
		 // 找到下一個 option 或是 --
		int endIndex;
		for(endIndex = startIndex+1 ;endIndex < allParams.size() && !isKeyOrSeparator(allParams.get(endIndex)) ;endIndex++);
		return endIndex;
	}
	
	protected int getOptStartIndex(String keyName) {
		return allParams.indexOf(keyName);
	}
	


}
