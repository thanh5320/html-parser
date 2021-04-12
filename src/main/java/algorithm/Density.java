package algorithm;

import org.jsoup.nodes.Element;

import java.util.Map;

public interface Density {
    public long countTagPlus(Element element);
    public long countTag(Element element);
    public long countChar(Element element);
    public double getDensityOfElement(Element element);
    public Map<Element, Double> ComputingDensity(Element element);
    public double getDensitySumOfElement(Element element);
    public Map<Element, Double> ComputingDensitySum(Element element);
    public Element maxElementDensity(Element element);
    public double threshold(Element element);
}
