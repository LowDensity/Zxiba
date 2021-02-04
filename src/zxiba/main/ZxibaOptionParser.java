package zxiba.main;

import java.util.List;

public class ZxibaOptionParser extends ZxibaParser {

	public ZxibaOptionParser(String[] args) {
		super(args);
	}

	@Override
	protected String addPrefix(String keyName) {
		return isKey(keyName) ? keyName : "--"+keyName;
	}

	@Override
	protected boolean isKey(String param) {
		return param.matches("--");//--opt ，opt = 前面加兩個減號
	}

	@Override
	public String[] getParamValues(String patternKey) {
		int[] startEnd;
		int start,end;
		startEnd=getStartEnd(patternKey);
		start=startEnd[0];
		end=startEnd[1];
		return allParams.subList(start, end+1).toArray(new String[end-start]);
	}

	//取值的時候同時檢查是否符合要求的數量
	@Override
	public String[] getParamValues(String patternKey, int valueCount, int range) {
		if(!hasKeyWithParam(patternKey,valueCount,range)) {return new String[0];}
		return getParamValues(patternKey);
	}
	





	
		
		
	
}
