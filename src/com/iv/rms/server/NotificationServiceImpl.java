package com.iv.rms.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iv.rms.client.NotificationService;
import com.iv.rms.client.NotificationViews;
import com.iv.rms.client.SimpleNotification;
import com.iv.rms.entity.Notification;
import com.iv.rms.entity.NotificationView;
import com.iv.rms.mail.MailService;
import com.iv.rms.shared.Util;

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
	
	public void processPendingNotification(){
		PersistenceManager pm = null;
		try{
			Date currentDate = new Date();
			Integer dateInt = Util.formatDate(currentDate);
			Integer minutes = Util.getMinutesSinceMidnight(currentDate);
			pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Notification.class);
		    q.setFilter("triggerDate == " + dateInt);
		    q.setFilter("minutes <= " + minutes);
		    q.setFilter("procesed == " + Boolean.FALSE);
		    List<Notification> results = (List<Notification>) q.execute();
		    MailService ms = new MailService(); 
		    if (!results.isEmpty()) {
	            for (Notification n : results) {
	                ms.sendMail(n);
	                n.setProcesed(Boolean.TRUE);
	            }
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
	}

}
