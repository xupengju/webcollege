package com.college.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户资源
 * Created by pengzhan.rong on 2016/11/4.
 */
public class ResourceModel implements Serializable {

    private Integer id;
    // 节点标题
    private String title;
    // 节点类型 folder 或 item
    private String type;
    // 附加数据
    private Map<String,Object> attr;
    // 排序
    private Integer sequence;
    // 子集
    private List<ResourceModel> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<ResourceModel> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceModel> children) {
        this.children = children;
    }
}
