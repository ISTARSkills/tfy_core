/**
 *
 */
package com.viksitpro.core.cms.interactive;		
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 */
@XmlRootElement(name="card_content")
public class CardContent implements Serializable {

	String aBackgroundColor;
	String aBackgroundImage;
	String aForegroundImage;
	String aTitle;
	String aDescription;
	
	String bBackgroundColor;
	String bBackgroundImage;
	String bTopleftBGImage;
	String bTopRightBGImage;
	String bBottomLeftBGImage;
	String bBottomRightBGImage;
	String bTopleftFGImage;
	String bTopRightFGImage;
	String bBottomLeftFGImage;
	String bBottomRightFGImage;
	String bCenterTopBackgroundImage;
	String bCenterTopForegroundImage;
	String bCenterBottomBackgroundImage;
	String bCenterBottomForegroundImage;
	
	String bTopLeftMediaUrl;
	String bTopRightMediaUrl;
	String bBottomLeftMediaUrl;
	String bBottomRightMediaUrl;
	String bCenterTopMediaUrl;
	String bCenterBottomMediaUrl;
	
	String bTopLeftMediaType;
	String bTopRightMediaType;
	String bBottomLeftMediaType;
	String bBottomRightMediaType;
	String bCenterTopMediaType;
	String bCenterBottomMediaType;

	public CardContent() {
		super();

	}

	public String getaBackgroundColor() {
		return aBackgroundColor;
	}

	@XmlAttribute(name = "aBackgroundColor", required = false)
	public void setaBackgroundColor(String aBackgroundColor) {
		this.aBackgroundColor = aBackgroundColor;
	}

	public String getaBackgroundImage() {
		return aBackgroundImage;
	}

	@XmlAttribute(name = "aBackgroundImage", required = false)
	public void setaBackgroundImage(String aBackgroundImage) {
		this.aBackgroundImage = aBackgroundImage;
	}

	public String getaForegroundImage() {
		return aForegroundImage;
	}

	@XmlAttribute(name = "aForegroundImage", required = false)
	public void setaForegroundImage(String aForegroundImage) {
		this.aForegroundImage = aForegroundImage;
	}

	public String getaTitle() {
		return aTitle;
	}

