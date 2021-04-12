import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextDensity {
    public TextDensity(){}

    // tính số tag của 1 element
    // trừ 1 đi vì nó tính cả cái thẻ to bên ngoài
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

    // đến số ký tự trong thẻ
    public long countChar(Element element){
        return element.getAllElements().first().text().length();
    }

    // trả về mật độ văn bản của một thẻ
    public double getTextDensityOfElement(Element element){
        long tag=countTag(element);
        tag=tag>1?tag:1;
        return countChar(element)*1.0/tag;
    }

    // trả về 1 mảng các element cùng mật đọ văn bản của chúng
    public Map<Element, Double> ComputingDensity(Element element){
        List<Element> elementList = element.getAllElements();
        Map<Element, Double> mapTextDensity = new HashMap<>();
        for(Element e: elementList){
            double textDensity = getTextDensityOfElement(e);
            mapTextDensity.put(e,textDensity);
        }
        return mapTextDensity;
    }

    //
    public double getDensitySumOfElement(Element element){
        List<Element> elementList = element.children();
        double sum=0;
        for(Element e: elementList){
            sum+=getTextDensityOfElement(e);
        }
        return sum;
    }

    public Map<Element, Double> ComputingDensitySum(Element element){
        List<Element> elementList = element.getAllElements();
        Map<Element, Double> mapTextDensity = new HashMap<>();
        for(Element e: elementList){
            double textDensity = getDensitySumOfElement(e);
            mapTextDensity.put(e,textDensity);
        }
        return mapTextDensity;
    }

    public Element maxElementDensity(Element element){
        Map<Element, Double> mapTextDensity = ComputingDensitySum(element);
        double max = 0;
        Element Emax = null;
        for(Map.Entry<Element, Double> em: mapTextDensity.entrySet()){
            if(em.getValue()>max){
                max=em.getValue();
                Emax=em.getKey();
            }
        }
        return Emax;
    }

    public double threshold(Element element){
        Element Emax = maxElementDensity(element);
        Elements elements = Emax.getAllElements();
        double thres = Double.MAX_VALUE;
        for(Element e : elements){
            double tmp = getDensitySumOfElement(e);
            if(thres>tmp) thres=tmp;
        }
        return thres;
    }

}
