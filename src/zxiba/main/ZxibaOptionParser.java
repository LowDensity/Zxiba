package zxiba.main;

import java.util.List;

public class ZxibaOptionParser extends ZxibaParser {

	public ZxibaOptionParser(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String addPrefix(String optionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isKey(String param) {
		// TODO Auto-generated method stub
		return param.matches("-");
	}

	@Override
	public String[] getParamValues(String patternKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParamValues(String patternKey, int valueCount, int range) {
		// TODO Auto-generated method stub
		return null;
	}





	
		
		
	
}
