package state.barrierTable;

import exception.BarrierDoesNotExistException;
import exception.BarrierFullException;
import javafx.util.Pair;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BarrierTable implements IBarrierTable {
    private final IDictionary<Integer, Pair<Integer, ArrayList<Integer>>> barriers = new HashMapDictionary<>();
    private int firstFreePosition = 1;

    @Override
    public synchronized int addBarrier(Integer barrierSize) {
        barriers.add(firstFreePosition, new Pair<>(barrierSize, new ArrayList<>()));
        return firstFreePosition++;
    }

    @Override
    public synchronized void awaitBarrier(Integer barrierId, int programId) {
        if (!barriers.contains(barrierId)) {
            throw new BarrierDoesNotExistException(barrierId);
        }
        var data = barriers.get(barrierId);
        if (data.getKey() == data.getValue().size())
            throw new BarrierFullException("");
        if (!data.getValue().contains(programId)) {
            data.getValue().add(programId);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Integer key : barriers.keySet()){
            var p = barriers.get(key);
            var userThreads = p.getValue().stream().map(String::valueOf).collect(Collectors.joining(", "));
            result += key.toString() + " --> " + p.getKey() + " --> " + userThreads + "\n";
        }
        if (!result.isEmpty()) {result = result.substring(0, result.length()-1);}
        return result;
    }
}
