package telran.multithreding.messaging;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	int limit;
	Queue<E> queue = new LinkedList<E>();
	ReentrantLock lock = new ReentrantLock();
	Condition waitingForProducing = lock.newCondition();
	Condition waitingForConsuming = lock.newCondition();
	
	public MyLinkedBlockingQueue(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean add(E e) {		
		if(queue.size() == limit) {
			throw new IllegalArgumentException();
		}				
		return offer(e);
	}

	@Override
	public boolean offer(E e) {		
		
		return queue.size() == limit ? false : queue.offer(e) ;
	}

	@Override
	public  void put(E e) throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == limit) {
				waitingForProducing.await();
			}
			add(e);
			waitingForConsuming.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		long timeOutMillis = unit.toMillis(timeout);
		if(queue.size() == limit) {
			wait(timeOutMillis);
		}
		return offer(e);
	}

	@Override
	public  E take() throws InterruptedException {
		try {
			lock.lock();
			while (queue.isEmpty()) {
				waitingForConsuming.await();
			}
			E res = poll();
			waitingForProducing.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		long timeOutMillis = unit.toMillis(timeout);
		if(queue.isEmpty()) {
			wait(timeOutMillis);
		}
		
		return poll();
	}

	@Override
	public E remove() {
		
		return queue.remove();
	}

	@Override
	public E element() {
		
		return queue.element();
	}

	@Override
	public E peek() {
		
		return queue.peek();
	}

	@Override
	public E poll() {
		
		return queue.poll();
	}

	
	

}
