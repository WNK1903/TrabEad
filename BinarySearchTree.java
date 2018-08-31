package bstPackage;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {
	protected Node root;

	protected class Node {
		private K key;
		private V value;
		private Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Node next(K other) {
			return other.compareTo(key) < 0 ? left : right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public String toString() {
			return "" + key;
		}
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	public V search(K key) {
		return search(root, key);
	}

	private V search(Node node, K key) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node.value;
		}
		return search(node.next(key), key);
	}

	// Search que retorna o nó ao invés de o valor da chave.
	public Node searchN(K key) {
		return searchN(key, root);
	}

	private Node searchN(K key, Node node) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node;
		}
		return searchN(key, node.next(key));

	}

	@Override
	public void insert(K key, V value) {
		root = insert(root, key, value);
	}

	private Node insert(Node node, K key, V value) {
		if (node == null) {
			return new Node(key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = insert(node.right, key, value);
		} else if (key.compareTo(node.key) < 0) {
			node.left = insert(node.left, key, value);
		}

		return node;
	}

	@Override
	public String toString() {
		return root == null ? "[empty]" : printTree(new StringBuffer());
	}

	private String printTree(StringBuffer sb) {
		if (root.right != null) {
			printTree(root.right, true, sb, "");
		}
		sb.append(root + "\n");
		if (root.left != null) {
			printTree(root.left, false, sb, "");
		}

		return sb.toString();
	}

	private void printTree(Node node, boolean isRight, StringBuffer sb, String indent) {
		if (node.right != null) {
			printTree(node.right, true, sb, indent + (isRight ? "        " : " |      "));
		}
		sb.append(indent + (isRight ? " /" : " \\") + "----- " + node + "\n");
		if (node.left != null) {
			printTree(node.left, false, sb, indent + (isRight ? " |      " : "        "));
		}
	}

	private boolean deleteByCopying(K key) {
		Node parent = null, current = root;
		for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key))
			;

		if (current == null)
			return false;
		else if (current.left != null && current.right != null) {
			// Caso 3
			Node tmp = current.left;
			while (tmp.right != null)
				tmp = tmp.right;
			deleteByCopying(tmp.key);
			current.key = tmp.key;
		} else {
			// Caso 1 ou Caso 2
			Node nextNode = current.right == null ? current.left : current.right;
			if (current.equals(root))
				root = nextNode;
			else if (parent.left.equals(current))
				parent.left = nextNode;
			else
				parent.right = nextNode;
		}

		return true;
	}

	public boolean deleteByCopying1(K key) {
		return deleteByCopying(key);
	}

	private boolean deleteByMerging(K key) {
		Node parent = null, current = root;
		for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key))
			;

		if (current == null)
			return false;
		else if (current.left != null && current.right != null) {
			// Caso 3
			Node tmp = current.left;
			while (tmp.right != null)
				tmp = tmp.right;
			tmp.right = current.right;

			if (current.equals(root))
				root = current.left;
			else if (parent.left.equals(current))
				parent.left = current.left;
			else
				parent.right = current.left;
		} else {
			// Caso 1 ou Caso 2
			Node nextNode = current.right == null ? current.left : current.right;
			if (current.equals(root))
				root = nextNode;
			else if (parent.left.equals(current))
				parent.left = nextNode;
			else
				parent.right = nextNode;
		}

		return true;
	}

	@Override
	public boolean delete(K key) {
		return deleteByMerging(key);
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	@Override
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node + " ");
			inOrder(node.right);
		}
	}

	@Override
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node + " ");
		}
	}

	public void levelOrder() {
		Queue queue = new LinkedQueue<K>();
		queue.enqueue(root.key);
		levelOrder(root, queue);
		;
	}

	private void levelOrder(Node node, Queue queue) {
		if (node != null) {
			levelOrder(node.right, queue);
			levelOrder(node.left, queue);
			if (node.left != null)
				queue.enqueue(node.left.key);
			if (node.right != null)
				queue.enqueue(node.right.key);

		}
		while (!queue.isEmpty())
			System.out.println(queue.dequeue());

	}

	//Conta todos os nós da árvore.
	public int countNodes() {
		if (isEmpty())
			return 0;
		return countNodes(root);
	}

	private int countNodes(Node node) {
		if (node == null)
			return 0;
		return countNodes(node.left) + countNodes(node.right) + 1;
	}
	
	//Conta rodas as folhas da árvore.
	public int countLeaves() {
		if (isEmpty())
			return 0;
		return countLeaves(root);
	}

	private int countLeaves(Node node) {
		if (node == null)
			return 0;
		return countLeaves(node.left) + countLeaves(node.right) + ((node.isLeaf()) ? 1 : 0);
	}

	//Conta os nós internos da árvore.	
	public int countInternalNodes() {
		if (isEmpty())
			return 0;
		return countInternalNodes(root);
	}

	private int countInternalNodes(Node node) {
		if (node == null)
			return 0;
		return countInternalNodes(node.left) + countInternalNodes(node.right)
				+ ((!node.isLeaf() && !node.equals(root)) ? 1 : 0);
	}

	//Retorna o grau de um nó específico.
	public int degree(K key) {
		if (search(key) == null)
			return -1;
		Node node = searchN(key);
		return degree(node);
	}

	private int degree(Node node) {
		if (node.left == null && node.right == null)
			return 0;
		else if (node.right != null && node.left != null)
			return 2;
		else
			return 1;
	}
	
	//Retorna o grau da árvore.
	public int degreeTree() {
		if (isEmpty())
			return -1;
		return degreeTree(root, 0);
	}

	private int degreeTree(Node node, int aux) {
		if (node.left == null && node.right == null) {
			return aux;
		} else if (node.left != null && node.right != null) {
			aux = 2;
			degreeTree(node.left, aux);
			degreeTree(node.right, aux);
		} else {
			aux = 1;
			if (node.left == null)
				degreeTree(node.right, aux);
			else
				degreeTree(node.left, aux);
		}
		return aux;
	}

	//Retorna a altura de um nó específico.
	public int height(K key) {
		if (isEmpty())
			return -1;
		if (search(key) == null)
			return -1;
		Node node = searchN(key);
		return height(node);
	}

	private int height(Node node) {
		if (node != null) {
			int rh, lh;
			rh = height(node.right);
			lh = height(node.left);
			if (rh > lh)
				return rh + 1;
			else
				return lh + 1;
		}
		return -1;

	}
	
	//Retorna a altura da árvore.
	public int heightTree() {
		return heightTree(root);
	}

	private int heightTree(Node node) {
		if (node != null) {
			int rh, lh;
			rh = heightTree(node.right);
			lh = heightTree(node.left);
			if (rh > lh)
				return rh + 1;
			else
				return lh + 1;
		}
		return -1;

	}

	//Retorna a profundidade do nó específicado.
	public int depth(K key) {
		if (search(key) == null)
			return -1;
		return depthP(key);
	}

	private int depthP(K key) {
		return height(root) - height(searchN(key));
	}

	//Retorna os ancestrais de um nó específico.
	public String ancestors(K key) {
		if (isEmpty())
			return null;
		if (search(key) == null)
			return null;
		return ancestors(root, key, "");
	}

	private String ancestors(Node node, K key, String aux) {
		 if (node.key.compareTo(key) == 0) 
			return aux += " " + node.key;	
		aux += " " + node.key;
		return ancestors(node.next(key), key, aux);
	}
	
	//Retorna os descendentes de um nó específico.
	public String descendents(K key) {
		if (isEmpty())
			return null;
		if (searchN(key) == null)
			return null;
		Node node = searchN(key);
		SinglyLinkedList<K> list = new SinglyLinkedList<K>();
		return descendents(node, list);

	}

	private String descendents(Node node, SinglyLinkedList<K> list) {
		if (node.isLeaf()) {
			list.insertLast(node.key);
			return list.toString();
		} else if (node.right == null && node.left != null) {
			list.insertLast(node.key);
			return descendents(node.left, list);
		} else if (node.left == null && node.right != null) {
			list.insertLast(node.key);
			return descendents(node.right, list);
		} else {
			if (list.search(node.key) == (int) node.key) {
				descendents(node.left, list);
				descendents(node.right, list);
			} else {
				list.insertLast(node.key);
				descendents(node.right, list);
				descendents(node.left, list);
			}
		}
		return list.toString();
	}

}