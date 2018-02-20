package com.github.maimart.monsterhunterfx.monsters;

import java.util.concurrent.CompletableFuture;

public interface MonsterService {

    CompletableFuture<Monster> rentAMonster();
}
