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
	
	private ZxibaParser(String[] args,String keyName) {
		super(args,"");
		this.keyName=keyName;
		keywords.put("", this);
		
	}
	
	public ZxibaParser(String[] args, String... parseFirst) {
		this(args,"");
		for(String m : parseFirst) {
			keywords.put(m, new ZxibaKeywordParser(args,m));
		}
	}
		
	public ZxibaParser(String[] args) {
		this(args,"");
	}
	
	public boolean hasKeyWithParam(String keyName,int valueCount,int range) {
		ZxibaKeywordParser keyword;
		keyword = keywords.containsKey(keyName) ? keywords.get(keyName) : new ZxibaKeywordParser(allParams,keyName); 
		return keyword.hasKeyWithParam(keyName, valueCount, range);
	}
	
	//檢查是否帶有指定的keyWord以及required parameter(option)
	public boolean hasKeyWithOptions(String keyName,String... optNames) {
		ZxibaKeywordParser keyword;
		keyword = keywords.containsKey(keyName) ? keywords.get(keyName) : new ZxibaKeywordParser(allParams,keyName);
		if(!keyword.containsKeyName) {return false;}
		return keyword.hasOptions(optNames);
	}
	
	//直接取得keyword底下的option的值
	public String[] getKeywordOption(String keyName,String optName) {
		ZxibaKeywordParser keyword;
		keyword = keywords.containsKey(keyName) ? keywords.get(keyName) : new ZxibaKeywordParser(allParams,keyName);
		return keyword.getOption(optName).getValues();
	}
	
	//直接取Keyword底下的值
	public String[] getKeywordValues(String keyName) {
		ZxibaKeywordParser keyword;
		keyword = keywords.containsKey(keyName) ? keywords.get(keyName) : new ZxibaKeywordParser(allParams,keyName);
		return keyword.getValues();
	}
	
	
	
	
	

	


	
		

	
}
