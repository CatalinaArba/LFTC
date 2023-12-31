import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {
    private List<String> setOfStates;
    private List<String> alphabet;
    private List<Transition> transitionsList;
    private List<String> finalStates;
    private String initialState;
    private String fileName;

    public FiniteAutomata(String fileName) {
        this.setOfStates = new ArrayList<>();
        this.alphabet =  new ArrayList<>();
        this.transitionsList =  new ArrayList<>();
        this.finalStates =  new ArrayList<>();
        this.initialState =  "";
        this.fileName = fileName;
    }

    public void readFromFile() {
        File file = new File(this.fileName);
        try {
            Scanner scanner = new Scanner(file);
            // Set of states
            String setOfStatesText = scanner.nextLine();
            String setOfStates = scanner.nextLine();
            this.setOfStates = Arrays.asList(setOfStates.split(","));

            // Alphabet
            String alphabetText = scanner.nextLine();
            String alphabet = scanner.nextLine();
            this.alphabet = Arrays.asList(alphabet.split(","));

            // Transitions
            String transitionsText = scanner.nextLine();
            String transition = "";

            //  As long as we have transitions, we should read them
            while (true) {
                transition = scanner.nextLine();
                if (transition.equals("FINAL STATES")) {
                    break;
                }

                List<String> transitions = Arrays.asList(transition.split(","));
                Transition t=this.findTransition(transitions.get(0), transitions.get(1));
                if(t!=null){
                    this.addFinalState(t,transitions.get(2));
                }else{
                    Transition model = new Transition();
                    model.setStartState(transitions.get(0));
                    model.setValue(transitions.get(1));
                    List<String> endStates = new ArrayList<>();
                    for (int i = 2; i < transitions.size(); i++) {
                        endStates.add(transitions.get(i));
                    }
                    model.setEndState(endStates);

                    this.transitionsList.add(model);
                }

            }

            // Final states
            String finalStates = scanner.nextLine();
            this.finalStates = Arrays.asList(finalStates.split(","));

            // Initial state
            String initialState = scanner.nextLine();
            this.initialState = scanner.nextLine();

            scanner.close();
        }
        catch (FileNotFoundException exception){
            System.out.println(exception);
            return;
        }


    }

    public List<String> getSetOfStates() {
        return setOfStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitionsList() {
        return transitionsList;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public String getInitialState() { return initialState; }

    public boolean isDFA() {
        for (Transition transition: transitionsList) {
            if (transition.getEndState().size() > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isAccepted(String seq) {
        String currentState = this.initialState;
        String[] sequence = seq.split("");
        for (String character : sequence) {
            String nextState = nextState(currentState, character);

            System.out.println(currentState + " " + character + " " + nextState);

            // Case: no state
            if (nextState.equals("no-state-found")) return false;

            currentState = nextState;
        }
        // Case: final state
        return this.finalStates.contains(currentState);
    }

    private String nextState(String startState, String value) {
        for (Transition transition: transitionsList) {
            if (transition.getStartState().equals(startState) && transition.getValue().equals(value))
                if (transition.getEndState().size() == 1)
                    return transition.getEndState().get(0);
        }
        return "no-state-found";
    }

    private Transition findTransition(String startState, String value){
        for (Transition transition:transitionsList ){
            if (Objects.equals(transition.getStartState(), startState) && Objects.equals(transition.getValue(), value))
                return transition;
        }
        return null;
    }

    private void addFinalState(Transition transition, String newFinalState){
        for(String finalState:transition.getEndState()){
            if(Objects.equals(finalState, newFinalState))
                return;
        }
        transition.getEndState().add(newFinalState);
    }

}
