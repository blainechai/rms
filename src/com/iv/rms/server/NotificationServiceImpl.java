package com.iv.rms.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.client.NotificationService;
import com.iv.rms.client.NotificationViews;
import com.iv.rms.client.SimpleNotification;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.NotificationView;

@SuppressWarnings("serial")
public class NotificationServiceImpl extends RemoteServiceServlet implements NotificationService {
	
	@Override
	public void saveNotification(SimpleNotification notification) {
		Notification n = new Notification();
		n.setCreationDate(new Date());
		n.setMessage(notification.getMessage());
		n.setTriggerDate(formatDate(notification.getDate()));
		n.setMinutes(notification.getMinutes());
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		try{
			pm.makePersistent(n);
			System.out.println("Id:" + n.getKey());
			for(NotificationViews v : notification.getViews()){
				NotificationView view = new NotificationView();
				view.setNotificationKey(n.getKey().getId());
				view.setViewType(v.getCode());
				pm.makePersistent(view);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
	}
	
	private Integer formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		return Integer.parseInt(str);
	}

}
