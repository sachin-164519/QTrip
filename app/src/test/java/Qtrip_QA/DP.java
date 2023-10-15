package Qtrip_QA;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import Qtrip_QA.utils.ExcelUtils;

public class DP {
    @DataProvider(name="dataProvider")
    public Object[][] dpMethod(Method m){
        // Object data[][] = ExcelUtils.getTableArray("/home/crio-user/workspace/sachin-164519-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx", m.getname());
        // return data;
        switch(m.getName()){
            case "TestCase01": 
            return ExcelUtils.getTableArray("/home/crio-user/workspace/sachin-164519-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx","TestCase01");
        case "TestCase02":
            return ExcelUtils.getTableArray("/home/crio-user/workspace/sachin-164519-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx","TestCase02");    
        case "TestCase03":
            return ExcelUtils.getTableArray("/home/crio-user/workspace/sachin-164519-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx","TestCase03");
        case "TestCase04":
            return ExcelUtils.getTableArray("/home/crio-user/workspace/sachin-164519-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx","TestCase04");    
        default:
            break;
        }
        return null;
    }
}
