package controller.garbage_collector;

import exception.InvalidAddressException;
import model.value.RefValue;
import state.ProgramState;

import java.util.*;

public class GarbageCollector {
    private List<Integer> getActiveAddresses(ProgramState state) {
        var activeInSymbolTable = state.getSymbolTable().values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .filter(address -> address != 0)
                .toList();
        ArrayList<Integer> activeAddresses = new ArrayList<>(activeInSymbolTable);

        // if the heap contains, at any given address in activeInSymbolTable, a reference to an address, add it as an active address as well
        for (var address : activeInSymbolTable) {
            var inHeap = state.getHeap().read(address);
            while (inHeap instanceof RefValue){
                var addr =  ((RefValue) inHeap).getAddress();
                if (addr == 0) break;
                if (!activeAddresses.contains(addr)) {
                    activeAddresses.add(addr);
                }
                inHeap = state.getHeap().read(addr);
            }
        }
        return activeAddresses;
    }
    public void runGarbageCollector(ProgramState state) {
        var heap = state.getHeap();
        List<Integer> activeAddresses = getActiveAddresses(state);

        List<Integer> inactiveAddresses = heap.getAddresses().stream().filter(address -> !activeAddresses.contains(address)).toList();
        for (var address : inactiveAddresses) {
            try{
                heap.deallocate(address);
            } catch (InvalidAddressException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
