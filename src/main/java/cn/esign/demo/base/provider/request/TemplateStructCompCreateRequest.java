package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 模版添加输入项组件请求
 * @author zhexiu
 * @since 2019/7/21 下午2:28
 */
@Data
public class TemplateStructCompCreateRequest {


    /**
     * 模板的结构化组件列表
     */
    private List<StructComponent> structComponent;


    /**
     * 模板的结构化组件信息
     */
    @Data
    public static class StructComponent {

        /**
         * 结构化组件id,id不为空时表示当前结构化组件是需要修改的
         */
        private String id;

        /**
         * 模板下输入项组件唯一标识，使用模板时也可用根据key值填充
         */
        private String key;

        /**
         * 结构化组件类型， 1-文本,2-数字,3-日期,6-签名区,7-签约方
         */
        private Integer type;


        /**
         * 结构化组件样式坐标等信息
         */
        private Context context;


        /**
         * 组件内容
         */
        @Data
        public static final class Context {

            /**
             * 结构化组件名称
             */
            private String label;

            /**
             * 限制，格式等控制，例如日期组件 yyyy-MM-dd
             */
            private String limit;
            /**
             * 是否必填,默认true ，签约区类型时候标识是否显示签署日期
             */
            private boolean required = true;
            /**
             * 结构化组件样式
             */
            private Style style;

            /**
             * 结构化组件位置信息
             */
            private Position pos;

        }

        /**
         * 组件位置
         */
        @Data
        public class Position {

            /**
             * x坐标
             */
            private Float x;
            /**
             * y坐标
             */
            private Float y;

            /**
             * 页码
             */
            private Integer page;
        }


        /**
         * 组件样式
         */
        public static final class Style  {
            /**
             * 宽度
             */
            private Float width;

            /**
             * 高度
             */
            private Float height;

            /**
             * 字体
             * 默认1，1-宋体，2-新宋体,3-微软雅黑,4-黑体,5-楷体
             */
            private Integer font = 1;
            /**
             * 字体大小, 默认12
             */
            private Integer fontSize = 12;
            /**
             * 字体颜色,默认黑色, #000000
             */
            private String textColor = "#000000";
        }



    }
}
