package com.exceltest.demo.excel;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author ZEUS
 * @Date 2019/5/6 14:06
 * @Version 1.0
 */
public class Test {

    /**
     * @Author zeus
     * @Description //testExcelToHtml
     * @Date 14:06 2019/5/6
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) {

        System.out.println(POIReadExcel.excelWriteToHtml("D:/BBCJ_NB_DC03.xls"));
    }

}
