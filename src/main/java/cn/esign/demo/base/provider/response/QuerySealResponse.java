package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * 查询用户印章信息
 * @author zhexiu
 * @since 2019/7/21 下午2:55
 */
@Data
public class QuerySealResponse {

    /**
     * 印章列表
     */
    private List<OrgSealBean> seals;

    /**
     * 查询总数
     */
    private Long total;



}
