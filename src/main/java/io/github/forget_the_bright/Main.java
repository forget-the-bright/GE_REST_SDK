package io.github.forget_the_bright;

import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.TokenHolder;
import io.github.forget_the_bright.ge.core.print.PrintUtil;
import io.github.forget_the_bright.ge.service.CollectorsApiInvoker;


//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
            PrintUtil.BLUE.Println("i = " + i);
        }
        ApiConfig apiConfig = new ApiConfig();
        new ApiClient(apiConfig);
        TokenHolder tokenHolder = new TokenHolder(apiConfig);
        tokenHolder.setToken("eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vSlhUWS0xNzYtOS91YWEvdG9rZW5fa2V5cyIsImtpZCI6ImtleS1pZC0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiIxOWFmMjAzMTUwMDY0MjUyOTM5N2IyYjZmZTAyNjRhNyIsInN1YiI6ImVmNzMyNDM1LWNmNzAtNGZiOS04NDEzLTk3YTY0YTg1MWVhMiIsInNjb3BlIjpbImloX2FyY2hpdmVfYWRtaW5zIiwiaWhfdW5hdWRpdGVkX3dyaXRlcnMiLCJpaF91bmF1ZGl0ZWRfbG9naW5zIiwiaWhfYXVkaXRlZF93cml0ZXJzIiwiaGlzdG9yaWFuX3Jlc3RfYXBpLnJlYWQiLCJpaF9yZWFkZXJzIiwiaWhfdGFnX2FkbWlucyIsImhpc3Rvcmlhbl9yZXN0X2FwaS53cml0ZSIsImhpc3Rvcmlhbl9lbnRlcnByaXNlLnVzZXIiLCJoaXN0b3JpYW5fcmVzdF9hcGkuYWRtaW4iLCJpaF9jb2xsZWN0b3JfYWRtaW5zIiwiaGlzdG9yaWFuX2VudGVycHJpc2UuYWRtaW4iLCJpaF9zZWN1cml0eV9hZG1pbnMiXSwiY2xpZW50X2lkIjoiaGlzdG9yaWFuX3B1YmxpY19yZXN0X2FwaSIsImNpZCI6Imhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJhenAiOiJoaXN0b3JpYW5fcHVibGljX3Jlc3RfYXBpIiwicmV2b2NhYmxlIjp0cnVlLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX2lkIjoiZWY3MzI0MzUtY2Y3MC00ZmI5LTg0MTMtOTdhNjRhODUxZWEyIiwib3JpZ2luIjoidWFhIiwidXNlcl9uYW1lIjoiSlhUWS0xNzYtOS5hZG1pbiIsImVtYWlsIjoiYWRtaW5AZ2UuY29tIiwiYXV0aF90aW1lIjoxNzQxMDQ4OTgwLCJyZXZfc2lnIjoiZGQ0YzA2YjIiLCJpYXQiOjE3NDEwNDg5ODAsImV4cCI6MTc0MTA5MjE4MCwiaXNzIjoiaHR0cHM6Ly9KWFRZLTE3Ni05L3VhYS9vYXV0aC90b2tlbiIsInppZCI6InVhYSIsImF1ZCI6WyJoaXN0b3JpYW5fZW50ZXJwcmlzZSIsImhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJoaXN0b3JpYW5fcmVzdF9hcGkiXX0.RZfdHK9L-0VcL8VW4E4NQpjZ9RB4xgekFWbpvg1iPLngi_ucH-0qv6e6L3olvchxncdg0_jP_XpS86yVq_5oaWXGV-jwbCGq8LG_1YY5pcaZSyyG9dGEifxyRryJF69QziEo-AcUJXC0j3M7XXI0NccVyg9SMFtQN44HpS6IzYDt3Vl78ALW2qhUHI1GOT2S9ySM1Kw52j8nPkvwp3Y4p5_fFjS237zAiwgLBMP66Zs_xT1fRJY9sJ4Akp0UE3gKu-JL0B-LHVvV1_IXpis--Kyb97ZCFTwktnpM0Xjl7omHj0dybNJwN0RPScND35I16r1-0d4dW0BvUIWHbff_mg");

        System.out.println(TokenHolder.getValidToken());

        JSONObject jsonObject = CollectorsApiInvoker.collectorDetails();
        System.out.println(jsonObject);
    }

}