package step2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
public class Task {

    /**
     * 使用fastjson解析数据
     * @param hotelResult	已经为你解析的所需json数据
     * @return
     */
    public List<Hotel> getHotle(String hotelResult){
        /**********   Begin   **********/
        List<Hotel> hotels = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(hotelResult);
        System.out.println(jsonObject);
        List<Hotel> hotelList = JSON.parseArray(jsonObject.getString("hotelPositionJSON"), Hotel.class);//hotelPositionJSON属性解析为一个Hotel类型的List集合
        System.out.println(hotelList);
        JSONArray priceList = jsonObject.getJSONArray("htllist");//获取名为htllist的JSON数组
        System.out.println(priceList);
        for (int i = 0; i < hotelList.size(); i++) {
            Hotel hotel = hotelList.get(i);
            String hotelId = hotel.getId();

            for (int j = 0; j < priceList.size(); j++) {
                JSONObject priceObj = priceList.getJSONObject(j);
                if (priceObj.getString("hotelid").equals(hotelId)) {
                    double price = priceObj.getDoubleValue("amount");
                    hotel.setPrice(price);
                    break;
                }
            }

            hotels.add(hotel);
        }

        return hotels;
        /**********   End   **********/
    }



    /**
     * 由于携程网站经常更新，为了不影响测试，我们直接读取本地文件。
     * @return
     */
    public  String getHotelListString(String cityId,String url){
        String hotelResult="";
        try {
            InputStream is = new FileInputStream(new File("D:\\workspace\\旅游网站大数据分析 - 数据清洗\\src\\step2\\hotelResult.txt"));
            byte[] b=new byte[1024];
            int len=0;
            try {
                while((len=is.read(b))!=-1){//逐个读取文件中的字节，最终将所有读取的字节拼接成一个字符串
                    String str=new String(b,0,len);
                    hotelResult+=str;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return hotelResult;
    }

}
