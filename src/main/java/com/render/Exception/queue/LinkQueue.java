package com.render.exception.queue;

import java.io.Serializable;

/**
 * @author linzukon
 * 
 * 作用：队列
 * 说明：因为是链表结构的队列，所以队列长度自增长，不用考虑满队列
 * 
 * */

public class LinkQueue<T> implements Serializable { // 考虑有可能将队列序列化保存，所以implements Serializable
	private static final long serialVersionUID = 1L;
	// 定义一个内部Node，Node对象代表链队列的节点
	private class Node {
		// 数据
		private T data;
		// 指向下一个节点的引用
		private Node next;
		// 无参构造函数
		//public Node() {}
		// 初始化全部属性的构造函数
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	// 保存该链队列的头节点
	private Node front;
	// 保存该链队列的尾节点
	private Node rear;
	// 保存队列中已包含的节点数
	private int size;
	
	
	// 创建空队列
	public LinkQueue() {
		this.front = null;
		this.rear = null;
	}
	// 以指定的数据元素来创建队列，该链队列只有一个元素
	public LinkQueue(T element) {
		this.front = new Node(element, null);
		this.rear = this.front;
		size++;
	}
	
	
	// 返回队列的长度
	public int length() {
		return this.size;
	}
	// 将新元素加入队列
	public synchronized void add(T element) {
		// 如果该链队列还是空链队列
		if (front == null) {
			this.front = new Node(element, null);
			this.rear = this.front;
		} else {
			// 创建新节点
			Node newNode = new Node(element, null);
			// 让尾节点的next指向新增节点
			this.rear.next = newNode;
			// 以新节点作为尾节点
			this.rear = newNode;
		}
		size++;
	}
	// 删除队列front端元素
	public synchronized T remove() {
		if (isEmpty()) {
			return null;
		} else {
			Node oldFront = this.front;
			this.front = this.front.next;
			oldFront.next = null;
			size--;
			return oldFront.data;
		}
	}
	// 访问链队列最后一个元素
	public T element() {
		return rear.data;
	}
	// 判断链队列是否是空队列
	public boolean isEmpty() {
		return size == 0;
	}
	// 清空链队列
	public synchronized void clear() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}
	public String toString() {
		if (isEmpty()) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder("[");
			for (Node current = this.front; current != null; current = current.next) {
				sb.append(current.data.toString() + ",");
			}
			int len = sb.length();
			return sb.delete(len-1, len).append("]").toString();
		}
	}
}
