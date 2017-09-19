package com.viksitpro.core.cms.oldcontent;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.viksitpro.core.elt.interactive.EntityOption;

@XmlRootElement(name = "outcome")
public class Outcome {
	int points;
	int coins;
	int jumpToSlideID;
	ArrayList<EntityOption> options;
}
