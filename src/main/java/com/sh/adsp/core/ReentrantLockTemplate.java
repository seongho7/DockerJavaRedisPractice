package com.sh.adsp.core;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class ReentrantLockTemplate {
  private final ReentrantLock locker = new ReentrantLock();

  public <T> T execute(Supplier<T> supplier) {
    this.locker.lock();

    try {
      return supplier.get();
    } finally {
      this.locker.unlock();
    }
  }
}