	@XmlAttribute(name = "aTitle", required = false)
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}

	public String getaDescription() {
		return aDescription;
	}

	@XmlAttribute(name = "aDescription", required = false)
	public void setaDescription(String aDescription) {
		this.aDescription = aDescription;
	}

	public String getbTopleftBGImage() {
		return bTopleftBGImage;
	}

	@XmlAttribute(name = "bTopleftBGImage", required = false)
	public void setbTopleftBGImage(String bTopleftBGImage) {
		this.bTopleftBGImage = bTopleftBGImage;
	}

	public String getbTopRightBGImage() {
		return bTopRightBGImage;
	}

	@XmlAttribute(name = "bTopRightBGImage", required = false)
	public void setbTopRightBGImage(String bTopRightBGImage) {
		this.bTopRightBGImage = bTopRightBGImage;
	}

	public String getbBottomLeftBGImage() {
		return bBottomLeftBGImage;
	}

	@XmlAttribute(name = "bBottomLeftBGImage", required = false)
	public void setbBottomLeftBGImage(String bBottomLeftBGImage) {
		this.bBottomLeftBGImage = bBottomLeftBGImage;
	}

	public String getbBottomRightBGImage() {
		return bBottomRightBGImage;
	}

	@XmlAttribute(name = "bBottomRightBGImage", required = false)
	public void setbBottomRightBGImage(String bBottomRightBGImage) {
		this.bBottomRightBGImage = bBottomRightBGImage;
	}

	public String getbTopleftFGImage() {
		return bTopleftFGImage;
	}

	@XmlAttribute(name = "bTopleftFGImage", required = false)
	public void setbTopleftFGImage(String bTopleftFGImage) {
		this.bTopleftFGImage = bTopleftFGImage;
	}

	public String getbTopRightFGImage() {
		return bTopRightFGImage;
	}

	@XmlAttribute(name = "bTopRightFGImage", required = false)

	public void setbTopRightFGImage(String bTopRightFGImage) {
		this.bTopRightFGImage = bTopRightFGImage;
	}

	public String getbBottomLeftFGImage() {
		return bBottomLeftFGImage;
	}

	@XmlAttribute(name = "bBottomLeftFGImage", required = false)

	public void setbBottomLeftFGImage(String bBottomLeftFGImage) {
		this.bBottomLeftFGImage = bBottomLeftFGImage;
	}

	public String getbBottomRightFGImage() {
		return bBottomRightFGImage;
	}

	@XmlAttribute(name = "bBottomRightFGImage", required = false)
	public void setbBottomRightFGImage(String bBottomRightFGImage) {
		this.bBottomRightFGImage = bBottomRightFGImage;
	}

	public String getbCenterTopBackgroundImage() {
		return bCenterTopBackgroundImage;
	}

	@XmlAttribute(name = "bCenterTopBackgroundImage", required = false)
	public void setbCenterTopBackgroundImage(String bCenterTopBackgroundImage) {
		this.bCenterTopBackgroundImage = bCenterTopBackgroundImage;
	}


	public String getbCenterTopForegroundImage() {
		return bCenterTopForegroundImage;
	}
	
	@XmlAttribute(name = "bCenterTopForegrounImage", required = false)
	public void setbCenterTopForegroundImage(String bCenterTopForegroundImage) {
		this.bCenterTopForegroundImage = bCenterTopForegroundImage;
	}

	public String getbCenterBottomBackgroundImage() {
		return bCenterBottomBackgroundImage;
	}

	@XmlAttribute(name = "bCenterBottomBackgroundImage", required = false)
	public void setbCenterBottomBackgroundImage(String bCenterBottomBackgroundImage) {
		this.bCenterBottomBackgroundImage = bCenterBottomBackgroundImage;
	}

	public String getbCenterBottomForegroundImage() {
		return bCenterBottomForegroundImage;
	}

	@XmlAttribute(name = "bCenterBottomForegroundImage", required = false)
	public void setbCenterBottomForegroundImage(String bCenterBottomForegroundImage) {
		this.bCenterBottomForegroundImage = bCenterBottomForegroundImage;
	}

	public String getbTopLeftMediaUrl() {
		return bTopLeftMediaUrl;
	}

	@XmlAttribute(name = "bTopLeftMediaUrl", required = false)
	public void setbTopLeftMediaUrl(String bTopLeftMediaUrl) {
		this.bTopLeftMediaUrl = bTopLeftMediaUrl;
	}

	public String getbTopRightMediaUrl() {
		return bTopRightMediaUrl;
	}

	@XmlAttribute(name = "bTopRightMediaUrl", required = false)
	public void setbTopRightMediaUrl(String bTopRightMediaUrl) {
		this.bTopRightMediaUrl = bTopRightMediaUrl;
	}

	public String getbBottomLeftMediaUrl() {
		return bBottomLeftMediaUrl;
	}

	@XmlAttribute(name = "bBottomLeftMediaUrl", required = false)
	public void setbBottomLeftMediaUrl(String bBottomLeftMediaUrl) {
		this.bBottomLeftMediaUrl = bBottomLeftMediaUrl;
	}

	public String getbBottomRightMediaUrl() {
		return bBottomRightMediaUrl;
	}

	@XmlAttribute(name = "bBottomRightMediaUrl", required = false)
	public void setbBottomRightMediaUrl(String bBottomRightMediaUrl) {
		this.bBottomRightMediaUrl = bBottomRightMediaUrl;
	}

	public String getbCenterTopMediaUrl() {
		return bCenterTopMediaUrl;
	}

	@XmlAttribute(name = "bCenterTopMediaUrl", required = false)
	public void setbCenterTopMediaUrl(String bCenterTopMediaUrl) {
		this.bCenterTopMediaUrl = bCenterTopMediaUrl;
	}

	public String getbCenterBottomMediaUrl() {
		return bCenterBottomMediaUrl;
	}

	@XmlAttribute(name = "bCenterBottomMediaUrl", required = false)
	public void setbCenterBottomMediaUrl(String bCenterBottomMediaUrl) {
		this.bCenterBottomMediaUrl = bCenterBottomMediaUrl;
	}

	public String getbTopLeftMediaType() {
		return bTopLeftMediaType;
	}

	@XmlAttribute(name = "bTopLeftMediaType", required = false)
	public void setbTopLeftMediaType(String bTopLeftMediaType) {
		this.bTopLeftMediaType = bTopLeftMediaType;
	}

	public String getbTopRightMediaType() {
		return bTopRightMediaType;
	}

	@XmlAttribute(name = "bTopRightMediaType", required = false)
	public void setbTopRightMediaType(String bTopRightMediaType) {
		this.bTopRightMediaType = bTopRightMediaType;
	}

	public String getbBottomLeftMediaType() {
		return bBottomLeftMediaType;
	}

	@XmlAttribute(name = "bBottomLeftMediaType", required = false)
	public void setbBottomLeftMediaType(String bBottomLeftMediaType) {
		this.bBottomLeftMediaType = bBottomLeftMediaType;
	}

	public String getbBottomRightMediaType() {
		return bBottomRightMediaType;
	}

	@XmlAttribute(name = "bBottomRightMediaType", required = false)
	public void setbBottomRightMediaType(String bBottomRightMediaType) {
		this.bBottomRightMediaType = bBottomRightMediaType;
	}

	public String getbCenterTopMediaType() {
		return bCenterTopMediaType;
	}

	@XmlAttribute(name = "bCenterTopMediaType", required = false)
	public void setbCenterTopMediaType(String bCenterTopMediaType) {
		this.bCenterTopMediaType = bCenterTopMediaType;
	}

	public String getbCenterBottomMediaType() {
		return bCenterBottomMediaType;
	}

	@XmlAttribute(name = "bCenterBottomMediaType", required = false)
	public void setbCenterBottomMediaType(String bCenterBottomMediaType) {
		this.bCenterBottomMediaType = bCenterBottomMediaType;
	}

	public String getbBackgroundColor() {
		return bBackgroundColor;
	}

	@XmlAttribute(name = "bBackgroundColor", required = false)
	public void setbBackgroundColor(String bBackgroundColor) {
		this.bBackgroundColor = bBackgroundColor;
	}

	public String getbBackgroundImage() {
		return bBackgroundImage;
	}

	@XmlAttribute(name = "bBackgroundImage", required = false)
	public void setbBackgroundImage(String bBackgroundImage) {
		this.bBackgroundImage = bBackgroundImage;
	}
}
