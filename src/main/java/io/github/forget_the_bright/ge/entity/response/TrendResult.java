package io.github.forget_the_bright.ge.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.ge.entity.response.base.DataItem;
import io.github.forget_the_bright.ge.entity.response.base.TrendItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/6/4 上午10:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TrendResult {
    /**
     * Data: A list of data items returned by the server.
     * 数据列表：服务器返回的数据项列表。
     */
    @JSONField(name = "Data")
    private List<TrendItem> data;
}
