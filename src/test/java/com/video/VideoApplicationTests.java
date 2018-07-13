package com.video;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.video.dao.CatalogMapper2;
import com.video.service.RedisService;
import com.video.util.EmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoApplicationTests {

	@Autowired
	RedisService redisService;
	@Autowired
	CatalogMapper2 cm2;

	@Test
	public void contextLoads() {

		// redisService.removePattern("userList*");

//		redisService.set("tes111t", "hahhahahah");// 设置键值对 和有效期 秒
	}

	@Test
	public void send() {// 测试邮件发送
		// et.sendTest();
		// et.sendOneNomal("972031129", "test", "content");
		// et.sendOneNomal("972031129", "test", "<a
		// style='color:red;font-size:30px;' href='http://www.baidu.com/'>百度</a>
		// ");

//		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 20; i++) {
			System.out.println((UUID.randomUUID()+"").replaceAll("-", ""));
			
		}

		// map.put("qqs", new String[]{"972031129","1902782804"});
		// String[] qqs=new String[]{"972031129"};//独侠
		// map.put("qqs",qqs );
		// map.put("title", "福利来了！！");
		// map.put("url", "http://119.29.172.118:80/video/index.html?qq=");
		// map.put("img", "http://119.29.172.118:80/video/pic/loading4.gif");
		// map.put("text1",
		// "还在为网上找不到资源而抓狂？还在为下载时间过长而焦虑，没关系一波福利来袭，私人影院为您打造属于您个人的小电影私密空间！解决您的所有烦恼！");
		// map.put("text2", "快点击下面的按钮开启你的全新之旅吧！");
		// map.put("btn1", "老司机入口");
		// map.put("btn2", "新司机入口");
		//
		//
		// map.put("content", EmailUtil.getModel2(map));
		//
		// EmailUtil.sendManyNormal(map);

	}

//	@Test
	public void createCataData() {
		Map<String,Object> map=null;
		InputStream is = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			is = new FileInputStream("C:\\Users\\xz\\Desktop\\saas\\prod-data.xlsx");
			xssfWorkbook = new XSSFWorkbook(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean flag = true;
		int i = 1;//第一行不是数据  从1开始
		int lieshu = 1;
		while (flag) {
			try {
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0); // 获取文件的第一个sheet

				XSSFRow xssfRow1 = sheet.getRow(i); // 获取sheet的第一行

				XSSFCell temp1 = xssfRow1.getCell(1); // 获取111111所在的列，每一列下标从0开始，故这里为1
				
				String category_name=temp1.getStringCellValue();
				
				int num=cm2.count(category_name);
				
				if(num>=1){//已经存在  该分类
					System.out.println("已经存在该品类：" + category_name); // 读取的内容
				}else{
					map=new HashMap<String,Object>();
					map.put("category_name", category_name);
					// store_id  :   d3c32708e6a34f249b4e54dc1ea879ec
					map.put("store_id", "d3c32708e6a34f249b4e54dc1ea879ec");
					// organize_id  :  999e3a4d56464ad08980851a0fabfd70
					map.put("organize_id", "999e3a4d56464ad08980851a0fabfd70");
					// category_id
					map.put("category_id", (UUID.randomUUID()+"").replaceAll("-", ""));
					map.put("id", (UUID.randomUUID()+"").replaceAll("-", ""));
					cm2.insert(map);
					System.out.println("新增品类：" + category_name); // 读取的内容
				}

//				System.out.println("单元个的内容：" + category_name); // 读取的内容
				i++;
				if (i >= 788) {//这里设置读取到第几行
					flag = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				flag=false;
			}finally{
				map=null;
			}

		}
	}
	
	
	@Test
	public void createGoodData() {
		Map<String,Object> map=null;
		InputStream is = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			is = new FileInputStream("C:\\Users\\xz\\Desktop\\saas\\prod-data.xlsx");
			xssfWorkbook = new XSSFWorkbook(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean flag = true;
		int i = 1;//第一行不是数据  从1开始
		int lieshu = 1;
		while (flag) {
			try {
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0); // 获取文件的第一个sheet

				XSSFRow xssfRow1 = sheet.getRow(i); // 获取sheet的第一行

				XSSFCell temp1 = xssfRow1.getCell(0); //获取 goods_name
				String goods_name=temp1.getStringCellValue();
				
				XSSFCell temp2 = xssfRow1.getCell(1); //获取 category_id  进行查询操作  如果没有
				String category_name=temp2.getStringCellValue();
				String category_id=cm2.getIdByName(category_name)!=null?cm2.getIdByName(category_name)+"":"12345";	
				
				XSSFCell temp3 = xssfRow1.getCell(2); //获取 goods_code 
				String goods_code=temp3.getStringCellValue();
				
				XSSFCell temp5 = xssfRow1.getCell(3); //获取 goods_unit 
				String goods_unit=temp5.getStringCellValue();
				
				XSSFCell temp6 = xssfRow1.getCell(6); //获取 goods_price 
//				String goods_price=temp3.getStringCellValue();
				String goods_price="";
				try {
					 goods_price=temp6.getStringCellValue();
				} catch (Exception e) {
					// TODO: handle exception
					 goods_price=temp6.getNumericCellValue()+"";
				}
				                                     
				XSSFCell temp4 = xssfRow1.getCell(23); //获取 goods_state 
				String goods_state=temp4.getStringCellValue();
				
					map=new HashMap<String,Object>();
					map.put("goods_name", goods_name);
					map.put("category_id", category_id);
					map.put("goods_code", goods_code);
					map.put("goods_state", goods_state);
					map.put("goods_unit", goods_unit);
					map.put("goods_price", goods_price);
					map.put("organize_id", "999e3a4d56464ad08980851a0fabfd70");
					map.put("store_id", "d3c32708e6a34f249b4e54dc1ea879ec");
					map.put("id", (UUID.randomUUID()+"").replaceAll("-", ""));
					cm2.insertGood(map);

				System.out.println("单元个的内容：" + category_name); // 读取的内容
				i++;
				System.out.println(i);
				if (i >= 788) {//这里设置读取到第几行
					flag = false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("poi  导入数据出错"+i);
				e.printStackTrace();
//				break;
				flag=false;
			}finally{
				map=null;
			}

		}
	}

}
