package com.picc.common.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxAccessTokenService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void setAccessToken() {
//		logger.info("定时任务重新获取微信AccessToken");
//		String appId = Constants.getJSSDK_APPID();
//		String appSecret = Constants.getJSSDK_APPSECRET();
//		appId = "wxc54c7a8be1edee7e";
//		appSecret = "0c4bb6b13470ce5f6191bfd391048992";
// 		少刚
//		appId = "wxb45676f77463f5cb";
//		appSecret = "5d159a729e3701a2daa3e72f52264fc4";
//		try {
//			Map<String, Object> map = Constants.getWX_ACCESS_TOKEN();
//			JSONObject accessToken = Sign.getAccessToken(appId, appSecret);
//			logger.info("token:" + accessToken.toString());
//			map.put("token", accessToken);
//			JSONObject ticket = Sign.getJsApiTicket(accessToken.getString("access_token"));
//			map.put("ticket", ticket);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("重新获取微信AccessToken失败");
//		}
	}
}
