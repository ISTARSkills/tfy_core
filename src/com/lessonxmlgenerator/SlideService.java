package com.lessonxmlgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSList;
import com.viksitpro.core.cms.oldcontent.CMSParagraph;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.cms.oldcontent.CMSTextItem;




public class SlideService {

	public CMSLesson getSlideHTML(int lessonID) {

		StringBuffer stringBuffer = new StringBuffer();

		String path = "";
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}
			path = properties.getProperty("mediaLessonPath1");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		path += "" + lessonID + ".xml";

		File file = new File(path);

		//System.err.println(path);

		try {
			JAXBContext jaxbcontext = JAXBContext.newInstance(CMSLesson.class);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			CMSLesson cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);
			if (cmsLesson != null && cmsLesson.getSlides() != null) {
				for (CMSSlide cmsSlide : cmsLesson.getSlides()) {
					String templateVMFileName = cmsSlide.getTemplateName();
					//System.err.println(templateVMFileName + " " + cmsSlide.getId());
					if (templateVMFileName.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH")) {

						CMSParagraph cmsphara = new CMSParagraph();

						if (!cmsSlide.getParagraph().getText().contains("<table")) {

							cmsSlide.setTemplateName("ONLY_TITLE_LIST");
							CMSList list = new CMSList();
							list.setList_type("SIMPLE_LIST");
							ArrayList<CMSTextItem> textitemArray = new ArrayList();
							String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");

							for (String w : words) {

								String[] result1 = w.split("\\<li.*?>");
								for (String w1 : result1) {

									CMSTextItem textitem = new CMSTextItem();
									textitem.setText(
											w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
													.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
									textitemArray.add(textitem);
								}

							}
							list.setItems(textitemArray);
							cmsSlide.setList(list);

							cmsphara.setText("");
							cmsSlide.setParagraph(cmsphara);

						} else {
							//System.err.println("has table");
						}

					}

					if (templateVMFileName.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH_IMAGE")) {

						CMSParagraph cmsphara = new CMSParagraph();

						if (!cmsSlide.getParagraph().getText().contains("<table")) {

							cmsSlide.setTemplateName("ONLY_TITLE_PARAGRAPH_IMAGE_LIST");
							CMSList list = new CMSList();
							list.setList_type("SIMPLE_LIST");
							ArrayList<CMSTextItem> textitemArray = new ArrayList();
							String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
							for (String w : words) {

								String[] result1 = w.split("\\<li.*?>");
								for (String w1 : result1) {

									CMSTextItem textitem = new CMSTextItem();
									textitem.setText(
											w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
													.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
									textitemArray.add(textitem);
								}

							}
							list.setItems(textitemArray);
							cmsSlide.setList(list);

							cmsphara.setText("");
							cmsSlide.setParagraph(cmsphara);

						} else {
							//System.err.println("has table");
						}

					}

					if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH")) {

						CMSParagraph cmsphara = new CMSParagraph();

						if (!cmsSlide.getParagraph().getText().contains("<table")) {

							cmsSlide.setTemplateName("ONLY_LIST");
							CMSList list = new CMSList();
							list.setList_type("SIMPLE_LIST");
							ArrayList<CMSTextItem> textitemArray = new ArrayList();
							String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
							for (String w : words) {

								String[] result1 = w.split("\\<li.*?>");
								for (String w1 : result1) {

									CMSTextItem textitem = new CMSTextItem();
									textitem.setText(
											w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
													.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
									textitemArray.add(textitem);
								}

							}
							list.setItems(textitemArray);
							cmsSlide.setList(list);

							cmsphara.setText("");
							cmsSlide.setParagraph(cmsphara);

						} else {
							//System.err.println("has table");
						}

					}

					if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH_IMAGE")) {

						CMSParagraph cmsphara = new CMSParagraph();

						if (!cmsSlide.getParagraph().getText().contains("<table")) {

							cmsSlide.setTemplateName("ONLY_PARAGRAPH_IMAGE_LIST");
							CMSList list = new CMSList();
							list.setList_type("SIMPLE_LIST");
							ArrayList<CMSTextItem> textitemArray = new ArrayList();
							String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
							for (String w : words) {

								String[] result1 = w.split("\\<li.*?>");
								for (String w1 : result1) {

									CMSTextItem textitem = new CMSTextItem();
									textitem.setText(
											w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
													.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
									textitemArray.add(textitem);
								}

							}
							list.setItems(textitemArray);
							cmsSlide.setList(list);

							cmsphara.setText("");
							cmsSlide.setParagraph(cmsphara);

						} else {
							//System.err.println("has table");
						}

					}

					if (templateVMFileName.equalsIgnoreCase("ONLY_PARAGRAPH_TITLE")) {

						CMSParagraph cmsphara = new CMSParagraph();

						if (!cmsSlide.getParagraph().getText().contains("<table")) {

							cmsSlide.setTemplateName("ONLY_PARAGRAPH_TITLE_LIST");
							CMSList list = new CMSList();
							list.setList_type("SIMPLE_LIST");
							ArrayList<CMSTextItem> textitemArray = new ArrayList();
							String[] words = cmsSlide.getParagraph().getText().split("\\<p.*?>");
							for (String w : words) {

								String[] result1 = w.split("\\<li.*?>");
								for (String w1 : result1) {

									CMSTextItem textitem = new CMSTextItem();
									textitem.setText(
											w1.replaceAll("</p>", "").replaceAll("&nbsp;", " ").replaceAll("</ul>", " ")
													.replaceAll("<ul>", " ").replaceAll("</li>", " ").trim());
									textitemArray.add(textitem);
								}

							}
							list.setItems(textitemArray);
							cmsSlide.setList(list);

							cmsphara.setText("");
							cmsSlide.setParagraph(cmsphara);

						} else {
							//System.err.println("has table");
						}

					}

				}
				return cmsLesson;
			}
			

		} catch (JAXBException e) {

			e.printStackTrace();
		}
		return null;

	}

