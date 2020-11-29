package zxiba.abstracts;

import java.util.List;

public class OptionValue {
	int startIndex;
	int endIndex;
	List<String> allArgs;
	
	public OptionValue(int startIndex,int endIndex,List<String> allArgs) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.allArgs = allArgs;
	}
	
	
	public boolean hasOption(String optionName) {
		for(int index=startIndex;index<=endIndex;index++) {
			if(allArgs.get(index).equals(optionName)) {return true;}
		}
		return false;
	}
	
	
	
	
}
