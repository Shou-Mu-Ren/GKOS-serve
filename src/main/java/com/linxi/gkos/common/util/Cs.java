package com.linxi.gkos.common.util;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linxi.gkos.mapper.CsMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

public class Cs {

    public static void test1(){


        //向CAS服务端请求地址
        String url1 = "https://api.eol.cn/web/api/?admissions=&central=&department=&dual_class=&f211=&f985=&is_doublehigh=&is_dual_class=&keyword=&nature=&page=";
        String url2 = "&province_id=&ranktype=&request_type=1&school_type=&size=20&top_school_id=[589]&type=&uri=apidata/api/gk/school/lists&signsafe=429e2ced223b25d694a7a8ce81f73915";

        String url3 ="https://static-data.gaokao.cn/www/2.0/schoolspecialindex/2022/";
        String url4 ="/33/3/16/";
        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5359.125 Safari/537.36");
        Map<String, String> headMap = new HashMap<>();
        headMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5359.125 Safari/537.36");
        String schoolUrl = url1+ 1 + url2;
        String itemUrl;

        //发送GET请求
        String schoolResult = HttpClientUtils.getInstance().doPost(schoolUrl, headers,null);
        //解析json
        JSONObject resultJsonObject = JSONObject.parseObject(schoolResult);
        String data = resultJsonObject.getString("data");
        JSONObject dataJsonObject = JSONObject.parseObject(data);
        JSONArray schools = dataJsonObject.getJSONArray("item");
        int i = 1;
        for (Object object : schools.toArray()){
//            System.out.println(object.toString());
//            System.out.println("--------------------------------------------------------------------");
            JSONObject school = JSONObject.parseObject(object.toString());
            String schoolId = school.getString("school_id");
            itemUrl = url3 + schoolId + url4 + i + ".json";
            //System.out.println(itemUrl);
            String itemResult = HttpClientUtils.getInstance().doGet(itemUrl, headers);
            //System.out.println(itemResult);
            JSONObject dataes = JSONObject.parseObject(itemResult);
            String datas = dataes.getString("data");
            JSONObject datasJsonObject = JSONObject.parseObject(datas);
            JSONArray items = datasJsonObject.getJSONArray("item");
            for (Object obj : items.toArray()){
                //System.out.println(obj.toString());
                JSONObject ii = JSONObject.parseObject(obj.toString());
                System.out.println(ii.getString("min"));
                System.out.println(ii.getString("min_section"));
                System.out.println(ii.getString("spname"));
                System.out.println(ii.getString("sp_info"));
            }
            System.out.println("*****************************************************************");
            i++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void test2(){
        String url = "http://kaoshi.edu.sina.com.cn/college/collegelist/view?provid=&typeid=&pro=&tab=&page=";
        String result = HttpClientUtils.getInstance().doGet(url+1,null);
        System.out.println(result);
    }

    public static void test3(HttpServletResponse response) throws Exception{


        //向CAS服务端请求地址
        String url1 = "https://api.eol.cn/web/api/?admissions=&central=&department=&dual_class=&f211=&f985=&is_doublehigh=&is_dual_class=&keyword=&nature=&page=";
        String url2 = "&province_id=&ranktype=&request_type=1&school_type=&size=20&top_school_id=[589]&type=&uri=apidata/api/gk/school/lists&signsafe=429e2ced223b25d694a7a8ce81f73915";

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.5359.125 Safari/537.36");


        File file = new File("E:\\gkos\\gkos.xlsx");
        InputStream inputStream = null;
        inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        int lastRowNum = sheet.getLastRowNum();

        for(int i = 1; i <= 145; i++){//145
            String schoolUrl = url1 + i + url2;
            //发送GET请求
            String schoolResult = HttpClientUtils.getInstance().doPost(schoolUrl, headers, null);
            //解析json
            JSONObject resultJsonObject = JSONObject.parseObject(schoolResult);
//            System.out.println(resultJsonObject);
            String data = resultJsonObject.getString("data");
            JSONObject dataJsonObject = JSONObject.parseObject(data);
            JSONArray schools = dataJsonObject.getJSONArray("item");
            for (Object object : schools.toArray()) {
//                System.out.println("--------------------------------------------------------------------");
//                System.out.println(object.toString());
                JSONObject school = JSONObject.parseObject(object.toString());
                String name = school.getString("name");
                String typeName = school.getString("type_name");
                String dualClassName = school.getString("dual_class_name");
                String f211 = school.getString("f211");
                String f985 = school.getString("f985");
                String provinceId = school.getString("province_id");
                String provinceName = school.getString("province_name");
                String cityId = school.getString("city_id");
                String cityName = school.getString("city_name");
                String natureName = school.getString("nature_name");
                row = sheet.createRow(lastRowNum);
                cell = row.createCell((short) 0);
                cell.setCellValue(name);
                cell = row.createCell((short) 1);
                cell.setCellValue(typeName);
                cell = row.createCell((short) 2);
                cell.setCellValue(dualClassName);
                cell = row.createCell((short) 3);
                cell.setCellValue(f211);
                cell = row.createCell((short) 4);
                cell.setCellValue(f985);
                cell = row.createCell((short) 5);
                cell.setCellValue(provinceId);
                cell = row.createCell((short) 6);
                cell.setCellValue(provinceName);
                cell = row.createCell((short) 7);
                cell.setCellValue(cityId);
                cell = row.createCell((short) 8);
                cell.setCellValue(cityName);
                cell = row.createCell((short) 9);
                cell.setCellValue(natureName);
                lastRowNum++;
            }
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("gkos", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }





    public static void test7() throws Exception{

        File file = new File("E:\\gkos\\高考大数据\\数据整理2021\\2021年夏季高考文化总成绩一分一段表.xls");
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row ;
        int lastRowNum = sheet.getLastRowNum();
        Integer grand = null, rank = null;
        for (int i = 3; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(0) != null){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                grand = Integer.parseInt(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(14) != null){
                row.getCell(14).setCellType(XSSFCell.CELL_TYPE_STRING);
                rank = Integer.parseInt(row.getCell(14).getStringCellValue());
            }
            System.out.println(grand + "----"+ rank);
        }
    }

    public static void main(String[] args) {
        try {
            test7();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
