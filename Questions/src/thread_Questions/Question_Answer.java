package thread_Questions;

import java.io.Serializable;

public class Question_Answer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String question;
	private String firstAnswer;
	private String secondAnswer;
	private String thirdAnswer;
	private String forthAnswer;
	private int trueAnswer;
	
	public Question_Answer(String question, String firstAnswer,
			String secondAnswer, String thirdAnswer, String forthAnswer,
			int trueAnswer) {
		setQuestion(question);
		setFirstAnswer(firstAnswer);
		setSecondAnswer(secondAnswer);
		setThirdAnswer(thirdAnswer);
		setForthAnswer(forthAnswer);
		setTrueAnswer(trueAnswer);
	}
        
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getFirstAnswer() {
		return firstAnswer;
	}
	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}
	public String getSecondAnswer() {
		return secondAnswer;
	}
	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}
	public String getThirdAnswer() {
		return thirdAnswer;
	}
	public void setThirdAnswer(String thirdAnswer) {
		this.thirdAnswer = thirdAnswer;
	}
	public String getForthAnswer() {
		return forthAnswer;
	}
	public void setForthAnswer(String forthAnswer) {
		this.forthAnswer = forthAnswer;
	}
	public int getTrueAnswer() {
		return trueAnswer;
	}
	public void setTrueAnswer(int trueAnswer) {
		this.trueAnswer = trueAnswer;
	}

}
