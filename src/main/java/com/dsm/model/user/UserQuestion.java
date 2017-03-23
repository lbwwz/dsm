package com.dsm.model.user;

/**
 * 用户的安全问题
 *
 * @author Think
 */
public class UserQuestion {

    private int quesItemId;

    private int userId;

    private String questionDesc;

    private String answer;

    private String addTime;

    public UserQuestion() {
    }

    public int getQuesItemId() {
        return quesItemId;
    }

    public void setQuesItemId(int quesItemId) {
        this.quesItemId = quesItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "UserQuestion [quesItemId=" + quesItemId + ", userId=" + userId + ", questionDesc=" + questionDesc
                + ", answer=" + answer + ", addTime=" + addTime + "]";
    }


}
