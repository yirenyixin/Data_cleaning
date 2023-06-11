package step1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;



public class Task {

    //通过filePath文件路径获取Docment对象
    public Document getDoc(String filePath) throws IOException{

        /**********   Begin   **********/
        File input = new File(filePath);
        //使用Jsoup.parse方法解析文件，指定编码和基础URL
        Document document = Jsoup.parse(input, "UTF-8", "http://www.ctrip.com/");

        return document;
        /**********   End   **********/
    }

    /**
     * 获取清理后的信息
     * @param doc
     * @return
     */
    public List<String> cleanHTML(Document doc){
        /**********   Begin   **********/
        List<String> result = new ArrayList<>();

        //第一组数据：保留富文本标签
        Whitelist whiteList1 = Whitelist.basic();
        String safeHtml1 = Jsoup.clean(doc.toString(), whiteList1);
        result.add(safeHtml1);
        // System.out.println("第一组数据长度：" + safeHtml1.length());

        //第二组数据：只保留文本内容
        Whitelist whiteList2 = Whitelist.simpleText();
        String safeHtml2 = Jsoup.clean(doc.toString(), whiteList2);
        result.add(safeHtml2);
        // System.out.println("第二组数据长度：" + safeHtml2.length());

        return result;
        /**********   End   **********/
    }

}
