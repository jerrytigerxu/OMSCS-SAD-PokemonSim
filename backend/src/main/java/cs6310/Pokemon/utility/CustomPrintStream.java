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
    public void println(String str) {
        outputList.add(str);
        super.println(str);
    }

    public List<String> getOutputList() {
        return outputList;
    }
}
