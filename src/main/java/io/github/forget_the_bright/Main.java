package io.github.forget_the_bright;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.TokenHolder;
import io.github.forget_the_bright.ge.core.print.PrintUtil;
import io.github.forget_the_bright.ge.entity.alarm.AlarmQueryInfoEntity;
import io.github.forget_the_bright.ge.service.AlarmApiInvoker;
import io.github.forget_the_bright.ge.service.TagsApiInvoker;


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
        tokenHolder.setToken("eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vSlhUWS0xNzYtOS91YWEvdG9rZW5fa2V5cyIsImtpZCI6ImtleS1pZC0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiJmMDk5MGFiMmU1ZTQ0N2VlODFkNWY5NzNjYmVlMWYxOSIsInN1YiI6ImVmNzMyNDM1LWNmNzAtNGZiOS04NDEzLTk3YTY0YTg1MWVhMiIsInNjb3BlIjpbImloX2FyY2hpdmVfYWRtaW5zIiwiaWhfdW5hdWRpdGVkX3dyaXRlcnMiLCJpaF91bmF1ZGl0ZWRfbG9naW5zIiwiaWhfYXVkaXRlZF93cml0ZXJzIiwiaGlzdG9yaWFuX3Jlc3RfYXBpLnJlYWQiLCJpaF9yZWFkZXJzIiwiaWhfdGFnX2FkbWlucyIsImhpc3Rvcmlhbl9yZXN0X2FwaS53cml0ZSIsImhpc3Rvcmlhbl9lbnRlcnByaXNlLnVzZXIiLCJoaXN0b3JpYW5fcmVzdF9hcGkuYWRtaW4iLCJpaF9jb2xsZWN0b3JfYWRtaW5zIiwiaGlzdG9yaWFuX2VudGVycHJpc2UuYWRtaW4iLCJpaF9zZWN1cml0eV9hZG1pbnMiXSwiY2xpZW50X2lkIjoiaGlzdG9yaWFuX3B1YmxpY19yZXN0X2FwaSIsImNpZCI6Imhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJhenAiOiJoaXN0b3JpYW5fcHVibGljX3Jlc3RfYXBpIiwicmV2b2NhYmxlIjp0cnVlLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX2lkIjoiZWY3MzI0MzUtY2Y3MC00ZmI5LTg0MTMtOTdhNjRhODUxZWEyIiwib3JpZ2luIjoidWFhIiwidXNlcl9uYW1lIjoiSlhUWS0xNzYtOS5hZG1pbiIsImVtYWlsIjoiYWRtaW5AZ2UuY29tIiwiYXV0aF90aW1lIjoxNzQwOTc0NDE0LCJyZXZfc2lnIjoiZGQ0YzA2YjIiLCJpYXQiOjE3NDA5NzQ0MTQsImV4cCI6MTc0MTAxNzYxNCwiaXNzIjoiaHR0cHM6Ly9KWFRZLTE3Ni05L3VhYS9vYXV0aC90b2tlbiIsInppZCI6InVhYSIsImF1ZCI6WyJoaXN0b3JpYW5fZW50ZXJwcmlzZSIsImhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJoaXN0b3JpYW5fcmVzdF9hcGkiXX0.XA-NxSreX20BG4DtrsftYpDx5jGznUGnVqyCVX8T9Ancj_ccz1QB2lQxft33Ow4_BLY2W6qF3IZHu8eIzBHyeMNZwhxCQxRngz6Oy1J8w5MwXA9g8rpVrdbUb8CM0sM4jlw6uauLOfifGmSauZvj-vmAbe15p2C-ml6SB927nHMSPB_k9I6YYl44DZJCmJXii126w5BocIQsmMrLsrzk7wVi1wTZAaJi84Fj2i__C5iu27LD7D4jvJzuwizgsUdGLEyQVcOK_4jpI_XlN1vhFxBDDwrKSawBBhJsCFXxt4-yXJIwRESjQkSNGs4lJwf6w_g20fMcL0CFP-ZO_XxEbA");

        System.out.println(TokenHolder.getValidToken());
        System.out.println(TokenHolder.getValidToken());
        System.out.println(TokenHolder.getValidToken());
        JSONObject jsonObject = TagsApiInvoker.queryTagsByPath(10, "DL*");
        jsonObject = TagsApiInvoker.renameTag(false, "123","1231");
        System.out.println(jsonObject);
    }

}