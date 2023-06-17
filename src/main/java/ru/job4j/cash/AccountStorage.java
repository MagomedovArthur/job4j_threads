package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) != null;
    }

    public synchronized boolean update(Account account) {
        return accounts.get(account.id()) != null
                && accounts.put(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.get(id) != null && accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> result = Optional.empty();
        for (Integer accountId : accounts.keySet()) {
            if (id == accountId) {
                result = Optional.of(accounts.get(accountId));
            }
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var result = false;
        if (accounts.get(fromId) != null && accounts.get(toId) != null
                && accounts.get(fromId).amount() >= amount) {
            update(new Account(fromId, accounts.get(fromId).amount() - amount));
            update(new Account(toId, accounts.get(toId).amount() + amount));
            result = true;
        }
        return result;
    }
}