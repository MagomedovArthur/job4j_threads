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
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.get(account.id()) != null
                && accounts.put(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.get(id) != null && accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var result = false;
        Optional<Account> accountFrom = getById(fromId);
        Optional<Account> accountTo = getById(toId);
        if (accountFrom.isPresent() && accountTo.isPresent()
                && accountFrom.get().amount() >= amount) {
            update(new Account(fromId, accountFrom.get().amount() - amount));
            update(new Account(toId, accountTo.get().amount() + amount));
            result = true;
        }
        return result;
    }
}