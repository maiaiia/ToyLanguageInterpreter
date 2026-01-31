package state.barrierTable;

import model.expression.IExpression;

public interface IBarrierTable {
    int addBarrier(Integer barrierSize);
    void awaitBarrier(Integer barrierId, int programId);
}
