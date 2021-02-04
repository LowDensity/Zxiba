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

	public ZxibaKeywordParser(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String addPrefix(String keyName) {
		return isKey(keyName) ? keyName : "-"+keyName;
	}
	

	@Override
	protected boolean isKey(String param) {
		// keyWord : 前面有一個 "-"號，--事option
		param.trim().matches("-{1}*");
		return false;
	}
	

}
