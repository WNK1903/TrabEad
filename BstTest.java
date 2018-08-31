package bstPackage;

import java.awt.HeadlessException;

public class BstTest {

	public static void main(String[] args) {
		BinarySearchTreeADT<Integer, Integer> bstCompleta = new BinarySearchTree<>();
		BinarySearchTreeADT<Integer, Integer> bstDegenerada = new BinarySearchTree<>();
		BinarySearchTreeADT<Integer, Integer> bstVazia = new BinarySearchTree<>();
		
		bstCompleta.insert(466, 1);
		bstCompleta.insert(100, 1);
		bstCompleta.insert(244, 1);
		bstCompleta.insert(557, 1);
		bstCompleta.insert(478, 1);
		
		bstCompleta.insert(699, 1);
		bstCompleta.insert(42, 1);
		bstCompleta.insert(22, 1);
		bstCompleta.insert(21, 1);
		bstCompleta.insert(20, 1);
		bstCompleta.insert(40, 1);
		
		
		
		
		System.out.println(bstCompleta.toString());
		System.out.println("Quantidade de nós: " + bstCompleta.countNodes());
		System.out.println("Nós internos: " + bstCompleta.countInternalNodes());
		System.out.println("Folhas: " + bstCompleta.countLeaves());
		System.out.println("Grau do nó 9: " + bstCompleta.degree(9));
		System.out.println("Grau da árvore: " + bstCompleta.degreeTree());
		System.out.println("Altura do nó 7: " + bstCompleta.height(7));
		System.out.println("Altura da árvore: " + bstCompleta.heightTree());
		System.out.println("Profundidade do nó 3: " + bstCompleta.depth(1281));
		System.out.println("Ancestrais do nó 557: " + bstCompleta.ancestors(699));
		System.out.println("Descendentes do nó 557: " + bstCompleta.descendents(557));
	
	}

}
