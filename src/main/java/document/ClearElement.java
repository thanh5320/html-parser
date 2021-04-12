package document;

import org.jsoup.nodes.Element;

import java.util.Locale;

public class ClearElement {
    public static boolean clear1(Element element){
        if(element.tagName().equals("body")){
            return false;
        }
        else if(element.tagName().toLowerCase().equals("footer")|| element.tagName().toLowerCase().equals("a") ||
        element.tagName().toLowerCase().equals("img") || element.tagName().toLowerCase().equals("script")||
                element.tagName().toLowerCase().equals("style")|| element.tagName().toLowerCase().equals("button")||
                element.tagName().toLowerCase().equals("select")){
            return true;
        }
        return clear1(element.parent());
    }

    public static boolean clear2(Element element){
        if(element.tagName().toLowerCase().equals("body")){
            return false;
        }
        else if(element.id().toLowerCase().contains("footer") || element.className().toLowerCase().contains("footer")
        || element.id().toLowerCase().contains("login") || element.className().toLowerCase().contains("login")){
            return true;
        }
        return clear2(element.parent());
    }

    public static boolean clear3(Element element){
        String tagName=element.tagName();
        if(tagName.equals("script")||tagName.equals("style")||tagName.equals("button")||tagName.equals("img")||tagName.equals("a")||tagName.equals("select")) return true;
        return false;
    }
}
