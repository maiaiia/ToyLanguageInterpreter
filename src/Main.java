import model.adt.DynamicArrayList;
import model.adt.HashMapDictionary;
import model.adt.Stack;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.statement.file_statements.CloseRFileStatement;
import model.statement.file_statements.OpenRFileStatement;
import model.statement.file_statements.ReadFileStatement;
import model.value.*;
import controller.*;
import repository.*;
import model.adt.HashMapFileTable;
import programState.ProgramState;
import view.*;

//TODO - close doesn't work

List<ProgramState> getHardcodedExpressionsList(){
    List<ProgramState> hardCodedProgramStates = new ArrayList<>();
    var ex1 =
            new CompoundStatement(
            new VariableDeclarationStatement("v", Type.INTEGER),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );
    var ex2 = new CompoundStatement(
            new VariableDeclarationStatement("a", Type.INTEGER),
            new CompoundStatement(
                    new VariableDeclarationStatement("b", Type.INTEGER),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    "a",
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(2)),
                                            new ArithmeticExpression(
                                                    new ValueExpression(new IntegerValue(3)),
                                                    new ValueExpression(new IntegerValue(5)),
                                                    '*'
                                            ),
                                            '+'
                                    )
                            ),
                            new CompoundStatement(
                                    new AssignmentStatement(
                                            "b",
                                            new ArithmeticExpression(
                                                    new VariableExpression("a"),
                                                    new ValueExpression(new IntegerValue(1)),
                                                    '+'
                                            )
                                    ),
                                    new PrintStatement(new VariableExpression("b"))
                            )
                    )
            )
    );
    var ex3 = new CompoundStatement(
            new VariableDeclarationStatement("a", Type.BOOLEAN),
            new CompoundStatement(
                    new VariableDeclarationStatement("v", Type.INTEGER),
                    new CompoundStatement(
                            new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                            new CompoundStatement(
                                    new IfStatement(
                                            new VariableExpression("a"),
                                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))
                                    ),
                                    new PrintStatement(new VariableExpression("v"))
                            )
                    )
            )
    );
    var ex4 = new CompoundStatement(
            new VariableDeclarationStatement("varf", Type.STRING),
            new CompoundStatement(
                    new AssignmentStatement("varf", new ValueExpression(new StringValue("test_files/test.in"))),
                    new CompoundStatement(
                            new OpenRFileStatement(new VariableExpression("varf")),
                            new CompoundStatement(
                                    new VariableDeclarationStatement("varc", Type.INTEGER),
                                    new CompoundStatement(
                                            new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                            new CompoundStatement(
                                                    new PrintStatement(new VariableExpression("varc")),
                                                    new CompoundStatement(
                                                            new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                            new CompoundStatement(
                                                                    new PrintStatement(new VariableExpression("varc")),
                                                                    new CloseRFileStatement(new VariableExpression("varf"))
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );


    var stk1 = new Stack<IStatement>();
    stk1.push(ex1);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk1, new DynamicArrayList<String>(), new HashMapFileTable(), "log_ex1.txt"));
    var stk2 = new Stack<IStatement>();
    stk2.push(ex2);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk2, new DynamicArrayList<String>(), new HashMapFileTable(), "log_ex2.txt"));
    var stk3 = new Stack<IStatement>();
    stk3.push(ex3);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk3, new DynamicArrayList<String>(), new HashMapFileTable(), "log_ex3.txt"));

    var stk4 = new Stack<IStatement>();
    stk4.push(ex4);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<>(), stk4, new DynamicArrayList<>(), new HashMapFileTable(), "log_ex4.txt"));

    return hardCodedProgramStates;
}

void cleanUpLogFiles(){
    String FILES_DIRECTORY = "log_files/";
    String[] logFiles = new String[]{"log_ex1.txt", "log_ex2.txt", "log_ex3.txt", "log_ex4.txt"};
    for(String logFile : logFiles){
        // clean up files
        PrintWriter file;
        try{
            file = new PrintWriter(new BufferedWriter(new FileWriter(FILES_DIRECTORY + logFile, false)));
            file.println();
        } catch (IOException _) {}
    }
}

IRepository getRepository(boolean hardcodedData){
    if (hardcodedData){cleanUpLogFiles();}
    return switch (hardcodedData){
        case true -> new ListRepository(getHardcodedExpressionsList());
        case false -> new ListRepository();
    };
}

void main() {
    IRepository repository = getRepository(true);
    IController controller = new Controller(repository);
    IView view = new View(controller);

    view.start();
}
