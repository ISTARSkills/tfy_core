/**
 * 
 */
package com.viksitpro.core.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ComplexObject
 *
 */
public class CollectionCustomUtils {

	
	public boolean collectionContainsObjectWithParameter(Collection<?> setObject, String parameter, String value)
	{
		if(setObject!=null && setObject.size()>0)
		{
			List<?> list = setObject.stream().collect(Collectors.toList());
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				Method m;
				try {
					m = object.getClass().getMethod("get" + StringUtils.capitalize(parameter));
					if(m.invoke(object).toString().equalsIgnoreCase(value))
					{
						return true;
					}
					else
					{
						return false;
					}	
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return false;
		}					
		else
		{
			return false;
		}
		
	}
}
