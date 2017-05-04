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
import org.hibernate.Transaction;

import com.viksitpro.core.dao.entities.BaseHibernateDAO;
import com.viksitpro.core.dao.entities.Campaign;
import com.viksitpro.core.dao.entities.Cmsession;
import com.viksitpro.core.dao.entities.Context;
import com.viksitpro.core.dao.entities.ContextDAO;
import com.viksitpro.core.dao.entities.IstarUser;
import com.viksitpro.core.dao.entities.IstarUserDAO;
import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.dao.entities.ModuleDAO;
import com.viksitpro.core.dao.utils.HibernateSessionFactory;
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
		/*Module m = (new ModuleDAO().findById(25));
		for (Cmsession cms : m.getCmsessions()) {
			System.out.println(cms.getId());
		}*/
		//TestingContextAdd();
		TestingContextFind();
	}

	private static void TestingContextFind() {
		// TODO Auto-generated method stub
		Context context = (new ContextDAO()).findById(1);
		System.err.println(context.getTitle());
	}

	private static void TestingContextAdd() {
		// TODO Auto-generated method stub
		Context context = new Context();
		ContextDAO contextDAO = new ContextDAO();
		context.setTitle("Champoa");
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			// some action
			contextDAO.attachDirty(context);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			session.flush();
		}
		System.err.println(context.getId());
	}

}
