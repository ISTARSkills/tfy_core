package com.viksitpro.core.dao.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UiTheme entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ui_theme", schema = "public")

public class UiTheme implements java.io.Serializable {

	// Fields

	private UiThemeId id;

	// Constructors

	/** default constructor */
	public UiTheme() {
	}

	/** full constructor */
	public UiTheme(UiThemeId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
			@AttributeOverride(name = "backgroundColor", column = @Column(name = "background_color")),
			@AttributeOverride(name = "titleFontSize", column = @Column(name = "title_____font_size")),
			@AttributeOverride(name = "titleLineHeight", column = @Column(name = "title_____line_height")),
			@AttributeOverride(name = "titleFontWeight", column = @Column(name = "title_____font_weight")),
			@AttributeOverride(name = "titleTextAlignment", column = @Column(name = "title_____text_alignment")),
			@AttributeOverride(name = "titleFontColor", column = @Column(name = "title_____font_color")),
			@AttributeOverride(name = "titleFontFamily", column = @Column(name = "title_____font_family")),
			@AttributeOverride(name = "subtitleFontSize", column = @Column(name = "subtitle_____font_size")),
			@AttributeOverride(name = "subtitleLineHeight", column = @Column(name = "subtitle_____line_height")),
			@AttributeOverride(name = "subtitleFontWeight", column = @Column(name = "subtitle_____font_weight")),
			@AttributeOverride(name = "subtitleTextAlignment", column = @Column(name = "subtitle_____text_alignment")),
			@AttributeOverride(name = "subtitleFontColor", column = @Column(name = "subtitle_____font_color")),
			@AttributeOverride(name = "subtitleFontFamily", column = @Column(name = "subtitle_____font_family")),
			@AttributeOverride(name = "listitemFontSize", column = @Column(name = "listitem_____font_size")),
			@AttributeOverride(name = "listitemLineHeight", column = @Column(name = "listitem_____line_height")),
			@AttributeOverride(name = "listitemFontWeight", column = @Column(name = "listitem_____font_weight")),
			@AttributeOverride(name = "listitemTextAlignment", column = @Column(name = "listitem_____text_alignment")),
			@AttributeOverride(name = "listitemFontColor", column = @Column(name = "listitem_____font_color")),
			@AttributeOverride(name = "listitemFontFamily", column = @Column(name = "listitem_____font_family")),
			@AttributeOverride(name = "paragraphFontSize", column = @Column(name = "paragraph_____font_size")),
			@AttributeOverride(name = "paragraphLineHeight", column = @Column(name = "paragraph_____line_height")),
			@AttributeOverride(name = "paragraphFontWeight", column = @Column(name = "paragraph_____font_weight")),
			@AttributeOverride(name = "paragraphTextAlignment", column = @Column(name = "paragraph_____text_alignment")),
			@AttributeOverride(name = "paragraphFontColor", column = @Column(name = "paragraph_____font_color")),
			@AttributeOverride(name = "paragraphFontFamily", column = @Column(name = "paragraph_____font_family")) })

	public UiThemeId getId() {
		return this.id;
	}

	public void setId(UiThemeId id) {
		this.id = id;
	}

}