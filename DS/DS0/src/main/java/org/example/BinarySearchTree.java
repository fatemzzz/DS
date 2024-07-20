package org.example;

import java.util.ArrayList;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert a node into the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    // Inorder traversal of BST
    public void inorder() {
        inorderRec(root);
        for (int i=0; i< integers.size();i++){
            System.out.println(integers.get(i).data);
        }
    }
    ArrayList<Node> integers=new ArrayList<>();

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            integers.add(root);
            //System.out.print(root.data + " ");
            inorderRec(root.right);
        }

    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes into BST
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Print inorder traversal
        bst.inorder();
    }
}
