package cs6310.Pokemon.utility;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CustomPrintStream extends PrintStream {
    private List<String> outputList;

    public CustomPrintStream(PrintStream original) {
        super(original);
        this.outputList = new ArrayList<>();
    }

    @Override
    public void println(String x) {
        outputList.add(x);
        super.println(x);
    }

    public List<String> getOutputList() {
        return outputList;
    }
}
