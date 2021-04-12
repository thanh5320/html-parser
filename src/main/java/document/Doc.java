package document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Doc {
    public static Document getDocumentByUrl(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("error: don't get html from url");
            e.printStackTrace();
        }
        return document;
    }

    public static Document getDocumentByString(String str){
        Document doc = null;
        doc=Jsoup.parse(str);
        return doc;
    }

    public static Element getBodyDocument(Document doc){
        //doc.body();
        return doc.body();
    }

    public static Document clearScript(Document doc){
        String str = doc.html();
        if(str != null) {
            String re = "<script>(.*)</script>";

            Pattern pattern = Pattern.compile(re);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
              //  return str.replace(matcher.group(1), "");
            }
        }
        return doc;
   }

    public static String getTextInTag(Element element) {
        List<Node> listChildNodes = element.childNodes();
        List<String> listContent = new ArrayList<String>();

        for(Node node : listChildNodes) {
            String text = node.outerHtml();
            if(checkHtmlChildNode(text)) {
                listContent.add(text);
            }
        }

        String content = "";
        for(String s : listContent) {
            content = content + "\n" + s;
        }

        content = normalize(content);
        return content;
    }

    public static boolean checkHtmlChildNode(String text) {
        if(text.contains("</") || text.contains("<img") || text.contains("<input") || text.contains("<!--")) {
            return false;
        }

        return true;
    }

    public static String normalize(String text) {
        text=text.trim();
        int lens = text.length();

        int start = 0;
        for(int i=0; i<lens; i++) { // find start
            if(text.charAt(i) != ' ' && text.charAt(i) != '\n') {
                start = i;
                break;
            }
        }

        int last = lens - 1;
        for(int i=lens-1; i>=0; i--) { // fine end
            if(text.charAt(i) != ' ' && text.charAt(i) != '\n') {
                last = i;
                break;
            }
        }

        String newText = text.substring(start, last+1);

        // remove rag <br>
        newText = newText.replace("<br>", "");

        // remove space if duplicate
        while(newText.contains("  ")) {
            newText = newText.replace("  ", " ");
        }

        while(newText.contains("&nbsp;")) {
            newText = newText.replace("&nbsp;", "");
        }

        // remove space after \n
        while(newText.contains("\n ")) {
            newText = newText.replace("\n ", "\n");
        }

        // remove \n if duplicate
        while(newText.contains("\n\n")) {
            newText = newText.replace("\n\n", "\n");
        }

        newText.trim();
        while (newText.endsWith("\n")) newText=newText.substring(0, newText.length()-1);
        newText.trim();
        while (newText.startsWith("\n")) newText=newText.substring(1);

        newText=newText.trim();
        return newText;
    }
}
