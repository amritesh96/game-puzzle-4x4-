package com;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PuzzleController {

	@RequestMapping(value = "getArray", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject solveBoardAndReturnResponse(@RequestBody String data) throws ParseException {
		System.out.println("i was here "+data);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(data);
		
		String[] arr=json.get("key").toString().split(",");	
		int k=0;
		int[][] board=new int[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				board[i][j]=Integer.parseInt(arr[k++]);
			}
		}
		ArrayList<Integer> ans=SolvePuzzle.solvePuzzle(board);
		int[] steps = ans.stream().mapToInt(Integer::intValue).toArray();
		System.out.println(steps.length);
		String jsArray=toJavascriptArray(steps);
	    JSONObject responseData = new JSONObject();
	    responseData.put("responseData",jsArray);
	    System.out.println(responseData.toString());
		return responseData;
	}
	
	public static String toJavascriptArray(int[] arr){
	    StringBuffer sb = new StringBuffer();
	    for(int i=0; i<arr.length; i++){
	        sb.append(arr[i]);
	        if(i+1 < arr.length){
	            sb.append(",");
	        }
	    }
	    return sb.toString();
	}
}
