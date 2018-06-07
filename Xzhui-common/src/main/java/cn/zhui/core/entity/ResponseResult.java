package cn.zhui.core.entity;

import cn.zhui.core.utils.ResponseCode;
import cn.zhui.core.utils.SpringContextHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.Locale;

/**
 * ��Ӧ���
 * Created by LIN on 2018/06/06.
 */
@ApiModel(value = "��Ӧ��Ϣ")
public class ResponseResult<T> implements Serializable {
    private  static  Logger logger = LoggerFactory.getLogger(ResponseResult.class);

    private static final long serialVersionUID = 6382523290069620956L;

    @ApiModelProperty("��Ӧ�룬��ʾ����ɹ���200���ɹ�")
    private String code;

    @ApiModelProperty("��Ӧ����")
    private String msg;

    @ApiModelProperty("��������")
    private T data;

    @JsonIgnore
    @ApiModelProperty("���ʻ�����")
    private String i18nCode;

    @JsonIgnore
    @ApiModelProperty("���ʻ��������")
    private Object[] i18nArgs;

    public ResponseResult(){ super(); }

    public ResponseResult (String code, String msg, T data ) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() { return code; }

    public ResponseResult<T> setCode ( String code ) {
        this.code = code;
        return  this;
    }

    public String getI18nCode() { return i18nCode; }

    public Object[] getI18nArgs() { return i18nArgs; }

    public void setI18nArgs(Object[] i18nArgs) {
        this.i18nArgs = i18nArgs;
    }

    public ResponseResult<T> setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
        return this;
    }

    public String getMsg() {
        // ���û�����ù��ʻ�code����ֱ�ӷ���
        if (StringUtils.isBlank(this.getI18nCode())) {
            return this.msg;
        }
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            return this.msg;
        }
        String i18nMsg = null;
        try {
            i18nMsg = SpringContextHolder.getApplicationContext().getMessage(i18nCode, this.getI18nArgs(), locale);

        } catch (Exception e) {
            logger.warn("get i18n msg for code[" + i18nCode + "] failed");
        }
        return StringUtils.isBlank(i18nMsg) || this.getI18nCode().equals(i18nMsg) ? this.msg : i18nMsg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static ResponseResult<String> success() { return success(""); }

    public static <T> ResponseResult<T> success(T data){
        return  new ResponseResult<T>().setCode(ResponseCode.Success.getCode())
                .setI18nCode(ResponseCode.Success.getKey()).setMsg(ResponseCode.Success.getMsg())
                .setData(data);
    }

    public static ResponseResult<String> fail( ResponseCode code) { return fail(code,""); }

    public static <T> ResponseResult<T> fail( ResponseCode code , T data) {
        return new ResponseResult<T>().setCode(code.getCode()).setI18nCode(code.getKey())
                .setMsg(code.getMsg()).setData(data);
    }
}
