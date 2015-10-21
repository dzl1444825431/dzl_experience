package com.thread;

import java.util.Collection;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.annotation.ThreadSafe;
//@ThreadSafe
//@NotThreadSafe
public class ThreadClass {

	public static void main(String[] args) {
		
		Executors e;//线程池执行者
		Executor executor;
		
		CyclicBarrier c;//屏障
		Thread thread;
		Lock lock;//锁
		
		ReentrantLock reentrantLock;//锁
		Condition condition;
		
		AtomicInteger atomicInteger;//原子
		
		ThreadLocal<?> threadLocal;//线程的局部变量
		
		Semaphore semaphore; //信号量
		
		Monitor monitor;//监视器
		
		CountDownLatch countDownLatch;//递减
		
		Exchanger<?> exchanger;
		
		ReadWriteLock readWriteLock;//读写锁
		
		Collection<?> collection;
		
		ThreadPoolExecutor threadPoolExecutor;//线程池
		
		TimerTask task;
		
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
		
		ThreadDeath threadDeath;
		
		
		
	}

}
