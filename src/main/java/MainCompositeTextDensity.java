
import org.jsoup.nodes.Element;
import java.util.Map;


public class MainCompositeTextDensity {
    public static void main(String[] args) {
        Map<Element, Double> mapTextDensityGlobal;
        String url = "https://tiki.vn/sieu-tri-nho-chu-han-tap-2-phien-ban-moi-in-mau-co-audio-nghe-huong-dan-viet-tung-net-tung-chu-dvd-qua-tang-p24819460.html?spid=87108556";
        url = "https://tiki.vn/muong-xao-dandihome-inox-304-ket-hop-be-mat-muong-silicon-cao-cap-chiu-duoc-nhiet-do-cao-an-toan-voi-chao-chong-dinh-p68607055.html?src=ss-organic";
       //url="https://kenh14.vn/vu-nu-du-hoc-sinh-tai-han-quoc-to-bi-hiep-dam-tap-the-me-nan-nhan-lan-dau-len-tieng-chia-se-ve-tinh-trang-cua-con-gai-20210410003407472.chn";
       // url="https://tiki.vn/noi-chien-khong-dau-dien-tu-lock-lock-ejf296blk-7-2-lit-kem-chao-nuong-hang-chinh-hang-p71159586.html?spid=71159587";
       // url="https://kenh14.vn/cap-bao-mot-thanh-vien-cot-can-cua-running-man-ban-viet-can-nhac-thay-the-vi-muon-banh-truong-20210409155850973.chn";
        //url="http://baochinhphu.vn/thoi-su/cong-bo-nghi-quyet-cua-quoc-hoi-ve-bau-tong-kiem-toan-nha-nuoc/428260.vgp";

        Element element = Doc.getBodyDocument(Doc.getDocumentByUrl(url));
        CompositeTextDensity cp = new CompositeTextDensity();
        long cb = cp.countChar(element);
        long lcb= cp.countLinkChar(element);
        mapTextDensityGlobal=cp.ComputingCompositeTextDensity(element, lcb, cb);

        double thres1 = cp.threshold(element, lcb, cb);
        double thres2 = cp.getCompositeDensitySumOfElement(element, lcb, cb);
        double thres = thres1>thres2 ? thres1:thres2;
        for(Map.Entry<Element, Double> ele : mapTextDensityGlobal.entrySet()){
            String tagName=ele.getKey().tagName();
            if(tagName.equals("script")||tagName.equals("style")||tagName.equals("button")||tagName.equals("img")||tagName.equals("a")||tagName.equals("select")) continue;
            if(ele.getValue()>thres*10){
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
