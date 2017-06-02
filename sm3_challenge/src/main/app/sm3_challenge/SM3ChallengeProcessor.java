package sm3_challenge;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class SM3ChallengeProcessor implements Callable {

	HashMap<Integer,String> result = new HashMap<Integer,String>();
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		
		Map<String, String> queryParams = eventContext.getMessage().getInboundProperty("http.query.params");
		int min= Integer.valueOf(queryParams.get("min"));
		int max= Integer.valueOf(queryParams.get("max"));
		
		result =multiplesof7andor3(min,max);
		
		
		if(result!=null){
			return result;
		}
		
			return null;
		
		
	}
	
	
	public HashMap<Integer,String> multiplesof7andor3(int start, int end){
		
		HashMap<Integer,String> resultMap = new HashMap<Integer,String>();
		
		boolean multiple3flag =false;
		boolean multiple7flag =false;
		for (int i=start;i<=end;i++){
			multiple3flag =false;
			multiple7flag =false;
			
			if(i%3==0){
				multiple3flag =true;
				
			}
			 if(i%7==0){
				multiple7flag =true;
				
			}
			
			 if (multiple3flag&&!multiple7flag)
				 resultMap.put(i, "ME");
			 else if(!multiple3flag&&multiple7flag)
				 resultMap.put(i, "MS3");
			 else if(multiple7flag&&multiple3flag){
				resultMap.put(i, "MS3 and ME");
			}
		}
		
		
		return resultMap;
		
	}

}
