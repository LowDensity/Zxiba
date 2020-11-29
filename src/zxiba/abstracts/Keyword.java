package zxiba.abstracts;

import java.util.Arrays;
import java.util.List;

public abstract class Keyword {
	private final String name;
	private final List<Option> options;
	
	//define a New Keyword
	public Keyword(String keywordName,Option... options) {
		this.name=keywordName;
		this.options=Arrays.asList(options);
		
	}
	public boolean isKeyWordName(String keywordName) {
		return name.equals(keywordName);
	}
	
	
	
}
