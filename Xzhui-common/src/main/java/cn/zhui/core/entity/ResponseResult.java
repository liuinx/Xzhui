package cn.zhui.core.entity;

import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
/**
 * ��Ӧ���
 * Created by LIN on 2018/06/06.
 */
@ApiModel(value = "��Ӧ��Ϣ")
public class ResponseResult<T> implements Serializable {
    private  static Logger logger = LoggerFactory.getLogger(ResponseResult.class);
}
