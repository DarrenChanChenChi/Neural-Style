package cn.ybz21.hackthon.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.ybz21.hackthon.bean.PushDataBean;

import com.google.gson.Gson;

public class Jdpush {
	private static final String APPKEY = "367bc16df3f5a65e269567d5";
	private static final String MASTERSECRET = "9ad43f09372669a02a2542a0";
	public static JPushClient jpushClient = null;

	
	public static void main(String args[]){
		new Jdpush().sendDZResultPushMessage("1111", "aaaa");
	}

	/**
	 * 推送自定义消息接口.根据别名修改标签（tag）
	 * @param alias 别名
	 * @param content 推送内容
	 */
	private static void sendPushMessage(String alias, String content) {
		jpushClient = new JPushClient(MASTERSECRET, APPKEY);
		PushPayload payload = null;
		// For push, all you need do is to build PushPayload object.
		// PushPayload payload = buildPushObject_all_all_alert();
		// 判断用户别名和tag不为空的情况下才推送修改标签（tag）
		if (content != null && alias != null) {
			payload = PushPayload.newBuilder()
					.setAudience(Audience.alias(alias))
					.setPlatform(Platform.all())
					.setMessage(Message.content(content)).build();
		} else {
//			log.info("No notification - " + content);
		}
		try {
			System.out.println(payload.toString());
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result + "................................");
//			log.info("Got result - " + result);
		} catch (APIConnectionException e) {
			System.out.println(e.toString());
//			log.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			System.out.println(e.toString());
//			log.error("Error response from JPush server. Should review and fix it. ", e);
//			log.info("HTTP Status: " + e.getStatus());
//			log.info("Error Code: " + e.getErrorCode());
//			log.info("Error Message: " + e.getErrorMessage());
//			log.info("Msg ID: " + e.getMsgId());
		}
	}
	/**
	 * 
	 * @param userId
	 * @param url
	 */
	public static void sendDZResultPushMessage( String userId, String url) {
		String alias=userId;
		PushDataBean bean=new PushDataBean(alias,url);
		String content=new Gson().toJson(bean);
		sendPushMessage(alias, content);
	}
}