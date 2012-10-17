package org.epics.archiverappliance.taglets;
import java.util.Map;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/**
 * Taglet for a param. 
 * @author mshankar
 *
 */
public class BPLActionEndTaglet implements Taglet {

    private static final String NAME = "epics.BPLActionEnd";

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
       BPLActionEndTaglet tag = new BPLActionEndTaglet();
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
        buf.append("</ul>");
        buf.append("</div>");
        
        BPLActionDetails.addMethodTerminator();
        
        return buf.toString();
    }    
}

