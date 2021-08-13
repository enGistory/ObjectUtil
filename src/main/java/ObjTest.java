import cn.hutool.core.codec.Base64;
import entity.Children;
import entity.Son;
import listener.FirstSome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task.ForkJoinTaskImpl;
import task.MyForkJoinTask;
import util.DefaultObjUtil;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ForkJoinTask;


public class ObjTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Son son = new Son();
        //指定属性赋值
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("price","1000");
        System.out.println("转换前--------------------");
        System.out.println("Son："+son);
        Object sonConvrt = DefaultObjUtil.convertDefaultObj(son, map);
        System.out.println("转换后--------------------");
        System.out.println("Son："+son);
        System.out.println(sonConvrt == son);

    }

}
