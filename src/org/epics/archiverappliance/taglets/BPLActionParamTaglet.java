package org.epics.archiverappliance.taglets;
import java.util.Map;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/**
 * Taglet for a param. 
 * @author mshankar
 *
 */
public class BPLActionParamTaglet implements Taglet {

    private static final String NAME = "epics.BPLActionParam";

    public String getName() {
        return NAME;
    }

    public boolean inField() {
        return false;
    }

    public boolean inConstructor() {
        return false;
    }

    public boolean inMethod() {
        return true;
    }

    public boolean inOverview() {
        return true;
    }

    public boolean inPackage() {
        return true;
    }

    public boolean inType() {
        return true;
    }

    public boolean isInlineTag() {
        return false;
    }

    /**
     * Register this Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    public static void register(Map<String, Taglet> tagletMap) {
       BPLActionParamTaglet tag = new BPLActionParamTaglet();
       Taglet t = (Taglet) tagletMap.get(tag.getName());
       if (t != null) {
           tagletMap.remove(tag.getName());
       }
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString(Tag tag) {
    	return toString(new Tag[] { tag });
    }

    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) {
        if (tags.length == 0) {
            return null;
        }

        StringBuilder buf = new StringBuilder();
        for(Tag tag : tags) {
            buf.append("<li>");
        	String tagText = tag.text();
        	if(tagText.contains("-")) { 
        		String[] parts = tagText.split("-");
        		buf.append("<b>");
                buf.append(parts[0]);
        		buf.append("</b>");
        		StringBuilder desc = new StringBuilder();
        		for(int i = 1; i < parts.length; i++) { 
        			desc.append(parts[i]);
        		}
        		String paramDesc = desc.toString();
        		buf.append(paramDesc);

        		BPLActionDetails.addParamDesc(parts[0], paramDesc);
        	} else { 
                buf.append(tag.text());
        	}
            buf.append("</li>");
        }

        return buf.toString();
    }    
}

