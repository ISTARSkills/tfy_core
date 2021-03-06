/**
 *
 */
package com.viksitpro.core.cms.interactive;		

import java.io.Serializable;
import java.util.HashMap;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;

/**
 * @author ComplexObject
 */

public class EntityOption implements Serializable {

    @Attribute(name = "id", required = false)
    Integer id;
    @Attribute(name = "option_text", required = false)
    String optionText;
    @Attribute(name = "image_url", required = false)
    String backgroundImage;
    @Attribute(name = "option_grid", required = false)
    String grid;
    @Attribute(name = "iscorrect", required = false)
    boolean isCorrect;
    @Attribute(name = "bg_color", required = false)
    String backgroundColor;

    @Attribute(name = "next_entity", required = false)
    Integer nextEntity;

    @ElementMap(entry = "info_cards",inline = false, required = false)
    HashMap<Integer, InfoCard> cards = new HashMap<Integer, InfoCard>();

    @Attribute(name = "eval_script", required = false)
    String evaluationScript;

    @Attribute(name = "media_url", required = false)
    String mediaUrl;
    @Attribute(name = "media_type", required = false)
    String mediaType;
    
    @Attribute(name = "font_color", required = false)
    String fontColor;
    
    @Attribute(name = "font_size", required = false)
    String fontSize;

    @Attribute(name = "is_bold", required = false)
    boolean isBold;
    
    @Attribute(name = "is_italic", required = false)
    boolean isItalic;
    
    @Attribute(name = "is_underlined", required = false)
    boolean isUnderlined;
    
    public HashMap<Integer, InfoCard> getCards() {
        return cards;
    }

    public void setCards(HashMap<Integer, InfoCard> cards) {
        this.cards = cards;
    }


    public EntityOption() {
        super();
        // TODO Auto-generated constructor stub
    }


    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }


    public Integer getNextEntity() {
        return nextEntity;
    }

    public void setNextEntity(Integer nextEntity) {
        this.nextEntity = nextEntity;
    }

    public String getEvaluationScript() {
        return evaluationScript;
    }

    public void setEvaluationScript(String evaluationScript) {
        this.evaluationScript = evaluationScript;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}

	public boolean isItalic() {
		return isItalic;
	}

	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
	}

	public boolean isUnderlined() {
		return isUnderlined;
	}

	public void setUnderlined(boolean isUnderlined) {
		this.isUnderlined = isUnderlined;
	}  
	   
    
}

