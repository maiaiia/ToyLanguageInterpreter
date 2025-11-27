package utils;

import model.expression.*;
import model.statement.*;
import model.statement.filestatements.CloseRFileStatement;
import model.statement.filestatements.OpenRFileStatement;
import model.statement.filestatements.ReadFileStatement;
import model.statement.heapstatements.AllocateHeapStatement;
import model.statement.heapstatements.WriteHeapStatement;
import model.type.BooleanType;
import model.type.IntegerType;
import model.type.RefType;
import model.type.StringType;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.StringValue;

import java.util.ArrayList;
import java.util.List;

public class HardCodedStatements {
    private final List<IStatement> statements = new ArrayList<>();
    public HardCodedStatements() {
        IStatement ex1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                );
        IStatement ex2 = new CompoundStatement(
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

        IStatement ex3 = new CompoundStatement(
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
        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("testfiles/test.in"))),
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
        int a; int b;
        a = 1; b = 2;
        Print(a < b)
         */
        IStatement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntegerType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new IntegerValue(1))),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ValueExpression(new IntegerValue(2))),
                                        new PrintStatement(new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), "<"))
                                )
                        )
                )
        );
        /*
        int v;
        v = 4;
        while (v > 0){
            print(v);
            v = v - 1;
        }
        print(v)
        */
        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(4))),
                        new CompoundStatement(
                                new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(0)), ">"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), '-')))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        /*
        Ref int v;
        new(v, 20);
        Ref Ref int a;
        new(a, v);
        print(v);
        print(a);
         */
        IStatement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                                new CompoundStatement(
                                        new AllocateHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );


        /*
        Ref int v;
        new(v, 20);
        Ref Ref int a;
        new(a, v);
        print(readHeap(v));
        print(readHeap(readHeap(a)) + 5);
         */
        IStatement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                                new CompoundStatement(
                                        new AllocateHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(
                                                        new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntegerValue(5)),
                                                        '+')
                                                )
                                        )
                                )
                        )
                )
        );

        /*
        Ref int v;
        new(v,20);
        print(readHeap(v));
        writeHeap(v, 30);
        print(readHeap(v) + 5);
         */

        var ex9 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                        new PrintStatement(new ArithmeticExpression(
                                                new ReadHeapExpression(new VariableExpression("v")),
                                                new ValueExpression(new IntegerValue(5)),
                                                '+'
                                                )
                                        )
                                )
                        )
                )
        );

        /*
        Ref int v;
        new(v, 20);
        Ref Ref int a;
        new(a, v);
        new(v, 30);
        print(rH(rH(a)));
         */

        var ex10 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                                new CompoundStatement(
                                        new AllocateHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                )
        );

        /*
        Ref int v;
        new(v, 20);
        new(v, 30);
        print(rH(v));
         */
        var ex11 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntegerType())),
                new CompoundStatement(
                        new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v")))
                        )
                )
        );


        statements.add(ex1);
        statements.add(ex2);
        statements.add(ex3);
        statements.add(ex4);
        statements.add(ex5);
        statements.add(ex6);
        statements.add(ex7);
        statements.add(ex8);
        statements.add(ex9);
        statements.add(ex10);
        statements.add(ex11);
    }

    public List<IStatement> getStatements() {
        return statements;
    }

}
