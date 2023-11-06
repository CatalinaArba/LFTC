import java.util.ArrayList;
import java.util.List;

public class FiniteAutomata {
    private List<String> setOfStates;
    private List<String> alphabet;
    private List<Transition> transitionsList;
    private List<String> finalStates;
    private String initialState;
    private String fileName;

    public FiniteAutomata(String initialState, String fileName) {
        this.setOfStates = new ArrayList<>();
        this.alphabet =  new ArrayList<>();
        this.transitionsList =  new ArrayList<>();
        this.finalStates =  new ArrayList<>();
        this.initialState = initialState;
        this.fileName = fileName;
    }
}
