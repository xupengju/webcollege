package com.college.exception;

import com.alibaba.fastjson.JSONObject;
import com.college.contants.AppCode;
import com.college.contants.Constants;
import com.college.model.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by fechin on 15/5/4.
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    private Boolean alarm;
    private String project;


    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        logger.debug("exception wechat alarm: {}", alarm);
        try {
            // 获取请求地址
            StringBuffer sbUrl = new StringBuffer();
            if (!(ex instanceof MaxUploadSizeExceededException)) {
                sbUrl.append(request.getRequestURL().toString());
                Enumeration en = request.getParameterNames();
                for (int i = 0; en.hasMoreElements(); i++) {
                    String arg = en.nextElement().toString();
                    if (i == 0) {
                        sbUrl.append("?");
                    } else {
                        sbUrl.append("&");
                    }
                    sbUrl.append(arg + "=" + request.getParameterValues(arg)[0]);
                }
            }
            // 根据不同错误实例化响应Body
            String errorMsg = "";
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                errorMsg += element + ", ";
                if (errorMsg.length() > 300) {
                    break;
                }
            }

            AppCode code;
            Object[] args = null;
            if (ex instanceof CollegeException) {
                CollegeException exception = (CollegeException) ex;
                args = exception.getArgs();
                code = exception.getCode() == null ? AppCode._900 : exception.getCode();

                // 发送微信警告 TODO
                if (alarm && exception.getAlarm()) {
                    String msg = String.format("[%s]%s:%s", project, code.getMsg(), errorMsg);
                    sendWechatMessage(3, msg);
                }
            } else {
                code = AppCode._900;

                // 发送微信警告 TODO
                if (alarm) {
                    String msg = String.format("[%s]%s:%s", project, code.getMsg(), errorMsg);
                    sendWechatMessage(2, msg);
                }

                // 打印异常信息
                logger.error("request url: " + sbUrl.toString());
                logger.error("Catch Exception: ", ex);
            }

            Resp resp = new Resp(code, args, ex.getMessage());

            // 输出结果
            response.setContentType(Constants.CONTENT_TYPE_JSON);
            String jsonResult = JSONObject.toJSONString(resp);
            logger.info("request url: " + sbUrl.toString());
            logger.info(jsonResult); // 打印错误码输出异常
            response.getWriter().write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }

    public void sendWechatMessage(final int level, final String content) {
    }


    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
