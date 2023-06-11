package step2;


import java.util.List;
import java.util.Scanner;

//输入
//http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx
public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Task task=new Task();
        String hotelResult = task.getHotelListString("1", sc.next());
        List<Hotel> hotle = task.getHotle(hotelResult);
        System.out.println("北京市酒店个数："+hotle.size());
        for (int i = 0; i < 3; i++) {
            System.out.println(hotle.get(i));
        }
    }
}
