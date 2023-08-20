package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.mapper.CsMapper;
import com.linxi.gkos.pojo.po.*;
import com.linxi.gkos.service.CsService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CsServiceImpl extends ServiceImpl<CsMapper, Province> implements CsService {
    @Autowired
    private CsMapper mapper;

    @Override
    public void c2() throws Exception{
        File file = new File("E:\\gkos\\高考大数据\\city.xlsx");
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row ;
        int lastRowNum = sheet.getLastRowNum();
        String id = null, name = null;
        Map map = new HashMap();
        for (int i = 0; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                id = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                name = row.getCell(1).getStringCellValue();
            }
            map.put(id, name);
        }
        System.out.println(map.size());
        map.forEach((key,value)-> {
            System.out.println(value);
            System.out.println(value.toString());
//            mapper.insertProvince(value.toString());
        });
    }

    @Override
    public void c3() throws Exception{
        File file = new File("E:\\gkos\\高考大数据\\city.xlsx");
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row ;
        int lastRowNum = sheet.getLastRowNum();
        String name = null, provinceName = null;
        Integer provinceId = null;
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                provinceName = row.getCell(1).getStringCellValue();
            }
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                name = row.getCell(3).getStringCellValue();
            }
            provinceId = mapper.findProvinceIdByName(provinceName);
            map.put(name,provinceId);
        }
        System.out.println(map.size());
        City city = new City();
        map.forEach((key,value)->{
            city.setName(key);
            city.setProvinceId(value);
//            mapper.insertCity(city);
        });
    }

    @Override
    public void c4(String year) throws Exception{
        File file = new File("E:\\gkos\\高考大数据\\gkos.xlsx");
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook1 = new XSSFWorkbook(inputStream);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);
        XSSFRow row1;
        int lastRowNum = sheet1.getLastRowNum();
        String str, name = null, code = null, cityName, type = null, nature = null,
                majorCode = null, majorName = null, level = null;
        Integer cityId = null, f211 = null, f985 = null, f11 = null, plan = null, rank = null;
        Map<String, University> universitys = new HashMap<>();
        Major major = new Major();
        Admit admit = new Admit();
        Map<String,Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 0);
        for (int i = 0; i < lastRowNum; i++){
            row1 = sheet1.getRow(i);
            if (row1.getCell(0) != null ){
                row1.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                name = row1.getCell(0).getStringCellValue();
            }
            if (row1.getCell(1) != null ){
                row1.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                type = row1.getCell(1).getStringCellValue();
            }
            if (row1.getCell(2) != null ){
                row1.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                if (row1.getCell(2).getStringCellValue().equals("双一流")){
                    f11 = 1;
                }else{
                    f11 = 0;
                }
            }
            if (row1.getCell(3) != null ){
                row1.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                f211 = map.get(row1.getCell(3).getStringCellValue());
            }
            if (row1.getCell(4) != null ){
                row1.getCell(4).setCellType(XSSFCell.CELL_TYPE_STRING);
                f985 = map.get(row1.getCell(4).getStringCellValue());
            }
            if (row1.getCell(8) != null ){
                row1.getCell(8).setCellType(XSSFCell.CELL_TYPE_STRING);
                cityName = row1.getCell(8).getStringCellValue();
                cityId = mapper.findCityIdByName(cityName);
            }
            if (row1.getCell(9) != null ){
                row1.getCell(9).setCellType(XSSFCell.CELL_TYPE_STRING);
                nature = row1.getCell(9).getStringCellValue();
            }
            University university = new University();
            university.setName(name);
            university.setType(type);
            university.setCityId(cityId);
            university.setNature(nature);
            university.setF11(f11);
            university.setF211(f211);
            university.setF985(f985);
            universitys.put(name,university);
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第1次志愿投档情况表.xls");
        inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row ;
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
                name = str.substring(4);
            }
            if(universitys.get(name) == null && mapper.findUniversityNameByCode(code) == null){
                University university = new University();
                university.setCode(code);
                university.setName(name);
                mapper.insertUniversity(university);
            }else if(universitys.get(name) != null && mapper.findUniversityNameByCode(code) == null){
                universitys.get(name).setCode(code);
                mapper.insertUniversityByAll(universitys.get(name));
            }
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                level = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
                majorName = str.substring(2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setName(majorName);
            major.setLevel(level);
            if(mapper.findMajorIdByCodeAndUniversityCode(major) == null){
                mapper.insertMajorByAll(major);
            }
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                plan = Integer.parseInt(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(6) != null ){
                row.getCell(6).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    rank = Integer.parseInt(row.getCell(6).getStringCellValue());
                }catch (Exception e){
                    rank = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setPlan(plan);
            admit.setRank(rank);
            admit.setYear(year);
            if(rank != null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmitByAll(admit);
            }else if(rank == null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmit(admit);
            }
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第2次志愿投档情况表.xls");
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheet("Sheet1");
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
                name = str.substring(4);
            }
            if(universitys.get(name) == null && mapper.findUniversityNameByCode(code) == null){
                University university = new University();
                university.setCode(code);
                university.setName(name);
                mapper.insertUniversity(university);
            }else if(universitys.get(name) != null && mapper.findUniversityNameByCode(code) == null){
                universitys.get(name).setCode(code);
                mapper.insertUniversityByAll(universitys.get(name));
            }
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                level = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
                majorName = str.substring(2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setName(majorName);
            major.setLevel(level);
            if(mapper.findMajorIdByCodeAndUniversityCode(major) == null){
                mapper.insertMajorByAll(major);
            }
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                plan = Integer.parseInt(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(6) != null ){
                row.getCell(6).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    rank = Integer.parseInt(row.getCell(6).getStringCellValue());
                }catch (Exception e){
                    rank = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setPlan(plan);
            admit.setRank(rank);
            admit.setYear(year);
            if(rank != null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmitByAll(admit);
            }else if(rank == null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmit(admit);
            }
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第3次志愿投档情况表.xls");
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
                name = str.substring(4);
            }
            if(universitys.get(name) == null && mapper.findUniversityNameByCode(code) == null){
                University university = new University();
                university.setCode(code);
                university.setName(name);
                mapper.insertUniversity(university);
            }else if(universitys.get(name) != null && mapper.findUniversityNameByCode(code) == null){
                universitys.get(name).setCode(code);
                mapper.insertUniversityByAll(universitys.get(name));
            }
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                level = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
                majorName = str.substring(2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setName(majorName);
            major.setLevel(level);
            if(mapper.findMajorIdByCodeAndUniversityCode(major) == null){
                mapper.insertMajorByAll(major);
            }
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                plan = Integer.parseInt(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(6) != null ){
                row.getCell(6).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    rank = Integer.parseInt(row.getCell(6).getStringCellValue());
                }catch (Exception e){
                    rank = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setPlan(plan);
            admit.setRank(rank);
            admit.setYear(year);
            if(rank != null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmitByAll(admit);
            }else if(rank == null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmit(admit);
            }
        }
    }

    @Override
    public void c5(String year) throws Exception {
        File file = new File("E:\\gkos\\高考大数据\\gkos.xlsx");
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row;
        int lastRowNum = sheet.getLastRowNum();
        String str, name = null, code = null, cityName, type = null, nature = null,
                majorCode = null, majorName = null, level = null;
        Integer cityId = null, f211 = null, f985 = null, f11 = null, plan = null, rank = null, grand = null;
        Map<String, University> universitys = new HashMap<>();
        Major major = new Major();
        Admit admit = new Admit();
        Map<String,Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 0);
        for (int i = 0; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                name = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                type = row.getCell(1).getStringCellValue();
            }
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                if (row.getCell(2).getStringCellValue().equals("双一流")){
                    f11 = 1;
                }else{
                    f11 = 0;
                }
            }
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                f211 = map.get(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(4) != null ){
                row.getCell(4).setCellType(XSSFCell.CELL_TYPE_STRING);
                f985 = map.get(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(8) != null ){
                row.getCell(8).setCellType(XSSFCell.CELL_TYPE_STRING);
                cityName = row.getCell(8).getStringCellValue();
                cityId = mapper.findCityIdByName(cityName);
            }
            if (row.getCell(9) != null ){
                row.getCell(9).setCellType(XSSFCell.CELL_TYPE_STRING);
                nature = row.getCell(9).getStringCellValue();
            }
            University university = new University();
            university.setName(name);
            university.setType(type);
            university.setCityId(cityId);
            university.setNature(nature);
            university.setF11(f11);
            university.setF211(f211);
            university.setF985(f985);
            universitys.put(name,university);
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第1次志愿投档情况表.xlsx");
        inputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
                name = str.substring(4);
            }
            if(universitys.get(name) == null && mapper.findUniversityNameByCode(code) == null){
                University university = new University();
                university.setCode(code);
                university.setName(name);
                mapper.insertUniversity(university);
            }else if(universitys.get(name) != null && mapper.findUniversityNameByCode(code) == null){
                universitys.get(name).setCode(code);
                mapper.insertUniversityByAll(universitys.get(name));
            }
            if (row.getCell(0) != null ){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                level = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
                majorName = str.substring(2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setName(majorName);
            major.setLevel(level);
            if(mapper.findMajorIdByCodeAndUniversityCode(major) == null){
                mapper.insertMajorByAll(major);
            }
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(3) != null ){
                row.getCell(3).setCellType(XSSFCell.CELL_TYPE_STRING);
                plan = Integer.parseInt(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(5) != null ){
                row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                grand = Integer.parseInt(row.getCell(5).getStringCellValue());
            }
            if (row.getCell(6) != null ){
                row.getCell(6).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    rank = Integer.parseInt(row.getCell(6).getStringCellValue());
                }catch (Exception e){
                    rank = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setPlan(plan);
            admit.setRank(rank);
            admit.setYear(year);
            admit.setGrand(grand);
            if(rank != null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmitByAll2(admit);
            }else if(rank == null && mapper.findAdmitIdByMajorIdAndYear(admit) == null){
                mapper.insertAdmit2(admit);
            }
        }
    }


    @Override
    public void c6(String year) throws Exception{
        String str, code = null, majorCode = null;
        Integer grand = null;
        Major major = new Major();
        Admit admit = new Admit();
        File file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第1次志愿投档情况表.xls");
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row ;
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(5) != null ){
                row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    grand = Integer.parseInt(row.getCell(5).getStringCellValue());
                }catch (Exception e){
                    grand = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setGrand(grand);
            admit.setYear(year);
            if(grand != null){
                mapper.updateAdmitGrand(admit);
            }
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第2次志愿投档情况表.xls");
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheet("Sheet1");
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(5) != null ){
                row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    grand = Integer.parseInt(row.getCell(5).getStringCellValue());
                }catch (Exception e){
                    grand = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setGrand(grand);
            admit.setYear(year);
            if(grand != null){
                mapper.updateAdmitGrand(admit);
            }
        }
        file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\山东省"+year+"年普通类常规批第3次志愿投档情况表.xls");
        inputStream = new FileInputStream(file);
        workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i < lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(2) != null ){
                row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(2).getStringCellValue();
                code = str.substring(0,4);
            }
            if (row.getCell(1) != null ){
                row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                str = row.getCell(1).getStringCellValue();
                majorCode = str.substring(0,2);
            }
            major.setUniversityCode(code);
            major.setCode(majorCode);
            major.setId(mapper.findMajorIdByCodeAndUniversityCode(major));
            if (row.getCell(5) != null ){
                row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                try {
                    grand = Integer.parseInt(row.getCell(5).getStringCellValue());
                }catch (Exception e){
                    grand = null;
                }
            }
            admit.setMajorId(major.getId());
            admit.setGrand(grand);
            admit.setYear(year);
            if(grand != null){
                mapper.updateAdmitGrand(admit);
            }
        }
    }

    public void c7(String year) throws Exception{

        File file = new File("E:\\gkos\\高考大数据\\数据整理"+year+"\\"+year+"年夏季高考文化总成绩一分一段表.xls");
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row ;
        int lastRowNum = sheet.getLastRowNum();
        Admit admit =new Admit();
        admit.setPlan(0);
        admit.setYear(year);
        for (int i = 3; i <= lastRowNum; i++){
            row = sheet.getRow(i);
            if (row.getCell(0) != null){
                row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                admit.setGrand(Integer.parseInt(row.getCell(0).getStringCellValue()));
            }
            if (row.getCell(14) != null){
                row.getCell(14).setCellType(XSSFCell.CELL_TYPE_STRING);
                admit.setRank(Integer.parseInt(row.getCell(14).getStringCellValue()));
            }
            mapper.updateAdmitGrandByRank(admit);
            admit.setPlan(admit.getRank()+1);
        }

    }
}
