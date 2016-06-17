package libtag;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ecoh70
 */
public class Tag {

    /**
     * @param source
     * @return 
     */
    public static List<Object> parse(String source) {
	List<Object> parsedScript = new ArrayList<>();
        parsedScript.add(new ArrayList<>());
	String word = "";
	for (char ch : source.toCharArray()) {
            switch (ch) {
                case ':':
                    parsedScript.add(new ArrayList<>());
                    if (!(word.equals(""))) {
                        ((List<Object>) parsedScript.get(parsedScript.size()-1)).add(word);
                        word = "";
                    }
                    break;
                case ';':
                    if (!(word.equals(""))) {
                        ((List<Object>) parsedScript.get(parsedScript.size()-1)).add(word);
                        word = "";
                    }
                    Object temp = ((List<Object>) parsedScript.get(parsedScript.size()-1));
                    parsedScript.remove(temp);
                    ((List<Object>) parsedScript.get(parsedScript.size()-1)).add(temp);
                    break;
                default:
                    word += ch;
                    break;
            }
        }
	return (List<Object>) parsedScript.get(0);
    }
    
    public static String get(String tagdoc, String path) {
        String[] tagpath = path.split(".");
        List<Object> recurse = parse(tagdoc);
        for (String tag : tagpath) {
            for (Object tags : recurse) {
                if (((String) tags).equals(tag)) {
                    recurse = (List<Object>) tags;
                }
            }
        }
        return (String) recurse.get(0);
    }
    public static void main(String[] args) {
        System.out.println(parse("hello:meep:boo;;"));
        System.out.println(get("hello:meep:boo;;", "hello.meep"));
    }
}
