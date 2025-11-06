import model.adt.DynamicArrayList;
import model.adt.HashMapDictionary;
import model.adt.Stack;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BooleanType;
import model.type.IntegerType;
import model.value.*;
import controller.*;
import repository.*;
import state.ProgramState;
import view.*;

List<ProgramState> getHardcodedExpressionsList(){
    List<ProgramState> hardCodedProgramStates = new ArrayList<>();
    var ex1 = new CompoundStatement(
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



    var stk1 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk1, new DynamicArrayList<String>(), ex1));
    var stk2 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk2, new DynamicArrayList<String>(), ex2));
    var stk3 = new Stack<IStatement>();
    hardCodedProgramStates.addLast(new ProgramState(new HashMapDictionary<String, IValue>(), stk3, new DynamicArrayList<String>(), ex3));

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
