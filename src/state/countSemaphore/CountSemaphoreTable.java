package state.countSemaphore;

import exception.SemaphoreFullException;
import exception.SemaphoreNotFoundException;
import javafx.util.Pair;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CountSemaphoreTable implements ISemaphoreTable {
    private final IDictionary<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTable = new HashMapDictionary<>();
    int nextFreePosition = 1;

    @Override
    public synchronized int addSemaphore(int size) {
        semaphoreTable.add(nextFreePosition++,  new Pair<>(size, new ArrayList<>()));
        return nextFreePosition - 1;
    }

    @Override
    public synchronized void acquireSemaphore(int semaphoreId, int threadId) {
        if (! semaphoreTable.contains(semaphoreId)) {
            throw new SemaphoreNotFoundException(semaphoreId);
        }
        var semaphoreData = semaphoreTable.get(semaphoreId);
        if (semaphoreData.getValue().contains(threadId)) {
            return;
        }
        if (semaphoreData.getKey() == semaphoreData.getValue().size())
            throw new SemaphoreFullException("");
        semaphoreData.getValue().add(threadId);

    }

    @Override
    public synchronized void releaseSemaphore(int semaphoreId, int threadId) {
        if (!semaphoreTable.contains(semaphoreId)) {
            throw new SemaphoreNotFoundException(semaphoreId);
        }
        var semaphoreData = semaphoreTable.get(semaphoreId);
        if (semaphoreData.getValue().contains(threadId)) {
            semaphoreData.getValue().remove(semaphoreData.getValue().indexOf(threadId));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Integer key : semaphoreTable.keySet()){
            var p = semaphoreTable.get(key);
            var userThreads = p.getValue().stream().map(String::valueOf).collect(Collectors.joining(", "));
            result += key.toString() + " --> " + p.getKey() + " --> " + userThreads + "\n";
        }
        if (!result.isEmpty()) {result = result.substring(0, result.length()-1);}
        return result;
    }
}
