import model.adt.DynamicArrayList;
import model.adt.HashMapDictionary;
import model.adt.Stack;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.value.*;
import controller.*;
import repository.*;
import programState.HashMapFileTable;
import programState.ProgramState;
import view.*;

//TODO - intreaba execution stack

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



    var stk1 = new Stack<IStatement>();
    stk1.push(ex1);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk1, new DynamicArrayList<String>(), new HashMapFileTable()));
    var stk2 = new Stack<IStatement>();
    stk2.push(ex2);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk2, new DynamicArrayList<String>(), new HashMapFileTable()));
    var stk3 = new Stack<IStatement>();
    stk3.push(ex3);
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk3, new DynamicArrayList<String>(), new HashMapFileTable()));

    return hardCodedProgramStates;
}

IRepository getRepository(boolean hardcodedData){
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
