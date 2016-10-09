package com.hcl.onlineexammobileapp.dto;

public class ExamData {
	
	String qno;
    String ques;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    String rightans;
    String category;

    public ExamData() {
    }

    public ExamData(String qno, String ques, String ans1, String ans2, String ans3, String ans4, String rightans, String category) {
        this.qno = qno;
        this.ques = ques;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.rightans = rightans;
        this.category = category;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getRightans() {
        return rightans;
    }

    public void setRightans(String rightans) {
        this.rightans = rightans;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
    
    	return ques;
    }

}
