package bstPackage;

/**
 * Implementacaoo de uma lista linear com armazenamento dinamico, baseado em nos
 * singularmente encadeados (encadeamento somente em um sentido).
 */
public class SinglyLinkedList<E> implements List<E> {
	Node<E> head;
	Node<E> tail;
	int numElements;

	/**
	 * Constrói uma lista inicialmente vazia.
	 */
	public SinglyLinkedList() {
		head = tail = null;
		numElements = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#numElements()
	 */
	public int numElements() {
		return numElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#isEmpty()
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#isFull()
	 */
	public boolean isFull() {
		// uma lista com alocação dinâmica nunca estará cheia!
		return false;
	}

	/**
	 * Insere um novo elemento na posicaoo inicial da lista.
	 * 
	 * @param element
	 *            O elemento a ser inserido
	 */
	public void insertFirst(E element) {
		// cria um novo nó e o torna o novo "head"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	/**
	 * Insere um novo elemento no final da lista.
	 * 
	 * @param element
	 *            O elemento a ser inserido
	 */
	public void insertLast(E element) {
		// cria um novo nó e o torna o novo "tail"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			tail = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	public void insert(E element, int pos) {
		// verifica se a posição é valida
		if (pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: inserção no início...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou inserção no no final
			insertLast(element);
		else { // caso geral: inserção no meio da lista
				// localiza o nó imediatamente anterior a posição
				// onde o novo será inserido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();

			// cria um novo nó e o posiciona logo após "prev",
			// ajustando os apontamentos e o total de elementos
			Node<E> newNode = new Node<E>(element);
			newNode.setNext(prev.getNext());
			prev.setNext(newNode);
			numElements++;
		}
	}

	/**
	 * Remove o primeiro elemento da lista.
	 * 
	 * @return O elemento removido
	 */
	public E removeFirst() {
		// verifica se há pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma referência temporária ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else
			// ...se não, o segundo elemento passa a ser o "head"
			head = head.getNext();

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	/**
	 * Remove o último elemento da lista.
	 * 
	 * @return O elemento removido
	 */
	public E removeLast() {
		// verifica se há pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma referência temporária ao elemento sendo removido
		E element = tail.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...se não, é necessário percorrer o encadeamento
			// até chegar ao nó imediatamente anterior ao úlltimo...
			Node<E> prev = head;
			while (prev.getNext() != tail)
				prev = prev.getNext();

			// ...para poder torna-lo o novo "tail"
			tail = prev;
			prev.setNext(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#remove(int)
	 */
	public E remove(int pos) {
		// verifica se a posição é válida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: remoção do ínicio...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements - 1) // ... ou remoção do final
			return removeLast();
		else { // caso geral: remoção do meio da lista
				// localiza o nó imediatamente anterior à posição
				// de onde o elemento será removido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();

			// guarda uma ref. temporária ao elemento sendo removido
			E element = prev.getNext().getElement();

			// ajusta o encadeamento "pulando" o nó sendo removido
			prev.setNext(prev.getNext().getNext());

			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#get(int)
	 */
	public E get(int pos) {
		// verifica se a posição é válida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento até chegar ao elemento
		Node<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();

		return current.getElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unisinos.prog2lab2.List#search(java.lang.Object)
	 */
	public int search(E element) {
		// percorre o encadeamento até encontrar o elemento
		Node<E> current = head;
		int i = 0;
		while (current != null) {
			if (element.equals(current.getElement()))
				return i;
			i++;
			current = current.getNext();
		}

		// se chegar até aqui, é porque não encontrou
		return -1;
	}

	/**
	 * Retorna uma representação String da lista.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";

		Node<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}

	public void addAfter(E element, int pos) {
		if (pos < 0 || pos > numElements()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		for (int i = 0; i < pos; i++) {
			current = current.getNext();
		}
		Node<E> newNode = new Node<>(element);
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		numElements++;

	}

	public void addBefore(E element, int pos) {
		if (pos < 0 || pos > numElements()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		for (int i = 0; i < pos - 1; i++) {
			current = current.getNext();
		}
		Node<E> newNode = new Node<>(element);
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		numElements++;

	}

	public SinglyLinkedList<E> split(int divisor) {
		if (divisor < 0 || divisor > numElements) {
			return null;
		}
		SinglyLinkedList<E> lista = new SinglyLinkedList<>();

		int i = 1;
		while (i <= divisor) {
			lista.insertLast(removeFirst());
			i++;

		}
		return lista;

	}

}