package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.util.Cs;
import com.linxi.gkos.pojo.po.Province;
import com.linxi.gkos.service.CsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cs")
public class CsController extends BaseController<Province>{
    @Autowired
    private CsService service;

    @Override
    protected IService<Province> getService() {
        return service;
    }

//    @GetMapping("/c1")
//    @ResponseBody
//    public void c1(HttpServletResponse response) {
//        try {
//            Cs.test3(response);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/c2")
    @ResponseBody
    public void c2() {
        try {
            service.c2();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/c3")
    @ResponseBody
    public void c3() {
        try {
            service.c3();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/c4")
//    @ResponseBody
//    public void c4(@RequestParam("year") String year) {
//        try {
//            service.c4(year);
//        } catch (Exception e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }
//    }



//    @GetMapping("/c5")
//    @ResponseBody
//    public void c5(@RequestParam("year") String year) {
//        try {
//            service.c5(year);
//        } catch (Exception e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/c6")
    @ResponseBody
    public void c6() {
        try {
            service.c6("2020");
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/c7")
    @ResponseBody
    public void c5(@RequestParam("year") String year) {
        try {
            service.c7(year);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
