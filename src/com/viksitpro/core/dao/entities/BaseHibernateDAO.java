package com.viksitpro.core.dao.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.viksitpro.core.dao.utils.HibernateSessionFactory;



/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	public static List<?> GetOrderedListFromSet (Collection<?> setObject, String property)
	{
		if(setObject!=null && setObject.size()>0)
		{
			List<?> list = setObject.stream().collect(Collectors.toList());		
			Collections.sort(list, new Comparator<Object>() {
			    public int compare(Object o1, Object o2) {
			        try {
			            Method m = o1.getClass().getMethod("get" + StringUtils.capitalize(property));
			            // Assume String type. If different, you must handle each type
			            String s1 =  m.invoke(o1).toString();
			            String s2 =  m.invoke(o2).toString();
			            return s1.compareTo(s2);
			        // simply re-throw checked exceptions wrapped in an unchecked exception
			        } catch (SecurityException e) {
			            throw new RuntimeException(e); 
			        } catch (NoSuchMethodException e) {
			            throw new RuntimeException(e);
			        } catch (IllegalAccessException e) {
			            throw new RuntimeException(e);
			        } catch (InvocationTargetException e) {
			            throw new RuntimeException(e);
			        }
			    }
			});		
			return list;
	
		}
		else
		{
			return null;
		}	
			}
	
}