package com.xdhc.demo.web;

import com.xdhc.demo.service.ExcelService;
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
    public String export(HttpServletResponse response, @RequestParam(value = "dataNum",required = false) Integer dataNum){
        return excelService.export(response,dataNum);
    }
}
