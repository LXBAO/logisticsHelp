package com.example.mapper;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.fill.Column;

import com.example.mapper.text.OrderService1;
import com.example.mapper.text.ProcessCallEvent;
import com.example.mapper.text.UserService1;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2024/4/23 14:57
 */
public class MainTest {
    String ss = "111";
  static RestTemplate restTemplate = new RestTemplate();

  public static void  createEntity(){

    FastAutoGenerator.create("jdbc:mysql://localhost:3306/easy_speck_job","root","123456").
        globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride().outputDir("c:\\data\\java"))
        // 包配置
        .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")).pathInfo(Collections.singletonMap(OutputFile.xml, "c:\\data\\java\\mapper")))
        // 策略配置
        .strategyConfig((scanner, builder) -> builder.likeTable(new LikeTable("t_", SqlLike.DEFAULT)))
        .strategyConfig((scanner, builder) -> builder.controllerBuilder().enableRestStyle().enableHyphenStyle()
            .entityBuilder().enableLombok()
            .versionColumnName("version") // 基于数据库字段
            .versionPropertyName("version")// 基于模型属性
            .addTableFills(
                new Column("create_time", FieldFill.INSERT)
            ).build())

        .execute();
  }

  static List<Integer> getNumList(Integer num,Supplier<Integer> supplier){
      List<Integer> list = new ArrayList<>();
      for(int i=0;i<num;i++){
        int n = supplier.get();
        list.add(n);
      }
    return list;
  }
        public static void test( Object lock1, Object lock2)    {
            synchronized (lock1){
                try{
                    Thread.sleep(2000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    try{
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
  public static void main(String [] a) throws  Exception {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    ExecutorService threadPoolTaskExecutor =new ThreadPoolExecutor(4, 2000, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(8),new ThreadPoolExecutor.CallerRunsPolicy());

Optional<String> optional = Optional.ofNullable(null);
    System.out.println(optional.orElse("456"));;


             




    //threadPoolTaskExecutor.shutdown();

      //消费型 特点：适用于扩展性业务场景，有参数，但是返回值类型是void
    Map<Integer, Consumer<ProcessCallEvent>> callBackTimeMap = Maps.newHashMapWithExpectedSize(16);
    callBackTimeMap.put(1, ProcessCallEvent::shopping);
    ProcessCallEvent o1 = new OrderService1 ();
    ProcessCallEvent u1 = new UserService1();
    Consumer<ProcessCallEvent>   combinedConsumer = callBackTimeMap.get(1);
    combinedConsumer.accept(u1);
    //供给型 特点：适用于延迟计算 无参，但是有返回值
    List<Integer> list2 = getNumList(10,() -> (int) (Math.random()*100));
    list2.forEach(o -> {
      System.out.println(o);
    });
    Supplier<ExpensiveObject> expensiveObjectSupplier = ExpensiveObject::new;

    // 在需要时才调用 get() 方法创建对象
    ExpensiveObject obj = expensiveObjectSupplier.get();
    System.out.println(obj); // 输出: ExpensiveObject instance

    //函数型 特点： 既有参数又有返回值
    Function<Integer,String> fun = s -> s+"Str";
    System.out.println(fun.apply(1)) ;

    //判断型 特点：有参，返回值类型是boolean结果
    // Predicate中使用一个条件过滤
    Predicate<Integer> noGreaterThan5 = x -> x > 5;
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> collect = list.stream().filter(noGreaterThan5).collect(Collectors.toList());
    System.out.println(collect);

    //Predicate 使用多个条件过滤 and ,or
    Predicate<Integer> noLessThan8 = x -> x < 8;
    List<Integer> collect2 = list.stream()
        .filter(noGreaterThan5.and(noLessThan8))
        .collect(Collectors.toList());
    System.out.println(collect2);

    TestFun fun1 =  x -> x * 2;
    System.out.println(fun1.test(5));
    //createEntity();
   /* String access_token= getAccess_token(),openid="o4C0Q7fiAVakplgykwHboCDKdcV0",
        templateId="SDazq_P9lAe_Uudex9ut2lEo70e_N96UCNbDkG4jvsk";

    String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send" +
        "?access_token=" + access_token;

    //拼接推送的模版
    WxMssVo wxMssVo = new WxMssVo();
    wxMssVo.setTouser(openid);//用户openid

    wxMssVo.setTemplate_id(templateId);//模版id
    Map<String, TemplateDataVo> m = new HashMap<>();
      m.put("character_string2", new TemplateDataVo("co2021112404888" ));
      m.put("phrase1", new TemplateDataVo( "开始"));

      wxMssVo.setData(m);

    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity(url, wxMssVo, String.class);
    System.out.println(responseEntity);*/




  }

  public static String getAccess_token() {
    //获取access_token
   /* String appid = "wxf05a87b9679f2bea";  //appid和appsecret到小程序后台获取
    String appsecret = "5f6491891341acac31fe9d7b76a5ab9b";  //appid和appsecret到小程序后台获取
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
        "&appid=" + appid + "&secret=" + appsecret;

    String json = restTemplate.getForObject(url, String.class);
    JSONObject myJson = JSONObject.parseObject(json);
    return myJson.get("access_token").toString();*/
   return null;
  }

}

@FunctionalInterface
  interface TestFun{

  int test(int x);

}

class ThreadPoolTask implements Runnable{
  ThreadPoolTask(){

  }
  @Override
  public void run() {

  }
}
class ExpensiveObject {
  public ExpensiveObject() {
    // 假设这个构造函数非常耗时
    System.out.println("ExpensiveObject created!");
  }

  @Override
  public String toString() {
    return "ExpensiveObject instance";
  }
}

