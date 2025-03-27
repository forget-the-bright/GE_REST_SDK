package io.github.forget_the_bright.ge.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/27 下午4:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HistorianUnit {
    private Date begin;
    private Date end;
    private Long intervalMs;
    private Integer count;
}
