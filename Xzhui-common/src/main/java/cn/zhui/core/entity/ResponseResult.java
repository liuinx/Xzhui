package cn.zhui.core.entity;

import cn.zhui.core.utils.ResponseCode;
import cn.zhui.core.utils.SpringContextHolder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

/**
 * 响应结果
 * Created by LIN on 2018/06/06.
 */
@ApiModel(value = "响应信息")
public class ResponseResult<T> implements Serializable {
    private  static  Logger logger = LoggerFactory.getLogger(ResponseResult.class);

    private static final long serialVersionUID = 6382523290069620956L;

    @ApiModelProperty("响应码，表示请求成功。200：成功")
    private String code;

    @ApiModelProperty("响应描述")
    private String msg;
    
    @ApiModelProperty("数据总数")
    private int count;
    
    @ApiModelProperty("返回数据")
    private T data;

    public ResponseResult(){ super(); }

    public ResponseResult (String code, String msg, T data ) {
        super();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String getCode() { return code; }

    public ResponseResult<T> setCode ( String code ) {
        this.code = code;
        return  this;
    }

    public String getMsg() {
		return msg;
	}

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public int getCount() {
		return count;
	}
	public ResponseUtils<T> setCount(int count) {
		this.count = count;
		return this;
	}
    
    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static ResponseResult<String> success() { 
        return success(""); 
    }

    public static <T> ResponseResult<T> success(T data){
       return new ResponseUtils<T>().setCode(ResponseCode.Success.getCode())
				.setCount(PageView.getRowCount()).setMsg(ResponseCode.Success.getMsg())
				.setData(data);
    }

    public static ResponseResult<String> fail( ResponseCode code) { return fail(code,""); }

    public static <T> ResponseResult<T> fail( ResponseCode code , T data) {
        return new ResponseResult<T>().setCode(code.getCode()).setCount(PageView.getRowCount())
                .setMsg(code.getMsg()).setData(data);
    }
}
