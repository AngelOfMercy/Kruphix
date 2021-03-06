package search;

import java.util.List;
import java.util.Timer;

import org.json.simple.JSONObject;

import updater.TimeUpdateChecker;

public abstract class Searcher {
	
	protected JSONObject data;
	protected static final double EDIT_DISTANCE_THRESHOLD = 0.85;
	protected static final int MAX_SEARCH_RESULTS = 3;
	
	public Searcher(){
		Timer t = new Timer();
		t.schedule(new TimeUpdateChecker(this), 0);
	}
		
	public abstract List<JSONObject> findCardListByName(String card);
	
	
	//Turns an array of JSON objects into strings for printing out.
	public String[] cardListToString(List<JSONObject> cardData) {
		String[] s = new String[cardData.size()];
		int i = 0;
		for(JSONObject obj : cardData){
			s[i++] = cardToString(obj);
		}
		return s;
	}
	
	//Turns a give JSON object into a string representation for output.
	public abstract String cardToString(JSONObject card);
	
	public abstract void setData(Object data);
	
	public abstract void loadData();
	
	
	protected List<JSONObject> getResultSubList(List<JSONObject> results){
		return results.subList(0, Math.min(results.size(), MAX_SEARCH_RESULTS));
	}
	
	public String printCardList(List<JSONObject> card_data) {
		String[] card_summery = cardListToString(card_data);
		 
		 if(card_summery.length > 0){
			 String message = "\n";
			 for(String s : card_summery){
				 message += s + "\n";
			 }
			 return (message);
		 }
		 else{
			 return ("That prophecy cannot be fulfilled");
		 }
	}
}
