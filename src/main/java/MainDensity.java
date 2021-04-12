import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class MainDensity {
    public static void main(String[] args) {
        Map<Element, Double> mapTextDensityGlobal = new HashMap<>();
        String url = "https://tiki.vn/sieu-tri-nho-chu-han-tap-2-phien-ban-moi-in-mau-co-audio-nghe-huong-dan-viet-tung-net-tung-chu-dvd-qua-tang-p24819460.html?spid=87108556";
        //url = "https://tiki.vn/muong-xao-dandihome-inox-304-ket-hop-be-mat-muong-silicon-cao-cap-chiu-duoc-nhiet-do-cao-an-toan-voi-chao-chong-dinh-p68607055.html?src=ss-organic";
        url="https://kenh14.vn/dem-khuya-thanh-vang-duoc-hanh-khach-boa-sop-tai-xe-taxi-bong-co-du-cam-la-nen-di-theo-chang-ngo-chung-kien-canh-tuong-gay-soc-20210410183620344.chn";
        //url="https://tiki.vn/bo-noi-chao-chong-dinh-baby-elmich-el-1167-2351167-p637936.html?spid=637950";
        Element element = Doc.getBodyDocument(Doc.getDocumentByUrl(url));
        TextDensity textDensity = new TextDensity();
        mapTextDensityGlobal=textDensity.ComputingDensity(element);

        double thres1 = textDensity.threshold(element);
        double thres2 = textDensity.getDensitySumOfElement(element);
        double thres = thres1>thres2 ? thres1:thres2;
        for(Map.Entry<Element, Double> ele : mapTextDensityGlobal.entrySet()){
            String tagName=ele.getKey().tagName();
            if(tagName.equals("script")||tagName.equals("style")||tagName.equals("button")||tagName.equals("img")||tagName.equals("a")||tagName.equals("select")) continue;
            if(ele.getValue()>thres*3){
                String str;
                if(!(str=Doc.getTextInTag(ele.getKey())).equals("")){
                    if(!ClearElement.clear1(ele.getKey()) && !ClearElement.clear2(ele.getKey())) {
                        System.out.println(str);
                    }
                }
            }
        }
    }
}
