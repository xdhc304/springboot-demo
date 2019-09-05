package com.xdhc.demo.service;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    String export(HttpServletResponse response,int dataNum);
}
