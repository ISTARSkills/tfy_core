/**
 * 
 */
package istarcore;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.viksitpro.core.customtask.TaskFormElement;
import com.viksitpro.core.customtask.TaskLibrary;
import com.viksitpro.core.customtask.TaskStep;
import com.viksitpro.core.customtask.TaskTemplate;

/**
 * @author istar
 *
 */
public class TaskTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskTemplate t = new TaskTemplate(100, "MID_SESSION_STUDENT_FEEDBACK");
		t.setLabel("Mid Session Student Feedback");
		t.setDescription("A class has been scheduled for the course $$PROGRAM in $$ORGANIZATION_NAME at $$EVENT_DATE");
		TaskLibrary tl = new TaskLibrary();
		tl.getTemplates().put(100, t);
		t = massage(t);
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(TaskLibrary.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(tl, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }

	}

	private static TaskTemplate massage(TaskTemplate t) {
		TaskStep e = new TaskStep();
		e.setLabel("First Steps");
		e.setFetchQuery(null);
		e.setUpdateQuery("update to something where something is something");
		List<TaskFormElement> form_elements = new ArrayList<>();
		TaskFormElement e1 =  new TaskFormElement("STAR_RATING", "Coverage", "coverage", "number");
		TaskFormElement e2 =  new TaskFormElement("STAR_RATING", "Ease of Understanding", "ease_of_understanding", "number");
		TaskFormElement e3 =  new TaskFormElement("STAR_RATING", "Quality Of Examples Provided", "quality_of_examples_provided", "number");
		TaskFormElement e4 =  new TaskFormElement("SWITCH", "Would you be interested in learning more subjects related to the sector?", "quality_of_examples_provided22", "boolean");
		TaskFormElement e5 =  new TaskFormElement("TEXT", "What aspects of the course would you change/remove?", "quality_of_examples_provided22", "text_area");
		form_elements.add(e1);
		form_elements.add(e2);
		form_elements.add(e3);
		form_elements.add(e4);
		form_elements.add(e5);
		e.setForm_elements(form_elements);
		t.getSteps().add(e);
		t.getSteps().add(e);t.getSteps().add(e);t.getSteps().add(e);t.getSteps().add(e);t.getSteps().add(e);
		return t;
	}

}
