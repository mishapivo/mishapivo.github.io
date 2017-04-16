
package com.audacityit.audacity.api.response_layer.faq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Faq_ {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;

    public boolean isShowingAnswer() {
        return isShowingAnswer;
    }

    public void setShowingAnswer(boolean showingAnswer) {
        isShowingAnswer = showingAnswer;
    }

    private boolean isShowingAnswer = false;

    public Faq_(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * 
     * @return
     *     The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @return
     *     The answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 
     * @param answer
     *     The answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
