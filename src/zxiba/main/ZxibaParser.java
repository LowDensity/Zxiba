package zxiba.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//basic parser
public class ZxibaParser {
	private List<String> allArgs;
	private LinkedList<ZxibaKeyword> keywords=null;
	private LinkedList<String> keywordNames = null;
	private HashMap<String,int[]> startEnds;// {keywordName =>[startIndex , endIndex]
	
	
	/*
	 * @comment 直接用當下的資源產生一個Parser，Keyword跟Option可以稍後再設定
	 * */
	public ZxibaParser(String[] args) {
		allArgs = Arrays.asList(args);
		keywordNames = new LinkedList<String>();
		startEnds= new HashMap<String,int[]>();
	}
	
	/*
	 * 產生一個未初始化的ZxibaParser
	 * 
	 * */
	public ZxibaParser() {}
	
	
	//詢問一個複雜狀況
	// 存在keyword 名稱
	// 必須含有傳入的所有參數
	public boolean hasPattern(String keyword, String... optionsExisted) {
		int startIndex;
		int[] startAndEnd;
		startIndex=keywordIndex(keyword);
		
		if(!startEnds.containsKey(keyword)&&startIndex==-1){return false;}
		startAndEnd = startEnds.get(keyword);
		
		if(startAndEnd==null) {
			startAndEnd = new int[] {startIndex,getEndIndex(startIndex)};
			startEnds.put(keyword,startAndEnd);
		}
		for(int x= 0 ; x < optionsExisted.length;x++) {
			optionsExisted[x]="--"+optionsExisted[x];
		}
		//考慮自己寫個findInRange來處理，目前先這樣就好。
		return allArgs.
				subList(startAndEnd[0], startAndEnd[1]).
				containsAll(Arrays.asList(optionsExisted));
	}
	//檢查是否含有指定的keyword並且帶有指定數量的參數，以及含有傳入的變數
	public boolean hasPattern(String keyword,int valueCount,String... options) {
		
		return false;
	}
	
	private int getValueCount(String keywordName) {
		
		return -1;
	}
		
	//前娺 "-" , ex : -msg
	private boolean isKeyword(String keywordName) {
		return false;
	}
	
	//放在keyword後面並且沒有任何前娺
	private boolean isValue(String arg) {
		return false;
	}
	
	//放在 keyword 後面並且前墜"--"
	public boolean isOption(String arg) {
		return false;
	}
	
	
	
	
	
	/*
	 * keyword : 加前綴 "-"
	 * Option : 必須在keyword之後，並且加前綴 "--"
	 * */
	//要符合keyword的規格
	private int keywordIndex(String keywordName) {return allArgs.indexOf("-"+keywordName);}
	
	public boolean hasKeyword(String keywordName) {return keywordIndex(keywordName)==-1;}
	
	private int getEndIndex(int startIndex) {
		int endIndex = startIndex;
		for(endIndex=startIndex+1;endIndex<allArgs.size();endIndex++) {
			if(allArgs.get(endIndex).matches("Keyword pattern")) {break;}
		}
		return endIndex;
		
	}
	
	//有傳入結構時直接處理
	public void parse() {
		
	}
	
	
}
