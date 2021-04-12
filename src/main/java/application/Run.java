package application;

import algorithm.CompositeTextDensity;
import algorithm.Density;
import algorithm.TextDensity;
import document.ClearElement;
import document.Doc;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void run(String url, int w){
        Map<Element, Double> mapDensityGlobal = new HashMap<>();
        Element element = Doc.getBodyDocument(Doc.getDocumentByUrl(url));
        TextDensity textDensity = new TextDensity();
        CompositeTextDensity compositeTextDensity = new CompositeTextDensity();
        long cb = compositeTextDensity.countChar(element);
        long lcb= compositeTextDensity.countLinkChar(element);
        compositeTextDensity.setLcb(lcb);
        compositeTextDensity.setLcb(cb);
        //mapDensityGlobal=textDensity.ComputingDensity(element);
        Density density = compositeTextDensity;
        //Density density = textDensity;
        mapDensityGlobal=density.ComputingDensity(element);

        double thres1 = density.threshold(element);
        double thres2 = density.getDensitySumOfElement(element);
        double thres = thres1>thres2 ? thres1:thres2;
        for(Map.Entry<Element, Double> ele : mapDensityGlobal.entrySet()){
            if(ele.getValue()>thres*w){
                if(!ClearElement.clear1(ele.getKey()) && !ClearElement.clear2(ele.getKey()) && !ClearElement.clear3(ele.getKey())) {
                    System.out.println(Doc.getTextInTag(ele.getKey()));
                }
            }
        }
    }
}
