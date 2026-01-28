package utils;

import model.expression.*;
import model.statement.*;
import model.statement.fileStatements.CloseRFileStatement;
import model.statement.fileStatements.OpenRFileStatement;
import model.statement.fileStatements.ReadFileStatement;
import model.statement.heapStatements.AllocateHeapStatement;
import model.statement.ConditionalAssignmentStatement;
import model.statement.heapStatements.WriteHeapStatement;
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
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("testFiles/test.in"))),
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
        /*
        int v ;
        ref int a;
        v = 10;
        new(a, 22);
        fork(writeHeap(a,30); v = 32; prnt(v); print(readHeap(a)));
        print(v);
        print(readHeap(a));
         */

        var ex12 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
                                new CompoundStatement(
                                        new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                            new WriteHeapStatement("a", new ValueExpression(new IntegerValue(30))),
                                                            new CompoundStatement(
                                                                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(32))),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VariableExpression("v")),
                                                                            new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                    )
                                                            )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        /*
        int v;
        fork(v = 2; v = v + 2; print(v);)
        fork(v = -1; print(v);)
        v = 3;
        print(v);
         */

        var ex13 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new ForkStatement(new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                new CompoundStatement(new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(2)), '+')),
                                        new PrintStatement(new VariableExpression("v"))))),
                        new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(-1))), new PrintStatement(new VariableExpression("v")))),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(3))), new PrintStatement(new VariableExpression("v")))
                        )
                )
        );

        /*
        Ref int a; Ref int b; int v;
        new(a,0); new(b,0);
        wh(a, 1); wh(b, 2);
        v=(rh(a) < rh(b))>100:200;
        print(v)
        v=((rh(b)-2)>rh(a))?100:200;
        print(v);
        // output should be 100 200
        */
        var ex14 = new CompoundStatement(
                new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new RefType(new IntegerType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v", new IntegerType()),
                                new CompoundStatement(
                                        new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(0))),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("b", new ValueExpression(new IntegerValue(0))),
                                                new CompoundStatement(
                                                        new WriteHeapStatement("a", new ValueExpression(new IntegerValue(1))),
                                                        new CompoundStatement(
                                                                new WriteHeapStatement("b", new ValueExpression(new IntegerValue(2))),
                                                                new CompoundStatement(
                                                                    new ConditionalAssignmentStatement(
                                                                            "v",
                                                                            new RelationalExpression(
                                                                                    new ReadHeapExpression(new VariableExpression("a")),
                                                                                    new ReadHeapExpression(new VariableExpression("b")),
                                                                                    "<"
                                                                            ),
                                                                            new ValueExpression(new IntegerValue(100)),
                                                                            new ValueExpression(new IntegerValue(200))
                                                                    ),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VariableExpression("v")),
                                                                            new CompoundStatement(
                                                                                    new ConditionalAssignmentStatement(
                                                                                            "v",
                                                                                            new RelationalExpression(
                                                                                                    new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("b")), new ValueExpression(new IntegerValue(2)), '-'),
                                                                                                    new ReadHeapExpression(new VariableExpression("a")),
                                                                                                    ">"
                                                                                            ),
                                                                                            new ValueExpression(new IntegerValue(100)),
                                                                                            new ValueExpression(new IntegerValue(200))
                                                                                    ),
                                                                                    new PrintStatement(new VariableExpression("v"))
                                                                            )
                                                                    )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        /*
        int a; int b; int c;
        a = 1; b = 2; c = 5;
        switch(a*10)
            (case (b * c) : print(a); print(b))
            (case (10) : print(100); print(200))
            (default : print(300))
        print(300);
         */
        var ex15 = new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new IntegerType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("b", new IntegerType()),
                                new VariableDeclarationStatement("c", new IntegerType())
                        )
                ),
                new CompoundStatement(
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new IntegerValue(1))),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ValueExpression(new IntegerValue(2))),
                                        new AssignmentStatement("c", new ValueExpression(new IntegerValue(5)))
                                )
                        ),
                        new CompoundStatement(
                                new SwitchStatement(
                                        new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntegerValue(10)), '*'),
                                        new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), '*'),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))),
                                        new ValueExpression(new IntegerValue(10)),
                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntegerValue(100))), new PrintStatement(new ValueExpression(new IntegerValue(200)))),
                                        new PrintStatement(new ValueExpression(new IntegerValue(300)))
                                ),
                                new PrintStatement(new ValueExpression(new IntegerValue(300)))
                        )
                )

        );
        /*
        Ref int a; new(a, 20);
        (for (v = 0; v < 3; v = v + 1) fork(print(v); v = v * rh(a));
        print(rh(a))
         */
        var ex16 = new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                        new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(20)))
                ),
                new CompoundStatement(
                        new ForStatement(
                                new ValueExpression(new IntegerValue(0)),
                                new ValueExpression(new IntegerValue(3)),
                                new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), '+'),
                                new ForkStatement(
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                new VariableExpression("v"),
                                                                new ReadHeapExpression(new VariableExpression("a")),
                                                                '*'
                                                        )
                                                )
                                        )
                                )
                        ),
                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                )
        );

        /*
        int v; int x; int y; v = 0;
        (repeat (fork(print(v);v=v-1);v=v+1) until v==3)
        x1 = 1; nop; y = 3; nop;
        print(v*10);
         */
        var ex17 = new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("x", new IntegerType()),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("y", new IntegerType()),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(0)))
                                )
                        )
                ),
                new CompoundStatement(
                        new RepeatUntilStatement(
                                new CompoundStatement(
                                        new ForkStatement(
                                                new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new AssignmentStatement(
                                                                        "v",
                                                                        new ArithmeticExpression(new VariableExpression("v"),
                                                                                new ValueExpression(new IntegerValue(1)),
                                                                                '-')
                                                                )
                                                        )
                                                ),
                                        new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), '+'))
                                ),
                                new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(3)), "==")
                        ),
                        new CompoundStatement(
                                new CompoundStatement(
                                        new AssignmentStatement("x", new ValueExpression(new IntegerValue(1))),
                                        new CompoundStatement(
                                                new NoOperationStatement(),
                                                new CompoundStatement(
                                                        new AssignmentStatement("y", new ValueExpression(new IntegerValue(3))),
                                                        new NoOperationStatement()
                                                )
                                        )
                                ),
                                new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(10)),'*'))
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
        statements.add(ex12);
        statements.add(ex13);
        statements.add(ex14);
        statements.add(ex15);
        statements.add(ex16);
        statements.add(ex17);
    }

    public List<IStatement> getStatements() {
        return statements;
    }

}
