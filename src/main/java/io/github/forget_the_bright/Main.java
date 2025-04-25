package io.github.forget_the_bright;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.constant.common.CalculationMode;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.ApiUtil;
import io.github.forget_the_bright.ge.core.LocalTimedCacheHolder;
import io.github.forget_the_bright.ge.core.TokenHolder;
import io.github.forget_the_bright.ge.core.print.PrintUtil;
import io.github.forget_the_bright.ge.entity.request.data.DataSampleEntity;
import io.github.forget_the_bright.ge.entity.response.DataResult;
import io.github.forget_the_bright.ge.service.DataApiInvoker;

import java.util.Date;


//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        String s = ApiUtil.retainSignificantDecimals("112.0901100", 2);

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
            PrintUtil.BLUE.Println("i = " + i);
        }
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setUsername("tjxcl_ro");
        apiConfig.setPassword("TJXCL2025%");
        new ApiClient(apiConfig);
        TokenHolder tokenHolder = new TokenHolder(new LocalTimedCacheHolder(apiConfig),apiConfig);
        //tokenHolder.setToken("eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vSlhUWS0xNzYtOS91YWEvdG9rZW5fa2V5cyIsImtpZCI6ImtleS1pZC0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiJiZTBmOThlNjQ5Nzc0MjllODVjMWYzYTlhZjM2MzBhOSIsInN1YiI6ImVmNzMyNDM1LWNmNzAtNGZiOS04NDEzLTk3YTY0YTg1MWVhMiIsInNjb3BlIjpbImloX2FyY2hpdmVfYWRtaW5zIiwiaWhfdW5hdWRpdGVkX3dyaXRlcnMiLCJpaF91bmF1ZGl0ZWRfbG9naW5zIiwiaWhfYXVkaXRlZF93cml0ZXJzIiwiaGlzdG9yaWFuX3Jlc3RfYXBpLnJlYWQiLCJpaF9yZWFkZXJzIiwiaWhfdGFnX2FkbWlucyIsImhpc3Rvcmlhbl9yZXN0X2FwaS53cml0ZSIsImhpc3Rvcmlhbl9lbnRlcnByaXNlLnVzZXIiLCJoaXN0b3JpYW5fcmVzdF9hcGkuYWRtaW4iLCJpaF9jb2xsZWN0b3JfYWRtaW5zIiwiaGlzdG9yaWFuX2VudGVycHJpc2UuYWRtaW4iLCJpaF9zZWN1cml0eV9hZG1pbnMiXSwiY2xpZW50X2lkIjoiaGlzdG9yaWFuX3B1YmxpY19yZXN0X2FwaSIsImNpZCI6Imhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJhenAiOiJoaXN0b3JpYW5fcHVibGljX3Jlc3RfYXBpIiwicmV2b2NhYmxlIjp0cnVlLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX2lkIjoiZWY3MzI0MzUtY2Y3MC00ZmI5LTg0MTMtOTdhNjRhODUxZWEyIiwib3JpZ2luIjoidWFhIiwidXNlcl9uYW1lIjoiSlhUWS0xNzYtOS5hZG1pbiIsImVtYWlsIjoiYWRtaW5AZ2UuY29tIiwiYXV0aF90aW1lIjoxNzQxMTY2MzE5LCJyZXZfc2lnIjoiZGQ0YzA2YjIiLCJpYXQiOjE3NDExNjYzMTksImV4cCI6MTc0MTIwOTUxOSwiaXNzIjoiaHR0cHM6Ly9KWFRZLTE3Ni05L3VhYS9vYXV0aC90b2tlbiIsInppZCI6InVhYSIsImF1ZCI6WyJoaXN0b3JpYW5fZW50ZXJwcmlzZSIsImhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJoaXN0b3JpYW5fcmVzdF9hcGkiXX0.aBUW0fjlxbhNmPpD5n4MpRj2k0h7GfwsCEsOX_gb2hl6apMT-Rjd5uGSUwaeGEectxUaSnqjPYs-kd1EPuZEP4ATTF2vm6k4LV_yRAhG8yr7ZeZUUbj-IX1M3Jq-FRCXNAicLcouNGSbBMrzo01oDnM99cNjxuEWDcQ6CPnSVUk8RJPfn1hzY5Z5rfYfYJQLXYhOtQDbMTVDWQVuVvqnpV-dCUPwyZzfBtcxBJAc5pGTm4tK7LQUWkUiMbAGWMUYCrWyiVxxWRukb5AaEQdbDyJlyEGyAD9Wje8HoC6Ry2-lgd6mEVlh1CFlveZSy6oNOJwHUZovhEUACVIqYl3ISA");
        System.out.println(TokenHolder.getValidToken());

       // JSONObject jsonObject = CollectorsApiInvoker.collectorDetails();
        //System.out.println(jsonObject);

        //TagsResult jsonObject1 = TagsApiInvoker.queryTagsByPath(10, "*");


        DataResult currentValueByPathVariable = DataApiInvoker.getCurrentValueByPathVariable("DL.FCS0202.1TI1031B_PV");
        DataResult calculatedByRequestParam = DataApiInvoker.getCalculatedByRequestParam(
                "DL.FCS0202.1TI1031B_PV",
                1,
                new DateTime("2025-02-28"),
                new DateTime("2025-03-05"),
                CalculationMode.Average,
                1000l);
        DataResult interpolatedByPathVariable = DataApiInvoker.getInterpolatedByPathVariable("DL.FCS0202.1TI1031B_PV", new DateTime("2025-02-28"), new DateTime("2025-03-05"), 10, 1000l);
        System.out.println(JSONObject.toJSONString(currentValueByPathVariable));
        System.out.println(JSONObject.toJSONString(calculatedByRequestParam));
        System.out.println(JSONObject.toJSONString(interpolatedByPathVariable));
        DataSampleEntity dataSampleEntity = new DataSampleEntity().setValue("10").setTimeStamp(new Date());//.setQuality(Quality.BAD)
        //String json = "{\"Quality\": 3}"; // 假设的JSON字符串
        //DataSampleEntity item = JSON.parseObject(json, DataSampleEntity.class);
        System.out.println(JSONObject.toJSONString(dataSampleEntity, SerializerFeature.WriteEnumUsingToString));
    }

}