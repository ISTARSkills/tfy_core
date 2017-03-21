package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UiThemeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class UiThemeId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String backgroundColor;
	private String titleFontSize;
	private String titleLineHeight;
	private String titleFontWeight;
	private String titleTextAlignment;
	private String titleFontColor;
	private String titleFontFamily;
	private String subtitleFontSize;
	private String subtitleLineHeight;
	private String subtitleFontWeight;
	private String subtitleTextAlignment;
	private String subtitleFontColor;
	private String subtitleFontFamily;
	private String listitemFontSize;
	private String listitemLineHeight;
	private String listitemFontWeight;
	private String listitemTextAlignment;
	private String listitemFontColor;
	private String listitemFontFamily;
	private String paragraphFontSize;
	private String paragraphLineHeight;
	private String paragraphFontWeight;
	private String paragraphTextAlignment;
	private String paragraphFontColor;
	private String paragraphFontFamily;

	// Constructors

	/** default constructor */
	public UiThemeId() {
	}

	/** minimal constructor */
	public UiThemeId(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public UiThemeId(Integer id, String name, String backgroundColor, String titleFontSize, String titleLineHeight,
			String titleFontWeight, String titleTextAlignment, String titleFontColor, String titleFontFamily,
			String subtitleFontSize, String subtitleLineHeight, String subtitleFontWeight, String subtitleTextAlignment,
			String subtitleFontColor, String subtitleFontFamily, String listitemFontSize, String listitemLineHeight,
			String listitemFontWeight, String listitemTextAlignment, String listitemFontColor,
			String listitemFontFamily, String paragraphFontSize, String paragraphLineHeight, String paragraphFontWeight,
			String paragraphTextAlignment, String paragraphFontColor, String paragraphFontFamily) {
		this.id = id;
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.titleFontSize = titleFontSize;
		this.titleLineHeight = titleLineHeight;
		this.titleFontWeight = titleFontWeight;
		this.titleTextAlignment = titleTextAlignment;
		this.titleFontColor = titleFontColor;
		this.titleFontFamily = titleFontFamily;
		this.subtitleFontSize = subtitleFontSize;
		this.subtitleLineHeight = subtitleLineHeight;
		this.subtitleFontWeight = subtitleFontWeight;
		this.subtitleTextAlignment = subtitleTextAlignment;
		this.subtitleFontColor = subtitleFontColor;
		this.subtitleFontFamily = subtitleFontFamily;
		this.listitemFontSize = listitemFontSize;
		this.listitemLineHeight = listitemLineHeight;
		this.listitemFontWeight = listitemFontWeight;
		this.listitemTextAlignment = listitemTextAlignment;
		this.listitemFontColor = listitemFontColor;
		this.listitemFontFamily = listitemFontFamily;
		this.paragraphFontSize = paragraphFontSize;
		this.paragraphLineHeight = paragraphLineHeight;
		this.paragraphFontWeight = paragraphFontWeight;
		this.paragraphTextAlignment = paragraphTextAlignment;
		this.paragraphFontColor = paragraphFontColor;
		this.paragraphFontFamily = paragraphFontFamily;
	}

	// Property accessors

	@Column(name = "id", nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "background_color")

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Column(name = "title_____font_size")

	public String getTitleFontSize() {
		return this.titleFontSize;
	}

	public void setTitleFontSize(String titleFontSize) {
		this.titleFontSize = titleFontSize;
	}

	@Column(name = "title_____line_height")

	public String getTitleLineHeight() {
		return this.titleLineHeight;
	}

	public void setTitleLineHeight(String titleLineHeight) {
		this.titleLineHeight = titleLineHeight;
	}

	@Column(name = "title_____font_weight")

	public String getTitleFontWeight() {
		return this.titleFontWeight;
	}

	public void setTitleFontWeight(String titleFontWeight) {
		this.titleFontWeight = titleFontWeight;
	}

	@Column(name = "title_____text_alignment")

	public String getTitleTextAlignment() {
		return this.titleTextAlignment;
	}

	public void setTitleTextAlignment(String titleTextAlignment) {
		this.titleTextAlignment = titleTextAlignment;
	}

	@Column(name = "title_____font_color")

	public String getTitleFontColor() {
		return this.titleFontColor;
	}

	public void setTitleFontColor(String titleFontColor) {
		this.titleFontColor = titleFontColor;
	}

	@Column(name = "title_____font_family")

	public String getTitleFontFamily() {
		return this.titleFontFamily;
	}

	public void setTitleFontFamily(String titleFontFamily) {
		this.titleFontFamily = titleFontFamily;
	}

	@Column(name = "subtitle_____font_size")

	public String getSubtitleFontSize() {
		return this.subtitleFontSize;
	}

	public void setSubtitleFontSize(String subtitleFontSize) {
		this.subtitleFontSize = subtitleFontSize;
	}

	@Column(name = "subtitle_____line_height")

	public String getSubtitleLineHeight() {
		return this.subtitleLineHeight;
	}

	public void setSubtitleLineHeight(String subtitleLineHeight) {
		this.subtitleLineHeight = subtitleLineHeight;
	}

	@Column(name = "subtitle_____font_weight")

	public String getSubtitleFontWeight() {
		return this.subtitleFontWeight;
	}

	public void setSubtitleFontWeight(String subtitleFontWeight) {
		this.subtitleFontWeight = subtitleFontWeight;
	}

	@Column(name = "subtitle_____text_alignment")

	public String getSubtitleTextAlignment() {
		return this.subtitleTextAlignment;
	}

	public void setSubtitleTextAlignment(String subtitleTextAlignment) {
		this.subtitleTextAlignment = subtitleTextAlignment;
	}

	@Column(name = "subtitle_____font_color")

	public String getSubtitleFontColor() {
		return this.subtitleFontColor;
	}

	public void setSubtitleFontColor(String subtitleFontColor) {
		this.subtitleFontColor = subtitleFontColor;
	}

	@Column(name = "subtitle_____font_family")

	public String getSubtitleFontFamily() {
		return this.subtitleFontFamily;
	}

	public void setSubtitleFontFamily(String subtitleFontFamily) {
		this.subtitleFontFamily = subtitleFontFamily;
	}

	@Column(name = "listitem_____font_size")

	public String getListitemFontSize() {
		return this.listitemFontSize;
	}

	public void setListitemFontSize(String listitemFontSize) {
		this.listitemFontSize = listitemFontSize;
	}

	@Column(name = "listitem_____line_height")

	public String getListitemLineHeight() {
		return this.listitemLineHeight;
	}

	public void setListitemLineHeight(String listitemLineHeight) {
		this.listitemLineHeight = listitemLineHeight;
	}

	@Column(name = "listitem_____font_weight")

	public String getListitemFontWeight() {
		return this.listitemFontWeight;
	}

	public void setListitemFontWeight(String listitemFontWeight) {
		this.listitemFontWeight = listitemFontWeight;
	}

	@Column(name = "listitem_____text_alignment")

	public String getListitemTextAlignment() {
		return this.listitemTextAlignment;
	}

	public void setListitemTextAlignment(String listitemTextAlignment) {
		this.listitemTextAlignment = listitemTextAlignment;
	}

	@Column(name = "listitem_____font_color")

	public String getListitemFontColor() {
		return this.listitemFontColor;
	}

	public void setListitemFontColor(String listitemFontColor) {
		this.listitemFontColor = listitemFontColor;
	}

	@Column(name = "listitem_____font_family")

	public String getListitemFontFamily() {
		return this.listitemFontFamily;
	}

	public void setListitemFontFamily(String listitemFontFamily) {
		this.listitemFontFamily = listitemFontFamily;
	}

	@Column(name = "paragraph_____font_size")

	public String getParagraphFontSize() {
		return this.paragraphFontSize;
	}

	public void setParagraphFontSize(String paragraphFontSize) {
		this.paragraphFontSize = paragraphFontSize;
	}

	@Column(name = "paragraph_____line_height")

	public String getParagraphLineHeight() {
		return this.paragraphLineHeight;
	}

	public void setParagraphLineHeight(String paragraphLineHeight) {
		this.paragraphLineHeight = paragraphLineHeight;
	}

	@Column(name = "paragraph_____font_weight")

	public String getParagraphFontWeight() {
		return this.paragraphFontWeight;
	}

	public void setParagraphFontWeight(String paragraphFontWeight) {
		this.paragraphFontWeight = paragraphFontWeight;
	}

	@Column(name = "paragraph_____text_alignment")

	public String getParagraphTextAlignment() {
		return this.paragraphTextAlignment;
	}

	public void setParagraphTextAlignment(String paragraphTextAlignment) {
		this.paragraphTextAlignment = paragraphTextAlignment;
	}

	@Column(name = "paragraph_____font_color")

	public String getParagraphFontColor() {
		return this.paragraphFontColor;
	}

	public void setParagraphFontColor(String paragraphFontColor) {
		this.paragraphFontColor = paragraphFontColor;
	}

	@Column(name = "paragraph_____font_family")

	public String getParagraphFontFamily() {
		return this.paragraphFontFamily;
	}

	public void setParagraphFontFamily(String paragraphFontFamily) {
		this.paragraphFontFamily = paragraphFontFamily;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UiThemeId))
			return false;
		UiThemeId castOther = (UiThemeId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null
						&& this.getName().equals(castOther.getName())))
				&& ((this.getBackgroundColor() == castOther.getBackgroundColor())
						|| (this.getBackgroundColor() != null && castOther.getBackgroundColor() != null
								&& this.getBackgroundColor().equals(castOther.getBackgroundColor())))
				&& ((this.getTitleFontSize() == castOther.getTitleFontSize())
						|| (this.getTitleFontSize() != null && castOther.getTitleFontSize() != null
								&& this.getTitleFontSize().equals(castOther.getTitleFontSize())))
				&& ((this.getTitleLineHeight() == castOther.getTitleLineHeight())
						|| (this.getTitleLineHeight() != null && castOther.getTitleLineHeight() != null
								&& this.getTitleLineHeight().equals(castOther.getTitleLineHeight())))
				&& ((this.getTitleFontWeight() == castOther.getTitleFontWeight())
						|| (this.getTitleFontWeight() != null && castOther.getTitleFontWeight() != null
								&& this.getTitleFontWeight().equals(castOther.getTitleFontWeight())))
				&& ((this.getTitleTextAlignment() == castOther.getTitleTextAlignment())
						|| (this.getTitleTextAlignment() != null && castOther.getTitleTextAlignment() != null
								&& this.getTitleTextAlignment().equals(castOther.getTitleTextAlignment())))
				&& ((this.getTitleFontColor() == castOther.getTitleFontColor())
						|| (this.getTitleFontColor() != null && castOther.getTitleFontColor() != null
								&& this.getTitleFontColor().equals(castOther.getTitleFontColor())))
				&& ((this.getTitleFontFamily() == castOther.getTitleFontFamily())
						|| (this.getTitleFontFamily() != null && castOther.getTitleFontFamily() != null
								&& this.getTitleFontFamily().equals(castOther.getTitleFontFamily())))
				&& ((this.getSubtitleFontSize() == castOther.getSubtitleFontSize())
						|| (this.getSubtitleFontSize() != null && castOther.getSubtitleFontSize() != null
								&& this.getSubtitleFontSize().equals(castOther.getSubtitleFontSize())))
				&& ((this.getSubtitleLineHeight() == castOther.getSubtitleLineHeight())
						|| (this.getSubtitleLineHeight() != null && castOther.getSubtitleLineHeight() != null
								&& this.getSubtitleLineHeight().equals(castOther.getSubtitleLineHeight())))
				&& ((this.getSubtitleFontWeight() == castOther.getSubtitleFontWeight())
						|| (this.getSubtitleFontWeight() != null && castOther.getSubtitleFontWeight() != null
								&& this.getSubtitleFontWeight().equals(castOther.getSubtitleFontWeight())))
				&& ((this.getSubtitleTextAlignment() == castOther.getSubtitleTextAlignment())
						|| (this.getSubtitleTextAlignment() != null && castOther.getSubtitleTextAlignment() != null
								&& this.getSubtitleTextAlignment().equals(castOther.getSubtitleTextAlignment())))
				&& ((this.getSubtitleFontColor() == castOther.getSubtitleFontColor())
						|| (this.getSubtitleFontColor() != null && castOther.getSubtitleFontColor() != null
								&& this.getSubtitleFontColor().equals(castOther.getSubtitleFontColor())))
				&& ((this.getSubtitleFontFamily() == castOther.getSubtitleFontFamily())
						|| (this.getSubtitleFontFamily() != null && castOther.getSubtitleFontFamily() != null
								&& this.getSubtitleFontFamily().equals(castOther.getSubtitleFontFamily())))
				&& ((this.getListitemFontSize() == castOther.getListitemFontSize())
						|| (this.getListitemFontSize() != null && castOther.getListitemFontSize() != null
								&& this.getListitemFontSize().equals(castOther.getListitemFontSize())))
				&& ((this.getListitemLineHeight() == castOther.getListitemLineHeight())
						|| (this.getListitemLineHeight() != null && castOther.getListitemLineHeight() != null
								&& this.getListitemLineHeight().equals(castOther.getListitemLineHeight())))
				&& ((this.getListitemFontWeight() == castOther.getListitemFontWeight())
						|| (this.getListitemFontWeight() != null && castOther.getListitemFontWeight() != null
								&& this.getListitemFontWeight().equals(castOther.getListitemFontWeight())))
				&& ((this.getListitemTextAlignment() == castOther.getListitemTextAlignment())
						|| (this.getListitemTextAlignment() != null && castOther.getListitemTextAlignment() != null
								&& this.getListitemTextAlignment().equals(castOther.getListitemTextAlignment())))
				&& ((this.getListitemFontColor() == castOther.getListitemFontColor())
						|| (this.getListitemFontColor() != null && castOther.getListitemFontColor() != null
								&& this.getListitemFontColor().equals(castOther.getListitemFontColor())))
				&& ((this.getListitemFontFamily() == castOther.getListitemFontFamily())
						|| (this.getListitemFontFamily() != null && castOther.getListitemFontFamily() != null
								&& this.getListitemFontFamily().equals(castOther.getListitemFontFamily())))
				&& ((this.getParagraphFontSize() == castOther.getParagraphFontSize())
						|| (this.getParagraphFontSize() != null && castOther.getParagraphFontSize() != null
								&& this.getParagraphFontSize().equals(castOther.getParagraphFontSize())))
				&& ((this.getParagraphLineHeight() == castOther.getParagraphLineHeight())
						|| (this.getParagraphLineHeight() != null && castOther.getParagraphLineHeight() != null
								&& this.getParagraphLineHeight().equals(castOther.getParagraphLineHeight())))
				&& ((this.getParagraphFontWeight() == castOther.getParagraphFontWeight())
						|| (this.getParagraphFontWeight() != null && castOther.getParagraphFontWeight() != null
								&& this.getParagraphFontWeight().equals(castOther.getParagraphFontWeight())))
				&& ((this.getParagraphTextAlignment() == castOther.getParagraphTextAlignment())
						|| (this.getParagraphTextAlignment() != null && castOther.getParagraphTextAlignment() != null
								&& this.getParagraphTextAlignment().equals(castOther.getParagraphTextAlignment())))
				&& ((this.getParagraphFontColor() == castOther.getParagraphFontColor())
						|| (this.getParagraphFontColor() != null && castOther.getParagraphFontColor() != null
								&& this.getParagraphFontColor().equals(castOther.getParagraphFontColor())))
				&& ((this.getParagraphFontFamily() == castOther.getParagraphFontFamily())
						|| (this.getParagraphFontFamily() != null && castOther.getParagraphFontFamily() != null
								&& this.getParagraphFontFamily().equals(castOther.getParagraphFontFamily())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (getBackgroundColor() == null ? 0 : this.getBackgroundColor().hashCode());
		result = 37 * result + (getTitleFontSize() == null ? 0 : this.getTitleFontSize().hashCode());
		result = 37 * result + (getTitleLineHeight() == null ? 0 : this.getTitleLineHeight().hashCode());
		result = 37 * result + (getTitleFontWeight() == null ? 0 : this.getTitleFontWeight().hashCode());
		result = 37 * result + (getTitleTextAlignment() == null ? 0 : this.getTitleTextAlignment().hashCode());
		result = 37 * result + (getTitleFontColor() == null ? 0 : this.getTitleFontColor().hashCode());
		result = 37 * result + (getTitleFontFamily() == null ? 0 : this.getTitleFontFamily().hashCode());
		result = 37 * result + (getSubtitleFontSize() == null ? 0 : this.getSubtitleFontSize().hashCode());
		result = 37 * result + (getSubtitleLineHeight() == null ? 0 : this.getSubtitleLineHeight().hashCode());
		result = 37 * result + (getSubtitleFontWeight() == null ? 0 : this.getSubtitleFontWeight().hashCode());
		result = 37 * result + (getSubtitleTextAlignment() == null ? 0 : this.getSubtitleTextAlignment().hashCode());
		result = 37 * result + (getSubtitleFontColor() == null ? 0 : this.getSubtitleFontColor().hashCode());
		result = 37 * result + (getSubtitleFontFamily() == null ? 0 : this.getSubtitleFontFamily().hashCode());
		result = 37 * result + (getListitemFontSize() == null ? 0 : this.getListitemFontSize().hashCode());
		result = 37 * result + (getListitemLineHeight() == null ? 0 : this.getListitemLineHeight().hashCode());
		result = 37 * result + (getListitemFontWeight() == null ? 0 : this.getListitemFontWeight().hashCode());
		result = 37 * result + (getListitemTextAlignment() == null ? 0 : this.getListitemTextAlignment().hashCode());
		result = 37 * result + (getListitemFontColor() == null ? 0 : this.getListitemFontColor().hashCode());
		result = 37 * result + (getListitemFontFamily() == null ? 0 : this.getListitemFontFamily().hashCode());
		result = 37 * result + (getParagraphFontSize() == null ? 0 : this.getParagraphFontSize().hashCode());
		result = 37 * result + (getParagraphLineHeight() == null ? 0 : this.getParagraphLineHeight().hashCode());
		result = 37 * result + (getParagraphFontWeight() == null ? 0 : this.getParagraphFontWeight().hashCode());
		result = 37 * result + (getParagraphTextAlignment() == null ? 0 : this.getParagraphTextAlignment().hashCode());
		result = 37 * result + (getParagraphFontColor() == null ? 0 : this.getParagraphFontColor().hashCode());
		result = 37 * result + (getParagraphFontFamily() == null ? 0 : this.getParagraphFontFamily().hashCode());
		return result;
	}

}