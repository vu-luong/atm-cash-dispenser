package com.vuluong.atmcashdispenser.service;

import com.vuluong.atmcashdispenser.repo.CashRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.vuluong.atmcashdispenser.constant.Constants.VALUE_BY_CASH_AMOUNT_KEY;

@Service
@AllArgsConstructor
public class CashService {

    private final CashRepository cashRepository;
    private Map<Integer, Integer> bestPath;

    public boolean isInitialized() {
        return cashRepository.isInitialized();
    }

    public void initialize(Map<String, Integer> amountByCash) {
        amountByCash.forEach(cashRepository::setAmount);
        cashRepository.setInitialized(true);
    }

    public Map<String, Integer> getAmountByCash() {
        return cashRepository.getAmountByCash();
    }


    public void updateCashAmount(Map<String, Integer> amountByCash) {
        amountByCash.forEach(cashRepository::addAmount);
    }

    /**
     * Performs main algorithm to dispense cash
     *
     * @param dispensedAmount
     *     Amount of cash needs to be dispensed
     * @return a map from cash values to their counts, which sum up to dispensed amount
     */
    public Map<String, Integer> dispense(int dispensedAmount) {

        // Obtain a positive sorted list of entries from the map in cashRepository
        List<Entry<String, Integer>> cashList = cashRepository.getPositiveSortedList();

        // Initialize savedPath and bestPath
        Map<Integer, Integer> savedPath = new HashMap<>();
        bestPath = new HashMap<>();

        // DFS to find combinations that satisfy the dispensedAmount
        dfs(cashList, 0, dispensedAmount, savedPath);

        // Acquire result from bestPath and update database
        Map<String, Integer> result = new HashMap<>();
        bestPath.forEach((cashIndex, count) -> {
            String cashKey = cashList.get(cashIndex).getKey();
            result.put(cashKey, count);
            cashRepository.subtractAmount(cashKey, count);
        });

        return result;
    }

    private void dfs(
        List<Entry<String, Integer>> cashList,
        int cashIndex,
        double dispensedAmount,
        Map<Integer, Integer> currentPath
    ) {
        String currentCashKey = cashList.get(cashIndex).getKey();
        double currentCashValue = VALUE_BY_CASH_AMOUNT_KEY.get(currentCashKey);
        int currentCashAmount = cashList.get(cashIndex).getValue();

        // stopping condition at a leaf node
        if (cashIndex == cashList.size() - 1) {
            int count = (int) (dispensedAmount / currentCashValue);
            if ((count * currentCashValue == dispensedAmount) && (count <= currentCashAmount)) {
                currentPath.put(cashIndex, count);
                evaluateSolution(currentPath, cashList);
            }
            return;
        }

        int maxCount = Math.min(
            currentCashAmount,
            (int) (dispensedAmount / currentCashValue)
        );

        for (int count = 0; count <= maxCount; count++) {
            currentPath.put(cashIndex, count);
            dfs(
                cashList,
                cashIndex + 1,
                dispensedAmount - count * currentCashValue,
                currentPath
            );
        }
    }

    private void evaluateSolution(
        Map<Integer, Integer> currentPath,
        List<Entry<String, Integer>> cashList
    ) {
        if (bestPath.isEmpty()) {
            for (int i = 0; i < cashList.size(); i++) {
                bestPath.put(i, currentPath.get(i));
            }
            return;
        }

        // min count if following current path and best path
        int lowestAmountInCurrentPath = Integer.MAX_VALUE;
        int lowestAmountInBestPath = Integer.MAX_VALUE;

        for (int i = 0; i < cashList.size(); i++) {
            lowestAmountInCurrentPath = Math.min(
                lowestAmountInCurrentPath,
                cashList.get(i).getValue() - currentPath.get(i)
            );

            lowestAmountInBestPath = Math.min(
                lowestAmountInBestPath,
                cashList.get(i).getValue() - bestPath.get(i)
            );
        }

        // find the maximum of the lowest one among remaining amounts
        // to prioritize the combinations of cash that leave options open
        if (lowestAmountInCurrentPath > lowestAmountInBestPath) {
            for (int i = 0; i < cashList.size(); i++) {
                bestPath.put(i, currentPath.get(i));
            }
        }
    }
}
