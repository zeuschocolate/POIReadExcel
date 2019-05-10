# POIReadExcel
#### --利用POI读取Excel文件并转化为html文件,保留格式的同时计算出公式值--

大部分代码取自于网上很多优秀的案例,具体实现流程很简单

以POI的逻辑按从左到右从上到下的过程读取excel表,在读取过程中因为业务需求,在excel单元格中存储有查询对应数据所需的sql,但读取完sql并从对应数据库查值后将值放回cell对象中时将影响与该单元格相关的excel公式计算.于是曲线救国!

逻辑如下:

第一次遍历单元格将忽略有公式格式的单元格的内容,并同时将有sql的单元格内容查完值更改cell值

---没有数据库读取需求或数据库需求不同以下部分代码请自行斟酌按照各自需求进行更改或删除

```java
 //数据库读值填回值(此处忽略公式类型单元格)
        for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
            row = sheet.getRow(rowNum);
            int lastColNum = POIReadExcel.getColsOfTable(sheet)[0];
            int rowHeight = POIReadExcel.getColsOfTable(sheet)[1];
            for (int colNum = 0; colNum < lastColNum; colNum++) {
                cell = row.getCell(colNum);
                String stringValue = getCellValueIgnoreFormula(cell);
                //数据库读值填回值
                if (stringValue.contains("key")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    String url = "jdbc:mysql://127.0.0.1:3306/exceltest?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
                    Connection con = null;
                    try {
                        con = DriverManager.getConnection(url, "root", "916324");
                        Statement statement = con.createStatement();
                        ResultSet resultSet2 = statement.executeQuery("select * from test");
                        while (resultSet2.next()) {
                            stringValue = resultSet2.getString("row");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            con.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    cell.setCellValue(Integer.valueOf(stringValue));
                }
            }
        }

```

第二次遍历单元格时考虑有公式格式的单元格并调用

getCreationHelper().createFormulaEvaluator().evaluateFormulaCell()

方法进行强制计算公式格式单元格值,同时进行字符串拼接操作

最后IO流将拼接完的html文件存入同级目录下,简单粗暴的转换过程完成