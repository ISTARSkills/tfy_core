/**
 * 
 */
package istarcore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.Campaign;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.IstarUserDAO;
import com.viksitpro.core.dao.utils.user.IstarUserServices;

/**
 * @author verma6uc
 *
 */
public class MAIN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IstarUserServices istarUserServices = new IstarUserServices();
		IstarUser recruiter = istarUserServices.getIstarUser(1);

		System.err.println(recruiter.getEmail());
	}

}
