/**
 *
 */
package com.viksitpro.core.cms.interactive;		

import java.io.Serializable;
import java.util.HashMap;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;


/**
 * @author ComplexObject
 */
@Root(name = "card_entity")
public class Entity implements Serializable {

    @Attribute(name = "id", required = false)
    Integer id;
    @Attribute(name = "image_url", required = false)
    String backgroundImage;
    @Attribute(name = "question_grid", required = false)
    String grid;
    @Attribute(name = "correct_opt_message", required = false)
    String correctMessage;
    @Attribute(name = "incorrect_opt_message", required = false)
    String incorrectMessage;
    @Attribute(name = "action", required = false)
    String actionType;
    @Attribute(name = "q_text", required = false)
    String text;
    @Attribute(name = "bg_color", required = false)
    String bgColor;
    @ElementMap(entry = "option_entity",  inline = false, required = false)
    HashMap<Integer, EntityOption> options;
    
    @Attribute(name = "transition_image", required = false)
    String transitionImage;
    
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
    

    public String getBgColor() {
        return bgColor;
    }


    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }


    public Entity() {
        super();
        // TODO Auto-generated constructor stub
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getActionType() {
        return actionType;
    }


    public void setActionType(String actionType) {
        this.actionType = actionType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCorrectMessage() {
        return correctMessage;
    }

    public void setCorrectMessage(String correctMessage) {
        this.correctMessage = correctMessage;
    }

    public String getIncorrectMessage() {
        return incorrectMessage;
    }

    public void setIncorrectMessage(String incorrectMessage) {
        this.incorrectMessage = incorrectMessage;
    }


    public HashMap<Integer, EntityOption> getOptions() {
        return options;
    }


    public void setOptions(HashMap<Integer, EntityOption> options) {
        this.options = options;
    }

    public String getTransitionImage() {
        return transitionImage;
    }

    public void setTransitionImage(String transitionImage) {
        this.transitionImage = transitionImage;
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
