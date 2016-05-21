package com.liujun.thread.lock.spinlock.clhlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 
 * CLHlock是不停的查询前驱变量， 导致不适合在NUMA 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
 * 
 * @author liujun
 * 
 */
public enum CLHLock {

	Instance();

	private CLHLock() {

	}

	public static class CLHNode {
		private volatile boolean isLocked = true;
	}

	@SuppressWarnings("unused")
	private volatile CLHNode tail;

	private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHLock.CLHNode>();

	private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATE = AtomicReferenceFieldUpdater
			.newUpdater(CLHLock.class, CLHNode.class, "tail");

	public void lock() {
		CLHNode node = new CLHNode();
		LOCAL.set(node);
		CLHNode preNode = UPDATE.getAndSet(this, node);
		if (preNode != null) {
			while (preNode.isLocked) {

			}
			preNode = null;
			LOCAL.set(node);
		}
	}

	public void unlock() {
		CLHNode node = LOCAL.get();
		if (!UPDATE.compareAndSet(this, node, null)) {
			node.isLocked = false;
		}
		node = null;
	}
}
