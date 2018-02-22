/**
 * 
 */
package com.viksitpro.core.logger;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author absin
 *
 */
public class UserJourney {
	public void createUserJourneyEntry(ServletRequest request, long timeTaken, HttpSession session) {
		Integer userID = null;
		String sessionId = null;
		String contextPath = ((HttpServletRequest) request).getContextPath();
		String requestURL = ((HttpServletRequest) request).getRequestURL().toString();
		try {
			userID = ((IstarUser) session.getAttribute("user")).getId();
		} catch (Exception e) {
			// e.printStackTrace();
			ViksitLogger.logMSG(this.getClass().getName(),
					"No user exists for request to url: " + requestURL + " for context: " + contextPath);
		}
		try {
			sessionId = session.getId();
		} catch (Exception e) {
			//e.printStackTrace();
			ViksitLogger.logMSG(this.getClass().getName(),
					"No session exists for request to url: " + requestURL + " for context: " + contextPath);
		}
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName objectNameOrgAdmin = null;
		ObjectName objectNameContent = null;
		ObjectName objectNamet2c = null;
		try {
			objectNameOrgAdmin = new ObjectName("Catalina:type=Manager,context=/,host=localhost");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		try {
			objectNameContent = new ObjectName("Catalina:type=Manager,context=/content,host=localhost");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		try {
			objectNamet2c = new ObjectName("Catalina:type=Manager,context=/t2c,host=localhost");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		Integer activeSessionsOrgAdmin = null;
		Integer activeSessionsContent = null;
		Integer activeSessionst2c = null;
		try {
			activeSessionsOrgAdmin = (Integer) mBeanServer.getAttribute(objectNameOrgAdmin, "activeSessions");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		}
		try {
			activeSessionsContent = (Integer) mBeanServer.getAttribute(objectNameContent, "activeSessions");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		}
		try {
			activeSessionst2c = (Integer) mBeanServer.getAttribute(objectNamet2c, "activeSessions");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		}
		Integer activeSessions = 0;
		activeSessions += activeSessionsOrgAdmin;
		activeSessions += activeSessionsContent;
		// activeSessions += activeSessionst2c;
		String sql = "INSERT INTO public.user_journey_logs ( url, sessionid, created_at, userid, page_render_time, source, active_sessions ) VALUES ('"
				+ requestURL + "', '" + sessionId + "', '" + new Timestamp(System.currentTimeMillis()).toString()
				+ "', " + userID + ", " + (timeTaken) + ", '" + contextPath + "', " + activeSessions + " );";
		Runnable insertUserJourney = () -> {
			try {
				String name = Thread.currentThread().getName();
				ViksitLogger.logMSG(this.getClass().getName(),
						"Inititating insertion of User Journey Log on thread: " + name + " with sql query: \n" + sql);
				TimeUnit.SECONDS.sleep(1);
				new DBUTILS().executeUpdate(sql);
				ViksitLogger.logMSG(this.getClass().getName(),
						"Insertion of User Journey Log done. The thread named " + name + " is freed!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread thread = new Thread(insertUserJourney);
		thread.start();
	}
}