	public CMSLesson massageCMSLIDE(CMSLesson cmsLesson) {

		ArrayList<CMSSlide> cmsSlides = new ArrayList<CMSSlide>();
		if (cmsLesson != null && cmsLesson.getSlides() != null) {
		for (CMSSlide cmsSlide : cmsLesson.getSlides()) {

			switch (cmsSlide.getTemplateName()) {
			case "ONLY_TITLE":
				cmsSlide.setFragmentCount(0);
				break;
			case "NO_CONTENT":
				cmsSlide.setFragmentCount(0);
				break;
			case "ONLY_TITLE_IMAGE":
				cmsSlide.setFragmentCount(1);
				break;
			case "ONLY_IMAGE":
				cmsSlide.setFragmentCount(0);
				break;
			case "ONLY_VIDEO":
				cmsSlide.setFragmentCount(0);
				break;
			case "ONLY_2TITLE":
				cmsSlide.setFragmentCount(1);
				break;
			case "ONLY_2TITLE_IMAGE":
				cmsSlide.setFragmentCount(2);
				break;

			case "ONLY_LIST":
				ArrayList<String> arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
					for(CMSTextItem retval : cmsSlide.getList().getItems()){
						if(!retval.getText().isEmpty()){
							arrayList.add(retval.getText());
						}
						
					}
				cmsSlide.setFragmentCount(arrayList.size()-1);
				}
				break;
			case "ONLY_TITLE_LIST":
				 arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
					for(CMSTextItem retval : cmsSlide.getList().getItems()){
						if(!retval.getText().isEmpty()){
							arrayList.add(retval.getText());
						}
						
					}
				cmsSlide.setFragmentCount(arrayList.size());
				}
				break;
			case "ONLY_TITLE_LIST_NUMBERED":
				 arrayList = new ArrayList<>();
					if(cmsSlide.getList().getItems() != null){
						for(CMSTextItem retval : cmsSlide.getList().getItems()){
							if(!retval.getText().isEmpty()){
								arrayList.add(retval.getText());
							}
							
						}
					cmsSlide.setFragmentCount(arrayList.size());
					}
				break;
			case "ONLY_LIST_NUMBERED":
				 arrayList = new ArrayList<>();
					if(cmsSlide.getList().getItems() != null){
						for(CMSTextItem retval : cmsSlide.getList().getItems()){
							if(!retval.getText().isEmpty()){
								arrayList.add(retval.getText());
							}
							
						}
					cmsSlide.setFragmentCount(arrayList.size()-1);
					}
				break;

			case "ONLY_PARAGRAPH":

				 arrayList = new ArrayList<>();
				if (cmsSlide.getParagraph().getText().contains("<table")) {

					if (cmsSlide.getParagraph().getText().contains("<p")) {

						for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
							arrayList.add(retval);
						}

					} else {
						arrayList.add(cmsSlide.getParagraph().getText());
					}

				} else {
					for (CMSTextItem ss : cmsSlide.getList().getItems()) {
						if (!ss.getText().isEmpty()) {

							arrayList.add(ss.getText());
						}

					}
				}

				cmsSlide.setFragmentCount(arrayList.size() - 1);
				break;
			case "ONLY_PARAGRAPH_LIST":

				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size() - 1);
				}
				break;

			case "ONLY_PARAGRAPH_IMAGE":

				arrayList = new ArrayList<>();
				if (cmsSlide.getParagraph().getText().contains("<table")) {

					if (cmsSlide.getParagraph().getText().contains("<p")) {

						for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
							arrayList.add(retval);
						}

					} else {
						arrayList.add(cmsSlide.getParagraph().getText());
					}

				} else {
					for (CMSTextItem ss : cmsSlide.getList().getItems()) {
						if (!ss.getText().isEmpty()) {

							arrayList.add(ss.getText());
						}

					}
				}
				cmsSlide.setFragmentCount(arrayList.size());

				break;

			case "ONLY_PARAGRAPH_IMAGE_LIST":
				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size());
				}
				break;
			case "ONLY_TITLE_PARAGRAPH_LIST":
				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size());
				}
				break;

			case "ONLY_TITLE_PARAGRAPH":
				arrayList = new ArrayList<>();

				if (cmsSlide.getParagraph().getText().contains("<table")) {

					if (cmsSlide.getParagraph().getText().contains("<p")) {

						for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
							arrayList.add(retval);
						}

					} else {
						arrayList.add(cmsSlide.getParagraph().getText());
					}

				} else {
					for (CMSTextItem ss : cmsSlide.getList().getItems()) {
						if (!ss.getText().isEmpty()) {

							arrayList.add(ss.getText());
						}

					}
				}

				cmsSlide.setFragmentCount(arrayList.size());
				break;

			case "ONLY_PARAGRAPH_TITLE_LIST":
				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size());
				}
				break;

			case "ONLY_PARAGRAPH_TITLE":
				arrayList = new ArrayList<>();
				if (cmsSlide.getParagraph().getText().contains("<table")) {

					if (cmsSlide.getParagraph().getText().contains("<p")) {

						for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
							arrayList.add(retval);
						}

					} else {
						arrayList.add(cmsSlide.getParagraph().getText());
					}

				} else {
					for (CMSTextItem ss : cmsSlide.getList().getItems()) {
						if (!ss.getText().isEmpty()) {

							arrayList.add(ss.getText());
						}

					}
				}
				cmsSlide.setFragmentCount(arrayList.size());
				break;
			case "ONLY_TITLE_PARAGRAPH_IMAGE":

				arrayList = new ArrayList<>();
				
				if (cmsSlide.getParagraph().getText().contains("<table")) {

					if (cmsSlide.getParagraph().getText().contains("<p")) {

						for (String retval : cmsSlide.getParagraph().getText().split("<p")) {
							arrayList.add(retval);
						}

					} else {
						arrayList.add(cmsSlide.getParagraph().getText());
					}

				} else {
					for (CMSTextItem ss : cmsSlide.getList().getItems()) {
						if (!ss.getText().isEmpty()) {

							arrayList.add(ss.getText());
						}

					}
				}
				cmsSlide.setFragmentCount(arrayList.size() + 1);

				break;

			case "ONLY_TITLE_PARAGRAPH_IMAGE_LIST":

				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size() + 1);
				}
				break;

			case "ONLY_TITLE_TREE":

				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){

				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());

						for (CMSTextItem sl : ss.getList().getItems()) {

							if (!sl.getText().isEmpty()) {
							arrayList.add(sl.getText());
							}
						}

					}

				}

				cmsSlide.setFragmentCount(arrayList.size());
			}
				break;

			default:
				break;

			case "ONLY_2BOX":

				arrayList = new ArrayList<>();
				if(cmsSlide.getList().getItems() != null){
				for (CMSTextItem ss : cmsSlide.getList().getItems()) {
					if (!ss.getText().isEmpty()) {

						arrayList.add(ss.getText());
					}

				}

				cmsSlide.setFragmentCount(arrayList.size() + 1);
				}
				break;
			}
			cmsSlides.add(cmsSlide);

		}
		cmsLesson.setSlides(cmsSlides);
		}
		
		return cmsLesson;
	}

	public void writeLessonXml(int lesson_id, CMSLesson cmsLesson) {

		String path = "";
		try {
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}
			path = properties.getProperty("mediaLessonPath");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		path += "" + lesson_id + ".xml";

		File file = new File(path);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(CMSLesson.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			if (cmsLesson != null && cmsLesson.getSlides() != null) {
			jaxbMarshaller.marshal(cmsLesson, file);
			//jaxbMarshaller.marshal(cmsLesson, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
