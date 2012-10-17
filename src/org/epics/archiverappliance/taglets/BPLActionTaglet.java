package org.epics.archiverappliance.taglets;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/**
 * Taglet for the action itself. 
 * @author mshankar
 *
 */
public class BPLActionTaglet implements Taglet {
    private static final String NAME = "epics.BPLAction";
    public static Properties pathMappings = new Properties();
    public static Properties reversePathMappings = new Properties();

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
       BPLActionTaglet tag = new BPLActionTaglet();
       Taglet t = (Taglet) tagletMap.get(tag.getName());
       if (t != null) {
           tagletMap.remove(tag.getName());
       }
       tagletMap.put(tag.getName(), tag);

       try { 
    	   pathMappings.load(new FileInputStream(new File("docs/api/mgmtpathmappings.txt")));
    	   for(Object key : pathMappings.keySet()) { 
    		   String className = pathMappings.getProperty((String) key);
    		   reversePathMappings.put(className, key);
    	   }

    	   File bplactionitems = new File("docs/api/mgmt_scriptables.txt");
	       if(bplactionitems.exists()) { 
	    	   bplactionitems.delete();
	       }
       } catch(Exception ex) { 
    	   throw new RuntimeException(ex);
       }
       
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
        buf.append("<div class=\"subNav\">Access using: <code>http://<i>mgmt_url</i>/bpl");
        ClassDoc doc = (ClassDoc) tags[0].holder();
        String path = reversePathMappings.getProperty(doc.qualifiedName());
		buf.append(path);
        buf.append("</code> ");
        StringBuilder tagDesc = new StringBuilder();
        for(Tag tag : tags) { 
        	tagDesc.append(tag.text());
        }
        String tagDescStr = tagDesc.toString();
        buf.append(tagDescStr);
        buf.append("<ul class=\"blockList\">");
        buf.append("</div>");
        
        BPLActionDetails.addMethod(path, doc.qualifiedName(), tagDescStr);

        return buf.toString();
    }    
}

