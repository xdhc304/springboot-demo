package com.xdhc.demo.service.impl;


import com.xdhc.demo.entity.Excel;
import com.xdhc.demo.service.ExcelService;
import com.xdhc.demo.util.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {

    /**
     * 输出方法
     *
     * @param response
     * @return
     */
    @Override
    public String export(HttpServletResponse response, int dataNum) {
        try {
            List<Excel> listExcel = ExcelUtil.mockExcel(dataNum);
            String fileName="项目审核表";
            List<Map<String,Object>> list=createExcelRecord(listExcel);
            String columnNames[] = {"编号","名称","父节点名称","简称","级别","城市编码","区域编码"};//列名
            String keys[] = {"id","name","parentId","shortName","levelType","cityCode","zipCode"};//map中的key
            ExcelUtil.downloadWorkBook(list,keys,columnNames,fileName,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "excel";
    }

    /**
     * 创建Excel表中的记录
     * @param ExcelList
     * @return
     */
    private List<Map<String, Object>> createExcelRecord(List<Excel> ExcelList){
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sheetName", "sheet1");
            listmap.add(map);
            for (int j = 0; j < ExcelList.size(); j++) {
                Excel Excel=ExcelList.get(j);
                Map<String, Object> mapValue = new HashMap<String, Object>();
                mapValue.put("id",Excel.getId());
                mapValue.put("name",Excel.getName());
                mapValue.put("parentId",Excel.getParentId());
                mapValue.put("shortName",Excel.getShortName());
                mapValue.put("levelType",Excel.getLevelType());
                mapValue.put("cityCode",Excel.getCityCode());
                mapValue.put("zipCode",Excel.getZipCode());
                //mapValue.put("submitTime", DateTimeUtil.dateToStr(projectAuditListVo.getSubmitTime(),"yyyy-MM-dd"));
                //String attachmentURL = projectAuditListVo.getAttachment()==null?"无": FileUtil.getUploadPath()+projectAuditListVo.getAttachment();

                listmap.add(mapValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listmap;
    }
}
