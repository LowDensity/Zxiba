package zxiba.abstracts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//parser holds Keyword and Optiond
/*
 * -Parser
 * |
 * -Keyword
 * 	-Option
 * 
 * */
public abstract class Parser {
	private List<String> rawArgs;
	private LinkedList<Keyword> keywords=new LinkedList<Keyword>();
	
	public Parser(String[] args) {
		rawArgs=Arrays.asList(args);		
	}
	
	//設定keyword
	public void addKeyWord(Keyword kw) {keywords.add(kw);}
	
	public void addKeyWord(String keywordName, String... Option) {
		
		
	}
	
	public void setOptionValue(String OptionName,String OptionValue) {}
	
	//match 一個相對複雜的狀況，可以用來處理
	public boolean matchPattern(String keyword,Option... opts) {
		
		return false;
	};

	
}
