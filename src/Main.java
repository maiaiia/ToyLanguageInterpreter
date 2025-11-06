import model.adt.DynamicArrayList;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;
import model.adt.Stack;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.statement.file_statements.CloseRFileStatement;
import model.statement.file_statements.OpenRFileStatement;
import model.statement.file_statements.ReadFileStatement;
import model.type.*;
import model.value.*;
import controller.*;
import repository.*;

import state.ProgramState;
import view.*;

List<ProgramState> getHardcodedExpressionsList(){
    List<ProgramState> hardCodedProgramStates = new ArrayList<>();
    var ex1 =
            new CompoundStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );
    var ex2 = new CompoundStatement(
            new VariableDeclarationStatement("a", new IntegerType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("b", new IntegerType()),
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
            new VariableDeclarationStatement("a", new BooleanType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("v", new IntegerType()),
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
            new VariableDeclarationStatement("varf", new StringType()),
            new CompoundStatement(
                    new AssignmentStatement("varf", new ValueExpression(new StringValue("test_files/test.in"))),
                    new CompoundStatement(
                            new OpenRFileStatement(new VariableExpression("varf")),
                            new CompoundStatement(
                                    new VariableDeclarationStatement("varc", new IntegerType()),
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


    /*
    var stk1 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<>(), stk1, new DynamicArrayList<String>(), new IDictionary<>(), ex1));
    var stk2 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<>(), stk2, new DynamicArrayList<String>(), new IDictionary<>(), ex2));
    var stk3 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<>(), stk3, new DynamicArrayList<String>(), new IDictionary<>(), ex3));
*/
    var stk4 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<>(), stk4, new DynamicArrayList<>(), new HashMapDictionary<>(), ex4));

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
    //if (hardcodedData){cleanUpLogFiles();}
    return switch (hardcodedData){
        case true -> new ListRepository(getHardcodedExpressionsList(), "logs.txt");
        case false -> new ListRepository();
    };
}

void main() {
    IRepository repository = getRepository(true);
    IController controller = new Controller(repository);
    IView view = new View(controller);

    view.start();
}


/* TODO
    - each program should have its own controller
    - have a class that manages each program
    - keep the ability to either only display the initial and final program states or both
 */