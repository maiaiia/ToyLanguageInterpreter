package model.statement;

import model.adt.IDictionary;
import model.type.IType;
import state.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState programState);
    String toString();
    IStatement deepCopy();
    IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment);
}
