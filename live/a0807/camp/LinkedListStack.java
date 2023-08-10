package a0807.camp;

import java.util.EmptyStackException;

public class LinkedListStack<E> implements IStack<E> {
	
	private Node<E> top = null;
	

	//첫 노드로 삽입
	@Override
	public void push(E e) {
		top = new Node<>(e, top);
	}

	//첫노드 삭제 후 반환
	@Override
	public E pop() {
		if(isEmpty())
			throw new EmptyStackException();
		Node<E> popNode = top;
		top = popNode.getLink();
		popNode.setLink(null);
		return popNode.getData();
	}

	//첫노드 값 반환
	@Override
	public E peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return top.getData();
	}

	@Override
	public int size() {
		int size = 0;
		
		for(Node<E> temp=top;temp!=null;temp=temp.getLink()) size++;	
		return size;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}
}
