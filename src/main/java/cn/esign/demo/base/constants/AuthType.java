package cn.esign.demo.base.constants;


/**
 * 授权类型
 */
public enum AuthType {

    /**
     * 客户端模式授权
     */
    CLIENT_CREDENTIALS("client_credentials", "客户端模式授权");

    private String grantType;
    private String desc;

    private AuthType(String grantType, String desc) {
        this.grantType = grantType;
        this.desc = desc;
    }

    public String getGrantType() {
        return this.grantType;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.grantType;
    }
}