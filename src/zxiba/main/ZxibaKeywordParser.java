package zxiba.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import zxiba.exceptions.ParameterCountNotInRangeException;

/*直接在此處實作公用方法
 * 
 * 假設input都是simple : java -jar xxxx.jar  kValue1 kValue2 --opt1 optValue2 --opt2 optValue3 
 * 沒有複雜Input，不會出現一個以上的Keyword，這一層預設keyword都是""
 * 會將keyword視為帶有一個 "-"符號的value
 * 沒有hasPattern
 * */
public  class ZxibaKeywordParser extends ZxibaOptionParser {
	
	private HashMap<String,ZxibaOptionParser> options=new HashMap<String,ZxibaOptionParser>();
	
	public ZxibaKeywordParser(String[] args,String keyName) {
		super(args,keyName);
		// TODO Auto-generated constructor stub
	}
	
	public ZxibaKeywordParser(List<String> args,String keyName) {
		super(args,keyName);
		// TODO Auto-generated constructor stub
	}
	
	
	


	@Override
	protected String addPrefix(String keyName) {
		return isKey(keyName) ? keyName : "-"+keyName;
	}
	
	protected boolean isKeyOrSeparator(String param) {
		return isSeparator(param) || isKey(param);
	}

	protected boolean isKey(String param) {
		// 是 keyword但〝不是Option的keyword
		// keyWord : 前面有一個 "-"號
		return param.trim().matches("(\\s|^)-{1}[\\w]");
	}

	//檢查是否帶有指定的option
	public boolean hasOptions(String[] optNames) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ZxibaOptionParser getOption(String optName) {
		ZxibaOptionParser optParser;
		optParser =  !options.containsKey(optName) ? new ZxibaOptionParser(allParams,optName) : options.get(optName);
		return optParser;
	}
	

	
	

}
