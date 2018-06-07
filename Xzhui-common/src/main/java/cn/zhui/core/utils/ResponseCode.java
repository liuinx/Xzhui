package cn.zhui.core.utils;

/**
 * ״̬��Ӧ��
 * Created by LIN on 2018/06/06.
 */
public enum ResponseCode {

    Success("200", "base_success", "�����ɹ�"),
    ERROR("500", "base_error", "ϵͳ�ڲ��쳣");

    private String httpCode;
    private String key;
    private String msg;

    private ResponseCode (String httpCode, String key, String msg){
        this.httpCode = httpCode;
        this.key = key;
        this.msg = msg;
    }
    public String getCode (){ return httpCode; }
    public String getKey () { return key; }
    public String getMsg () { return msg; }
}
