package libtag;

/**
 *
 * @author ecoh70
 */
public class Tag {
    private final String tag;
    public Tag(String tag) {this.tag=tag;}
    public String getValue() {
        if(tag.substring(tag.indexOf(":")+1,tag.indexOf(";")) != null) {return tag.substring(tag.indexOf(":")+1,tag.indexOf(";"));}
        System.out.println("Cannot retrieve Value of "+tag.split(":")[0].split(",")[0]+": Value is null.");
        System.exit(-1);
        return null;
    }
    public String getDescriptors() {return tag.split(":")[0];}
    public int getID() {return Integer.parseInt(getDescriptor("id"));}
    public String getName() {return getDescriptor("name");}
    public String getType() {return getDescriptor("type");}
    public String getDescriptor(String descriptor) {
        for(int descs=1;descs<new Tag(tag).getDescriptors().split(",").length;descs++) {if(new Tag(tag).getDescriptors().split(",")[descs].startsWith(tag.split(":")[0].split(",")[0]+"."+descriptor)) {return new Tag(tag).getDescriptors().split(",")[descs].substring(new Tag(tag).getDescriptors().split(",")[descs].indexOf("[")+1,new Tag(tag).getDescriptors().split(",")[descs].indexOf("]"));}}
        System.out.println("Cannot Retrieve Descriptor for "+tag.split(":")[0].split(",")[0]+": No Descriptor "+descriptor+" found for Tag "+tag.split(":")[0].split(",")[0]);
        System.exit(-1);
        return null;
    }
    public String setID(String value) {return setDescriptor("id",value);}
    public String setName(String value) {return setDescriptor("name",value);}
    public String setType(String value) {return setDescriptor("type",value);}
    public String setValue(String value) {return tag.replace(new Tag(tag).getDescriptors()+":"+new Tag(tag).getValue()+";",new Tag(tag).getDescriptors()+":"+value+";");}
    public String setDescriptor(String descriptor,String value) {
    for (String split : new Tag(tag).getDescriptors().split(",")) {if (split.startsWith(tag.split(":")[0].split(",")[0]+"."+descriptor)) {return tag.replace(split, split.replace(new Tag(tag).getDescriptor(descriptor), value));}}
        System.out.println("Cannot edit Descriptor "+descriptor+"'s Value for Tag "+tag.split(":")[0].split(",")[0]+": No Descriptor "+descriptor+" found for Tag "+tag.split(":")[0].split(",")[0]);
        System.exit(-1);
        return null;
    }
    public String appendValue(String value) {return new Tag(tag).getDescriptors()+":"+tag.substring(tag.indexOf(":"),tag.indexOf(";"))+value+";";}
    public String addDescriptor(String descriptor,String value) {return new Tag(tag).getDescriptors()+","+tag.split(":")[0].split(",")[0]+"."+descriptor+"["+value+"]"+":"+new Tag(tag).getValue()+";";}
    public String delValue(String value) {return tag.replace(new Tag(tag).getDescriptors()+":"+new Tag(tag).getValue()+";",new Tag(tag).getDescriptors()+":"+";");}
    public String delDescriptor(String tag,String descriptor) {
        for(int descs=0;descs<new Tag(tag).getDescriptors().split(",").length;descs++) {
            if(new Tag(tag).getDescriptors().split(",")[descs].startsWith(tag+"."+descriptor)) {
                if(descs == 0) {return tag.replace(new Tag(tag).getDescriptors().split(",")[descs],tag);}
                else {return tag.replace(","+new Tag(tag).getDescriptors().split(",")[descs],"");}
            }
        }
        System.out.println("Cannot delete Descriptor "+descriptor+"'s Value for Tag "+tag.split(":")[0].split(",")[0]+": No Descriptor "+descriptor+" found for Tag "+tag.split(":")[0].split(",")[0]);
        System.exit(-1);
        return null;
    }
    @Override
    public String toString() {return tag;}
}
