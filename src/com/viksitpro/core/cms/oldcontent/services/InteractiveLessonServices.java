/**
 * 
 */
package com.viksitpro.core.cms.oldcontent.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.viksitpro.core.cms.interactive.EntityOption;
import com.viksitpro.core.cms.oldcontent.CMSLesson;
import com.viksitpro.core.cms.oldcontent.CMSSlide;
import com.viksitpro.core.utilities.AppProperies;

public class InteractiveLessonServices {

	public CMSLesson getCmsInteractiveLesson(int lessonID) {
		CMSLesson cmsLesson = null;
		try {
			File file = getLessonPath(lessonID);
			JAXBContext jaxbcontext = JAXBContext.newInstance(CMSLesson.class);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			cmsLesson = (CMSLesson) unmarshaller.unmarshal(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return cmsLesson;
	}

	public void updateCMSLesson(CMSLesson cmsLesson, int lessonID) {
		try {
			File file = getLessonPath(lessonID);
			JAXBContext jaxbContext = JAXBContext.newInstance(CMSLesson.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(cmsLesson, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File getLessonPath(int lesson_id) throws IOException {
		String path = "";
		path = AppProperies.getProperty("mediaLessonPath");
		path += "" + lesson_id + "/" + lesson_id + "/" + lesson_id + ".xml";
		File file = new File(path);
		return file;
	}

	public void createUpdateInteractiveSlide(CMSLesson cmsLesson, CMSSlide cmsSlide, int lessonId,
			HttpServletRequest request) {
		boolean isCreate = false;

		if (null == cmsSlide) {
			cmsSlide = new CMSSlide();
			cmsSlide.setId(Math.abs((int) System.nanoTime()));
			cmsSlide.setOrder_id(cmsLesson.getSlides() != null ? cmsLesson.getSlides().size() + 1 : 1);
			isCreate = true;
		}

		String slide_template = request.getParameter("slide_template");
		int coins_slide = Integer.parseInt((!request.getParameter("coins_slide").equalsIgnoreCase(""))
				? request.getParameter("coins_slide") : "0");
		int points_slide = Integer.parseInt((!request.getParameter("points_slide").equalsIgnoreCase(""))
				? request.getParameter("points_slide") : "0");
		cmsSlide.setCoins(coins_slide);
		cmsSlide.setPoints(points_slide);
		cmsSlide.setAudio_type(request.getParameter("slide_audio_type"));
		cmsSlide.setTemplate(slide_template);

		String bg_image = "";
		switch (slide_template) {

		case "D-T":

			String drop_zone = request.getParameter("drop_zone");
			if (drop_zone.equalsIgnoreCase("TOP")) {
				bg_image = request.getParameter("slide_bg_image_top");

			} else {
				bg_image = request.getParameter("slide_bg_image_bottom");

			}
			cmsSlide.setSub_type(drop_zone);

			EntityOptions entityOptions1 = new EntityOptions();
			ArrayList<EntityOption> entityOptionsList1 = new ArrayList<>();
			for (int i = 1; i <= 6; i++) {
				EntityOption entityOption = null;
				if (isCreate) {
					entityOption = new EntityOption();
					entityOption.setId(i);
				} else {
					if (cmsSlide.getEntityOptions() != null
							&& i < cmsSlide.getEntityOptions().getEntityOptions().size()) {
						entityOption = cmsSlide.getEntityOptions().getEntityOptions().get(i);
					} else {
						entityOption = new EntityOption();
						entityOption.setId(i);
					}
				}

				String bg_audio = request.getParameter("option_audio1");
				entityOption.setBg_audio(bg_audio);

				String opt_bg_image = request
						.getParameter("bg_image_opt" + (drop_zone.equalsIgnoreCase("TOP") ? "0" + i : i));
				entityOption.setImage_BG(opt_bg_image);
				entityOption.setOption_text(
						request.getParameter("bg_text_opt" + (drop_zone.equalsIgnoreCase("TOP") ? "0" + i : i)));
				entityOption.setNext_slide(Integer.parseInt(request.getParameter("option_" + i + "_nav")));

				entityOptionsList1.add(entityOption);
			}
			entityOptions1.setEntityOptions(entityOptionsList1);
			cmsSlide.setEntityOptions(entityOptions1);

			String action_type = request.getParameter("action_type");
			cmsSlide.setAction(action_type);

			String correct_option = request.getParameter("correct_option");
			cmsSlide.setMarking_scheme(correct_option);

			String audio_BG = request.getParameter("bg_audio");
			cmsSlide.setAudio_BG(audio_BG);
			break;

		case "MATCH":
			String[] match_questions = request.getParameter("match_questions").split(",");
			String[] match_options = request.getParameter("match_options").split(",");
			EntityOptions entityOptions2 = new EntityOptions();
			ArrayList<EntityOption> entityOptionsList2 = new ArrayList<>();

			if (match_questions.length != 0) {
				for (String key : match_questions) {

					if (!key.equalsIgnoreCase("")) {
						int optionId = Integer.parseInt(key);
						EntityOption entityOption =new EntityOption();
						entityOption.setId(optionId);
						entityOption.setOption_text(request.getParameter("text-m-ques" + optionId));
						entityOption.setImage_BG(request.getParameter("bg_image-m-ques" + optionId));
						entityOption.setType("draggable");
						entityOptionsList2.add(entityOption);
					}
				}
			}

			if (match_options.length != 0) {
				for (String key : match_options) {
					if (!key.equalsIgnoreCase("")) {
						int optionId = Integer.parseInt(key);
						EntityOption entityOption = new EntityOption();
						entityOption.setId(optionId);
						entityOption.setOption_text(request.getParameter("text-m-option" + optionId));
						entityOption.setImage_BG(request.getParameter("bg_image-m-option" + optionId));
						entityOption.setType("droppable");
						entityOption.setCorrect_option(request.getParameter("option-correct-m-" + optionId));
						entityOptionsList2.add(entityOption);
					}
				}
			}

			entityOptions2.setEntityOptions(entityOptionsList2);
			cmsSlide.setEntityOptions(entityOptions2);
			break;

		case "ORDERING":
			String[] orderings = request.getParameter("order_options").split(",");
			
			EntityOptions entityOptions3 = new EntityOptions();
			ArrayList<EntityOption> entityOptionsList3 = new ArrayList<>();
			
			if (orderings.length != 0) {
				for (String key : orderings) {
					if (!key.equalsIgnoreCase("")) {
						int optionId = Integer.parseInt(key);
						EntityOption entityOption = new EntityOption();
						entityOption.setId(optionId);
						entityOption.setOption_text(request.getParameter("option-order-text-" + optionId));
						entityOption.setCorrect_order_id(Integer.parseInt(request.getParameter("order-based-correct-" + optionId)));
						entityOption.setImage_BG(request.getParameter("option-order-bg-" + optionId));
						
						entityOptionsList3.add(entityOption);
					}
				}
			}
			entityOptions3.setEntityOptions(entityOptionsList3);
			cmsSlide.setEntityOptions(entityOptions3);
			break;
		}

		cmsSlide.setImage_BG(bg_image);

		if (isCreate) {
			if (cmsLesson.getSlides() != null && cmsLesson.getSlides().size() != 0) {
				cmsLesson.getSlides().add(cmsSlide);
			} else {
				ArrayList<CMSSlide> cmsSlides = new ArrayList<>();
				cmsSlides.add(cmsSlide);
				cmsLesson.setSlides(cmsSlides);
			}
		} else {
			ArrayList<CMSSlide> cmsSlides = new ArrayList<>();
			for (CMSSlide slide : cmsLesson.getSlides()) {
				if (slide.getId() == cmsSlide.getId()) {
					cmsSlides.add(cmsSlide);
				} else {
					cmsSlides.add(slide);
				}
			}
			cmsLesson.setSlides(cmsSlides);
		}

		updateCMSLesson(cmsLesson, lessonId);
	}

}