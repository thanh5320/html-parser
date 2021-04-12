import org.jsoup.nodes.Element;

public class ClearElement {
    public static boolean clear1(Element element){
        if(element.tagName().equals("body")){
            return false;
        }
        else if(element.tagName().equals("footer")){
            return true;
        }
        return clear1(element.parent());
    }

    public static boolean clear2(Element element){
        if(element.tagName().toLowerCase().equals("body")){
            return false;
        }
        else if(element.id().toLowerCase().contains("footer") || element.className().toLowerCase().contains("footer")){
            return true;
        }
        return clear2(element.parent());
    }
}
