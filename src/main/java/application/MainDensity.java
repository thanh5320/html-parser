package application;

import algorithm.Density;
import document.ClearElement;
import algorithm.CompositeTextDensity;
import algorithm.TextDensity;
import document.Doc;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class MainDensity {
    public static void main(String[] args) {

        String url = "https://tiki.vn/sieu-tri-nho-chu-han-tap-2-phien-ban-moi-in-mau-co-audio-nghe-huong-dan-viet-tung-net-tung-chu-dvd-qua-tang-p24819460.html?spid=87108556";
        url = "https://tiki.vn/muong-xao-dandihome-inox-304-ket-hop-be-mat-muong-silicon-cao-cap-chiu-duoc-nhiet-do-cao-an-toan-voi-chao-chong-dinh-p68607055.html?src=ss-organic";
        url="https://kenh14.vn/dem-khuya-thanh-vang-duoc-hanh-khach-boa-sop-tai-xe-taxi-bong-co-du-cam-la-nen-di-theo-chang-ngo-chung-kien-canh-tuong-gay-soc-20210410183620344.chn";
        //url="https://tiki.vn/bo-noi-chao-chong-dinh-baby-elmich-el-1167-2351167-p637936.html?spid=637950";
        //url="https://bonbanh.com/xe-hyundai-tucson-2.0-at-2021-3466706";
        //url="https://trannguyenhan.github.io/posts/discrete-it/";
        Run.run(url, 10);

    }
}
