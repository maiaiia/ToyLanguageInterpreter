package model.statement;

import model.adt.IDictionary;
import model.type.IType;
import state.ProgramState;

public class NoOperationStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        return typeEnvironment;
    }
}
