package utilities;


import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {


    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String xlFilePath = ".//testData//LoginData.xlsx";

        ExcelUtil xlUti = new ExcelUtil(xlFilePath);

        int totalRowCount = xlUti.getTotalRows("Sheet1");
        int totalColCount = xlUti.getTotalColumns("Sheet1",1);

        String[][] loadData = new String[totalRowCount][totalColCount];

        for (int rows=1;rows<=totalRowCount;rows++){
            for (int col = 0;col<totalColCount;col++){
                loadData[rows-1][col] = xlUti.getCellData("Sheet1",rows,col);
            }
        }

        return loadData;
    }

}
