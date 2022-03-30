public class Question {

    private String question;
    private String[] answers;
    private int correct;
   
    public Question(String question, String[] answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

    public void ask() {
        System.out.println();
        System.out.println(question);
        
        for(int i = 0; i < answers.length; i++)
            System.out.println("  " + (char)(i + 'A') + " - " + answers[i]);
    }

    public boolean answer(String answer) {
        if(answer.charAt(0) - 'A' == correct) {
            System.out.println("Poprawna odpowiedź.");
            return true; 
        }
        System.out.println("Błędna odpowiedź.");
        return false;
    }    
}
