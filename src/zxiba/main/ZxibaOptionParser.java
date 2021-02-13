package zxiba.main;

import java.util.HashMap;
import java.util.List;

public class ZxibaOptionParser extends ZxibaAbstractParser {
	
	protected HashMap<String,int[]> startEnds;
	protected String keyName = "";// 如果沒有設定，就無法使用
	public ZxibaOptionParser(String[] args ,String keyName) {
		super(args,keyName);
	}

	public ZxibaOptionParser(List<String> args ,String keyName) {
		super(args,keyName);
	}
	

	@Override
	protected String addPrefix(String keyName) {
		return isOption(keyName) ? keyName : "--"+keyName;
	}

	protected boolean isOption(String param) {
		return param.trim().matches("(\\s|^)-{2}[\\w]+") ;//--opt ，opt = 前面加兩個減號
	}
	
	protected boolean isKeyOrSeparator(String param) {
		return isSeparator(param) || isOption(param);
	}

	

	@Override
	public String[] getParamValues(String patternKey) {
		// TODO Auto-generated method stub
		return null;
	}



		
	
}
