package libtag;

/**
 *
 * @author ecoh70
 */
public class TagUtils {
    String tagfile;
    public TagUtils(String tagfile) {this.tagfile=tagfile;}
    public Tag getTag(String tag) {
        for (String split : tagfile.split(";")) {if (split.startsWith(tag)) {return new Tag(split + ";");}}
        System.out.println("No Tag "+tag+" found.");
        System.exit(-1);
        return null;
    }
    public Tag getTagByID(String tag,int id) {
        for (String split : tagfile.split(";")) {if (split.startsWith(tag) && new Tag(split).getID() == id) {return new Tag(split + ";");}}
        System.out.println("No Tag "+tag+" with ID "+id+" found.");
        System.exit(-1);
        return null;
    }
    public Tag getTagByName(String tag,String name) {
        for (String split : tagfile.split(";")) {if (split.startsWith(tag) && new Tag(split).getName().equals(name)) {return new Tag(split + ";");}}
        System.out.println("No Tag "+tag+" with Name "+name+" found.");
        System.exit(-1);
        return null;
    }
    public Tag getTagByType(String tag,String type) {
        for (String split : tagfile.split(";")) {if (split.startsWith(tag) && new Tag(split).getType().equals(type)) {return new Tag(split + ";");}}
        System.out.println("No Tag "+tag+" with Type "+type+" found.");
        System.exit(-1);
        return null;
    }
    public Tag getTagByDescriptor(String tag,String descriptor,String value) {
        for (String split : tagfile.split(";")) {if (split.startsWith(tag) && new Tag(split).getDescriptor(descriptor).equals(value)) {return new Tag(split + ";");}}
        System.out.println("No Tag "+tag+" with Descriptor "+descriptor+" found.");
        System.exit(-1);
        return null;
    }
    public String addTag(String tag,String value) {return tagfile+tag+":"+value+";";}
    public String delTag(String tag) {
        if(tagfile.contains(new Tag(tag).getDescriptors()+":"+getTag(tag)+";")) {return tagfile.replace(new Tag(tag).getDescriptors()+":"+getTag(tag)+";","");}
        System.out.println("Cannot Delete "+tag+": No Tag "+tag+" found.");
        System.exit(-1);
        return null;
    }
}
