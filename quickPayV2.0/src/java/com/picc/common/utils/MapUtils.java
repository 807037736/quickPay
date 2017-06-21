package com.picc.common.utils;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.schedule.AddressInfo;

public class MapUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(MapUtils.class);
	/**
	 * 根据地址返回经纬度
	 * @param address
	 * @return
	 */
	public static GeocoderVo trandAddress(String address) {
		GeocoderVo vo =new GeocoderVo();
		try{
			HttpClient client = new HttpClient(); 
			client.getHostConfiguration().setProxy("58.1.32.198",9090);
			String add=URLEncoder.encode(address, "UTF-8");
			System.out.println(add);
			HttpMethod method = new GetMethod("http://api.map.baidu.com/geocoder/v2/?address="+add+"&output=json&ak=E609bfedaa87ff31217e51f5114d6799");
			client.executeMethod(method);
			 JSONObject json=JSONUtil.toJSONObject(method.getResponseBodyAsString());
			 if(json!=null){
				 JSONObject json1=JSONUtil.toJSONObject(json.get("result").toString());
				 if(json1!=null){
					 JSONObject json2=JSONUtil.toJSONObject(json1.get("location").toString());
					 if(json2!=null){
						 vo.setLongitude(json2.get("lng").toString());
						 vo.setLatitude(json2.get("lat").toString());
					 }
				 }
			 }
			 method.releaseConnection();
		}catch(Exception e){
			 e.printStackTrace();
				logger.error("", e);
		}
		return vo;
	}
	
	
	/**
	 * 根据经纬度返回地址
	 * @param address
	 * @return
	 */
	public static AddressInfo trandtoAddress(String longitude,String latitude) {
		AddressInfo info =null;
		try{
			
			HttpClient client = new HttpClient(); 
			client.getHostConfiguration().setProxy("58.1.32.198",9090);
			/*String add=URLEncoder.encode(address, "UTF-8");
			System.out.println(add);*/
			HttpMethod method = new GetMethod("http://api.map.baidu.com/geocoder/v2/?ak=E609bfedaa87ff31217e51f5114d6799&location="+longitude+","+latitude+"&output=json");
			client.executeMethod(method);
			 JSONObject json=JSONUtil.toJSONObject(method.getResponseBodyAsString());
			 if(json!=null){
				 JSONObject json1=JSONUtil.toJSONObject(json.get("result").toString());
				 if(json1!=null){
					 info = new AddressInfo();
					  String address=json1.get("formatted_address").toString();
					  info.setAddressCName(address);
					  JSONObject json2=JSONUtil.toJSONObject(json1.get("addressComponent"));
					  if(json2!=null){
						  //{"city":"北京市","district":"海淀区","province":"北京市","street":"中关村大街","street_number":"27号1101-08室"}
						  
						  info.setCity(json2.getString("city"));
						  info.setDistrict(json2.getString("district"));
						  info.setProvince(json2.getString("province"));
						  info.setStreet(json2.getString("street"));
					  }
				 }
			 }
			 method.releaseConnection();
		}catch(Exception e){
			 e.printStackTrace();
				logger.error("", e);
		}
		return info;
	}
	
	
	public static double getDistatce(double lat1, double lat2, double lon1,double lon2) { 
        double R = 6371; 
        double distance = 0.0; 
        double dLat = (lat2 - lat1) * Math.PI / 180; 
        double dLon = (lon2 - lon1) * Math.PI / 180; 
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) 
                + Math.cos(lat1 * Math.PI / 180) 
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) 
                * Math.sin(dLon / 2); 
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R*1000; 
        return distance; 
    }
	public static void main(String[] args) {
		//trandtoAddress("114.08151","22.562605");
		//getDistatce(22.553259,22.552258,114.155602,114.143816); 
		getGridID(0.1,0.2);
			/*double longitude = 114.389;
			double latitude = 22.485;
			int x = (int)((longitude-113.733300)/0.01);
			
			int y = 92*(int)((latitude-22.4)/0.01);
			
			int grid = x+y;
			System.out.println(grid);*/
		

	}
	//生成地图经纬度的格子
	public static String getGridID (double x,double y){
		//113.733300,22.4；114.64706,22.85461
		//x--经度   y-纬度
		x=113.733300;
		y=22.4;
		StringBuffer sql =new StringBuffer();
		int i=0;
		for (y=22.4;y<=22.85461;y=y+0.01){
			for(x=113.733300;x<=114.64706;x=x+0.01){
				int dx = (int)((x - 113.733300) / 0.01);
				int dy = (int)((y -22.4) / 0.01);
				int GridID = dy * 92 + dx;
				double x1=x+0.01;
				double y1=y+0.01;
				/*if(GridID%100==1){
					System.out.println(GridID +" : "+x+" "+x1+" "+y+" "+y1);
				}*/
				i++;
				sql.append("insert into bm_T_szgrid (GRIDID, GRIDUP, GRIDLEFT, GRIDDOWN, GRIDRIGHT, VALIDSTATUS) values ('"
				+i+"', '"+x1+"#"+y+"', '"+x1+"#"+y1+"', '"+x+"#"+y1+"', '"+x+"#"+y+"', '1');");
			}
		}
		System.out.println(sql);
		return null;
	}
}
