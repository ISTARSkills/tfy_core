/**
 *
 */
package com.viksitpro.core.cms.interactive;		



import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author ComplexObject
 */

public class EntityOption implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer id;    
    String optionText;
    String backgroundImage;    
    String grid;       
    String backgroundColor;          
    String mediaUrl;    
    String mediaType;        
    String fontColor;        
    String fontSize;    
    Boolean isBold;        
    Boolean isItalic;
    Boolean isUnderlined;
    Boolean draggable;
    Boolean dropable;
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

    @XmlAttribute(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlAttribute(name="option_text", required=false)
    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    @XmlAttribute(name="background_image", required=false)
    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @XmlAttribute(name="grid", required=false)
    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    @XmlAttribute(name="media_url", required=false)
    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    @XmlAttribute(name="media_type", required=false)
    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @XmlAttribute(name="font_color", required=false)
	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	@XmlAttribute(name="font_size", required=false)
	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	@XmlAttribute(name="is_bold", required=false)
	public Boolean getIsBold() {
		return isBold;
	}


	public void setIsBold(Boolean isBold) {
		this.isBold = isBold;
	}

	@XmlAttribute(name="is_italic")
	public Boolean getIsItalic() {
		return isItalic;
	}


	public void setIsItalic(Boolean isItalic) {
		this.isItalic = isItalic;
	}

	@XmlAttribute(name="is_underlined", required=false)
	public Boolean getIsUnderlined() {
		return isUnderlined;
	}


	public void setIsUnderlined(Boolean isUnderlined) {
		this.isUnderlined = isUnderlined;
	}

	@XmlAttribute(name="dragable", required=false)
	public Boolean getDraggable() {
		return draggable;
	}


	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	@XmlAttribute(name="droppable", required=false)
	public Boolean getDropable() {
		return dropable;
	}


	public void setDropable(Boolean dropable) {
		this.dropable = dropable;
	}

    
}

