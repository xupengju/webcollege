package com.college.exception;

import com.college.contants.AppCode;

/**
 * @author Milo on 2018/3/24.
 * @description
 */
public class CollegeException extends RuntimeException  {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6417641452178955756L;

    private AppCode code;
    private Boolean alarm = false;
    private Object[] args;

    public CollegeException() {
        super();
    }

    public CollegeException(String message) {
        super(message);
    }

    @Deprecated
    public CollegeException(AppCode code, String message) {
        super(message);
        this.code = code;
    }

    public CollegeException(AppCode code, Object[] args){
        this.code = code;
        this.args = args;
    }

    public CollegeException(AppCode code) {
        this.code = code;
    }

    public CollegeException(AppCode code, Boolean alarm) {
        this.code = code;
        this.alarm = alarm;
    }

    public CollegeException(Throwable cause) {
        super(cause);
    }

    public CollegeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppCode getCode() {
        return code;
    }

    public void setCode(AppCode code) {
        this.code = code;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
