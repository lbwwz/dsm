package com.dsm.model.user;

/**
 * 系统设定的默认的安全问题
 * 由管理员进行填写操作
 *
 * @author lbwwz
 */
public class SecurityQuestion {
    private int inquesId;
    private String questionDesc;
    private int sort;
    private String addtime;
    private int status;

    public SecurityQuestion() {
    }

    public int getInquesId() {
        return inquesId;
    }

    public void setInquesId(int inquesId) {
        this.inquesId = inquesId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecurityQusetion [inquesId=" + inquesId + ", questionDesc=" + questionDesc + ", sort=" + sort
                + ", addtime=" + addtime + ", status=" + status + "]";
    }


}
