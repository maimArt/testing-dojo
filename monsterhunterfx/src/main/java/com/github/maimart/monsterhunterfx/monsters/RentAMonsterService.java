package com.github.maimart.monsterhunterfx.monsters;

import java.util.concurrent.CompletableFuture;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RentAMonsterService extends Service<Monster> implements MonsterService {
    @Override
    public CompletableFuture<Monster> rentAMonster() {
        CompletableFuture<Monster> future = new CompletableFuture<>();
        setOnSucceeded(event -> future.complete(getValue()));
        restart();
        return future;
    }

    @Override
    protected Task<Monster> createTask() {
        return new CreateANewMonsterTask();
    }

    private static class CreateANewMonsterTask extends Task<Monster> {
        @Override
        protected Monster call() {
            return new Monster();
        }
    }
}
