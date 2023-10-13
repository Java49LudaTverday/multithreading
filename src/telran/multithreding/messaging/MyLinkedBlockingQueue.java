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
		try {
			lock.lock();
			if (queue.size() == limit) {
				throw new IllegalArgumentException();
			}
			boolean res = queue.add(e);
			waitingForConsuming.signal();
			return res;
		//	return offer(e);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E e) {	
		boolean res = true;
		try {
			lock.lock();
			if(queue.size() == limit){
				res = false;
			} else {
				queue.add(e);
				waitingForConsuming.signal();
			};
			return res;
			//return queue.size() == limit ? false : queue.offer(e);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public  void put(E e) throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == limit) {
				waitingForProducing.await();
			}
			queue.add(e);
			waitingForConsuming.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == limit) {
				if(!waitingForProducing.await(timeout, unit)) {
					return false;
				};
			}
			//boolean res = offer(e);
			queue.add(e);
			waitingForConsuming.signal();
			return true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public  E take() throws InterruptedException {
		try {
			lock.lock();
			while (queue.isEmpty()) {
				waitingForConsuming.await();
			}
		//	E res = poll();
			E res = queue.remove();
			waitingForProducing.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			while(queue.isEmpty()) {
				if(!waitingForConsuming.await(timeout, unit)) {
				return null;
				}
			}
			E res = queue.remove();
			//E res = poll();
			waitingForProducing.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E remove() {
		try {
			lock.lock();
			E res = queue.remove();
			waitingForProducing.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E element() {
		try {
			lock.lock();
			return queue.element();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E peek() {
		try {
			lock.lock();
			E res = queue.peek();
			if(res != null) {
				waitingForProducing.signal();
			}
			return res;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E poll() {
		try {
			lock.lock();
			E res = queue.poll();
			if(res != null) {
				waitingForProducing.signal();
			}
			return res;
		} finally {
			lock.unlock();
		}
	}

	
	

}
