import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.soap.SAAJResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CompositeTextDensity {
    public CompositeTextDensity(){}

    public boolean isRemoveTag(Element element){
        String tagName = element.tagName().toLowerCase();
        if(tagName.equals("script")||tagName.equals("style")||tagName.equals("button")||tagName.equals("img")||tagName.equals("a")||tagName.equals("select")) return true;
        return false;
    }

    //Ti
    public long countTagPlus(Element element){
        long sum=0;
        if(element.childrenSize()==0) {
            return 1;
        }
        else {
            for(Element e: element.children()){
                sum+=countTagPlus(e);
            }
            return sum+1;
        }
    }

    public long countTag(Element element){
        return countTagPlus(element)-1;
    }

    // Ci
    public long countChar(Element element){
        return element.getAllElements().first().text().length();
    }

    //LTi ok
    public long countLinkTag(Element element){
        long sum=0;
        Elements elements = element.getAllElements();
        for(Element e : elements){
           if(e.hasAttr("src") || e.hasAttr("href")){
               sum+=1;
           }
        }
        return sum;
    }

    //LCi tách string ok
    public long countLinkChar(Element element){
        long sum=0;
        Elements elements = element.getElementsByTag("a");
        for(Element e: elements){
            sum+=e.text().length();
        }
        return sum;
        /*
        long sum=0;
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        String textElement = element.getAllElements().first().text();
        StringTokenizer textBlock=new StringTokenizer(textElement, " ");
        while (textBlock.hasMoreTokens()){
            String nextText=textBlock.nextToken();
            if(nextText.matches(regex)){
                sum+=nextText.length();
            }
        }
        return sum;*/
    }

    //-LCi ok
    public long countNotLinkChar(Element element){
        return countChar(element)-countLinkChar(element);
    }

    public double getCompositeTextDensityOfElement(Element element, long lcb, long cb){
        String tagName = element.tagName().toLowerCase();
        if(tagName.equals("script")||tagName.equals("style")||tagName.equals("button")||tagName.equals("img")||tagName.equals("a")||tagName.equals("select")) return 0;

        lcb=lcb>1 ? lcb:1;
        cb=cb>1 ? cb:1;
        long ci=countChar(element);
        ci=ci>1 ? ci:1;
        long ti=countTag(element);
        ti=ti>1 ? ti:1;
        long lci=countLinkChar(element);
        lci=lci>1 ? lci:1;
        long nlci=countNotLinkChar(element);
        nlci=nlci>1 ? nlci:1;
        long lti=countLinkTag(element);
        lti=lti>1 ? lti:1;
        double var1=  ((ci*1.0/nlci)*lci+(lcb*1.0/cb)*ci+Math.E);
        double var2=Math.log(var1);
        double var3=(ci*1.0/lci)*(ti*1.0/lti);
        double var4 =Math.log(var3)/Math.log(var2);
        return (ci*1.0/ti )* var4;
    }


    public Map<Element, Double> ComputingCompositeTextDensity(Element element, long lcb, long cb){
        List<Element> elementList = element.getAllElements();
        Map<Element, Double> mapCompositeTextDensity = new HashMap<>();
        for(Element e: elementList){
            double compositeTextDensity = getCompositeTextDensityOfElement(e, lcb, cb);
            mapCompositeTextDensity.put(e,compositeTextDensity);
        }
        return mapCompositeTextDensity;
    }

    public double getCompositeDensitySumOfElement(Element element, long lcb, long cb){
        List<Element> elementList = element.children();
        double sum=0;
        for(Element e: elementList){
            sum+=getCompositeTextDensityOfElement(e, lcb, cb);
        }
        return sum;
    }

    public Map<Element, Double> ComputingCompositeDensitySum(Element element, long lcb, long cb){
        List<Element> elementList = element.getAllElements();
        Map<Element, Double> mapCompositeTextDensity = new HashMap<>();
        for(Element e: elementList){
            double compositeTextDensity = getCompositeTextDensityOfElement(e, lcb, cb);
            mapCompositeTextDensity.put(e,compositeTextDensity);
        }
        return mapCompositeTextDensity;
    }

    public Element maxElementCompositeDensity(Element element, long lcb, long cb){
        Map<Element, Double> mapCompositeTextDensity = ComputingCompositeDensitySum(element, lcb, cb);
        double max = 0;
        Element Emax = null;
        for(Map.Entry<Element, Double> em: mapCompositeTextDensity.entrySet()){
            if(em.getValue()>max){
                max=em.getValue();
                Emax=em.getKey();
            }
        }
        return Emax;
    }

    public double threshold(Element element, long lcb, long cb){
        Element Emax = maxElementCompositeDensity(element, lcb, cb);
        Elements elements = Emax.getAllElements();
        double thres = Double.MAX_VALUE;
        for(Element e : elements){
            double tmp = getCompositeDensitySumOfElement(e, lcb, cb);
            if(thres>tmp) thres=tmp;
        }
        return thres;
    }

}
