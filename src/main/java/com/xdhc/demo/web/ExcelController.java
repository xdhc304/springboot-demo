package com.xdhc.demo.web;

import com.xdhc.demo.entity.Result;
import com.xdhc.demo.service.ExcelService;
import com.xdhc.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @RequestMapping(value = "/export", produces = {"application/vnd.ms-excel;charset=UTF-8"})
    public Result<String> export(HttpServletResponse response, @RequestParam(defaultValue = "1", value = "dataNum") Integer dataNum){
        try {
            excelService.export(response, dataNum);
            return ResultUtil.error("下载成功");
        } catch (Exception ex){
            ex.printStackTrace();
            return ResultUtil.error("下载失败");
        }
    }
}
