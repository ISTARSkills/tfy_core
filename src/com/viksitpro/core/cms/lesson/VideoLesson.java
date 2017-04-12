package com.viksitpro.core.cms.lesson;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "video_lesson")
public class VideoLesson implements Serializable{
	Integer id;
	String video_url;
	String video_thumb_url;
	
	public VideoLesson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@XmlElement(name = "id", required = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement(name = "video_url", required = false)
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	@XmlElement(name = "video_thumb_url", required = false)
	public String getVideo_thumb_url() {
		return video_thumb_url;
	}
	public void setVideo_thumb_url(String video_thumb_url) {
		this.video_thumb_url = video_thumb_url;
	}
	
}
