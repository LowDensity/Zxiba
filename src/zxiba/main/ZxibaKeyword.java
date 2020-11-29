package zxiba.main;

import java.util.LinkedList;
import java.util.List;

public class ZxibaKeyword {
	private String  keywordName;
	private List<ZxibaOption> options;
	
	
	public ZxibaKeyword(String keywordName,String... options) {
		this.keywordName=keywordName;
		this.options=new LinkedList<ZxibaOption>();
		for(int i = 0;i< options.length;i++) {
			this.options.add(new ZxibaOption(options[i]));			
		}
	}
	
	
	//設定Option的種類
	/*
	 * type
	 * 	1 : boolean , 只有存在與否
	 *  2 : range limited value : 存在多種允許值
	 *  3 : unlimited value
	 * */
//	public void setOption(String optionName,int optType,String...values) {
//		ZxibaOption optionToSet=null;
//		for(ZxibaOption zopt : options) { 
//			if(zopt.isNamed(optionName)) {
//				optionToSet=zopt;
//				break;
//			}
//		}
//		//zxiba options are default to "Unlimited"
//		optionToSet.setOptType(optType);
//		optionToSet.setValues(values);
//		
//	}
	
	//取得 keyword後方跟著的所有參數，不做任何處理。
	public String[] getFollowingArgs(boolean includeKeyword) {
		return null;
	}
	
	
	
	
}
