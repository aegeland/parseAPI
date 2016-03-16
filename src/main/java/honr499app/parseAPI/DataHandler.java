package honr499app.parseAPI;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class DataHandler {
	
	private Set<String> userIDs = new HashSet<String>();
	private ArrayList<ParseObject> objects;
	
	public DataHandler(){
		Parse.initialize("1y6Pbr9Xgwc1CMLZ6VaZWmiC4md6smub6GOOcbgg", "qY8jmn8iEh2lJIpLMvMIh7B2fuZO05NPnNz51kab");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("PollutionReading");
		query.limit(1000);
		try {
			objects = new ArrayList<ParseObject>(query.find());
			Collections.reverse(objects);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("PollutionReading");
		query.limit(1000);
		try {
			objects = new ArrayList<ParseObject>(query.find());
			Collections.reverse(objects);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	

	public void retrieveUsers(){
		//ParseQuery<ParseObject> query = ParseQuery.getQuery("PollutionReading");
		//query.whereEqualTo("userId", "117033098229820206439");
			//List<ParseObject> objects = query.find();
			for(ParseObject obj : objects) {
				double lat = obj.getParseGeoPoint("geo_point").getLatitude();
				double longi = obj.getParseGeoPoint("geo_point").getLongitude();
				userIDs.add(obj.getString("userId"));
				String timestamp = obj.getString("time");
				String temp = obj.getString("temp");
				/*System.out.println("latitude: " + lat + " || longitude: " + longi + " || timestamp: " + 
						timestamp + " || temperature: " + temp);*/
			}
		
		System.out.println(userIDs);
		System.out.println(getLocalTimeDate());
	}
	
	public Set<String> getUsers(){
		return userIDs;
	}
	
	public ArrayList<ParseObject> getObjects(){
		return objects;
	}
	
    private String getLocalTimeDate(){
        DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        Date dateobj = new Date();
        return df.format(dateobj);
    }
    
    public void removePoint(ParseObject po){
    	try {
			po.delete();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    public void plotPoint(String userId, String lat, String longi, String temperature){
    	NumberFormat formatter = new DecimalFormat("#0.0000000");
    	NumberFormat formatter2 = new DecimalFormat("#0.0");  
        ParseObject testObject = new ParseObject("PollutionReading");
        testObject.put("userId", userId);
        ParseGeoPoint point = new ParseGeoPoint(Double.parseDouble(formatter.format(Double.parseDouble(lat))), Double.parseDouble(formatter.format(Double.parseDouble(longi))));
        testObject.put("time", getLocalTimeDate());
        testObject.put("geo_point", point);
        testObject.put("temp", formatter2.format(Double.parseDouble(temperature)) + " degrees");
        testObject.saveInBackground();
    }

}
