import javax.lang.model.util.ElementScanner6; //left in from author's code

// BinarySearchTree class 
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * 
     * @param element the item to insert.
     */
    public void insert(AnyType element) //main calls this
    {
        root = insert(element, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x ) //main calls this
    {
        root = remove( x, root );
    }

    //checks if tree is empty, if not, sends in root to 
    //recursively check the amount of nodes
    public int nodeCount( ) //main calls this
    {
        if( isEmpty( ) )
            return 0;
        return nodeCount( root );
    }

    /**
     * Rotates the tree right
     * @param i is the element to rotate at
     */
    private void rotateRight(AnyType i) //main calls this
    {
        if(contains(i))
            root = rotateRight(i, root);
        else    
            System.out.println("Nothing to rotate");
        
    }

    /**
     * Rotates the tree left
     * @param i is the element to rotate at
     */
    private void rotateLeft(AnyType i) //main calls this
    {
        if(contains(i))
            root = rotateLeft(i, root);
        else    
            System.out.println("Nothing to rotate");
        
    }

    // checks if tree is empty, if not, sends in root to
    //recursively check the amount of nodes
    public boolean isFull( ) //main calls this
    {
        if( isEmpty( ) )
        {
            System.out.println("Tree is empty");
            return true; //empty trees are full trees
        }
            
        return isFull( root );
    }

    //calls the author's height function
    public int height( ) //main calls this
    {
        if( isEmpty( ) )
        {
            System.out.println("There is no height on empty tree");
            throw new UnderflowException( );
        }
        return height( root );
    }


    /**
     * calls the function compareStructure(t's root, p's root) to see if they have the same structure
     * @param t the first tree
     * @param p the second tree
     * @return if they are equal or not
     */
    private boolean compareStructure(BinaryNode<AnyType> t) //main calls this
    {
        if(t == null && isEmpty())
        {
            return true; //empty trees have the same structure (both being none)
        }
        else if(t == null|| isEmpty())
        {
            return false; //only one of the trees are empty
        }
        else
        {
            return compareStructure(t, root); //neither tree is empty
        }
    }

    /**
     * calls the function equals(t's root, p's root) to see if they have the same structure
     * @param t the first tree
     * @param p the second tree
     * @return if they are equal or not
     */
    private boolean equals(BinaryNode<AnyType> t) 
    {
        if(t == null && isEmpty())
        {
            return true; //they are both empty and therefore equal
        }
        else if(t == null || isEmpty())
        {
            return false; //only one of the trees are empty
        }
        else
        {
            return equals(t, root); //neither tree is empty
        }
    }

    /**
     * calls the function isMirror(t's root, m's root) to see if they have the same structure
     * @param t the first tree
     * @param m the second tree
     * @return if they are equal or not
     */
    private boolean isMirror(BinaryNode<AnyType> t) 
    {
        if(t == null && isEmpty())
        {
            return true; //they are both empty and therefore mirrors of each other
        }
        else if(t == null || isEmpty())
        {
            return false; //only one of the trees are empty, so they are not mirrors
        }
        else
        {
            return isMirror(t, root); //neither tree is empty
        }
    }

    /**
     * Find the smallest item in the tree.
     * 
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     * 
     * @return the largest item of null if empty.
     */
    public AnyType findMax() {
        if (isEmpty())
            throw new UnderflowException();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     * 
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * 
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() //main callls this
    {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Print the tree contents in sorted order and looks like a binary tree
     */
    public void printRealTree() //main calls this
    {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printRealTree(root, 0);
    }

    /**
     * Print the tree contents in sorted order and looks like a binary tree
     */
    public void printLevels() //main calls this
    {
        if (isEmpty())
            System.out.println("Empty tree");
        else
        {
            for(int i = 0; i <= height(); i++)
            {
                printLevels(root, i);
                System.out.println(""); //new level being printed, so start a new line
            }
        }
    }

    private void copy(BinaryNode<AnyType> t) // main calls this
    {
        //BinarySearchTree<AnyType> c = new BinarySearchTree<>();
        if (t == null)
        {
            System.out.println("There is nothing to copy");
            throw new UnderflowException();
            //return null;
        } else
            root = copy(t, root);
            //return c;
    }

    private void mirror(BinaryNode<AnyType> t) //main calls this
    {
        //BinarySearchTree<AnyType> m = new BinarySearchTree<>();
        if(t == null)
        {
            System.out.println("There is nothing to copy");
            throw new UnderflowException();
          //  return null;
        }
        else    
            root = mirror(t, root);
            //return m;
    }



    /**
     * Internal method to insert into a subtree.
     * 
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) 
    {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * 
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t; // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * 
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     * 
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * 
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true; // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * 
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) 
    {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     * Also prints it to resemble an actual binary tree turned -90degrees (root is on the left center)
     * @param t the node that roots the subtree.
     */
    private void printRealTree(BinaryNode<AnyType> t, int count) 
    {
        count++; //increment the amount of spaces 
        if (t != null) {
            printRealTree(t.right, count);
            System.out.println("");//add a new line
            for(int i = 0; i < count; i++) //set the amount of spaces
                System.out.print("        ");
            System.out.print(t.element);
            printRealTree(t.left, count);
        }
    }

        
    /**
     * Prints tree from the top
     * prints each level in same line
     * @param t the tree to print
     * @param level counts down to the level we need to print at this time
     */
    private void printLevels(BinaryNode<AnyType> t, int level)
    {
        if(t != null)
        {
            if(level == 0)
            {
                System.out.print(t.element + " ");
            }
            else
            {
                printLevels(t.left, level-1);
                printLevels(t.right, level-1);
            }
        }
            
    }



    /**
     * Internal method to compute height of a subtree.
     * 
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    private int nodeCount(BinaryNode<AnyType> t) {
        int count = 1;
        if (t.left != null)
            count += +nodeCount(t.left); // counts all the left nodes
        if (t.right != null)
            count += nodeCount(t.right); // counts all the right nodes
        return count;
    }

    // checks if the tree is full by checking if t has zero or two kids
    // if it comes back false for anything, then the tree is not full
    private boolean isFull(BinaryNode<AnyType> t) {
        // check if there are not any children
        if (t.left == null && t.right == null)
            return true; // the current node is a leaf
        else if (t.left == null || t.right == null)
            return false; // the current node has only one child

        // checks the left side and right side of the tree
        // if everything comes back true, then the tree is full
        if (isFull(t.left) && isFull(t.right)) 
        {
            return true; // left nor right are nulls, so continue checking
        } 
        else
            return false; // default case
    }

    /**
     * check two trees and see if they are built the same way 
     * Leafs should all be in the same spot; there should not be a null where another has a node
     * @param t first tree
     * @param p second tree
     * @return whether or not they match (true/false)
     */
    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> p) 
    {
        if(t.left == null && p.left == null)
        {
            return true; //both of the left children are a null so we have the same structure...so far
        }
        else if(t.left == null || p.left == null)
        {
            return false; //only one of the left children is a null which means the structure is different
        }

        //the left side is matching so far
        if(compareStructure(t.left, p.left))
        {
            //now check if we have right children
            if(t.right == null && p.right == null)
            {
                return true; //both of the right children are a null so we have the same structure...so far
            }
            else if(t.right == null || p.right == null)
            {
                return false; //only one of the right children is a null which means the structure is different
            }

            //we do have right children for both trees, so move to them and continue checking
            if(compareStructure(t.right, p.right))
            {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> p)
    {
        if(t == null && p == null)
        {
            return true; //they are both empty and therefore equal
        }
        else if(t == null || p == null)
        {
            return false; //only one of the trees are empty
        }
        else
         //first two ifs are base cases to check to see if one tree has a node in a place the other tree does not; if it does, then they aren't equal
         //if((one of the trees has a left equal null ==> true) && (not both trees are null)) //if both trees are null, there is a chance they are equal
         if((t.left == null || p.left == null) && !(t.left == null && p.left == null)) 
         {
             return false;
         }     
         //if((one of the trees has a right equal null ==> true) && (not both trees are null)) //if both trees are null, there is a chance they are equal
         else if((t.right == null || p.right == null) && !(t.right == null && p.right == null)) 
         {
             return false;
         }
          
         //if there are two children, we need to check both branches
         else if((t.left != null && p.left != null) && (t.right != null && p.right != null))
         {
             //check both branches
             if(equals(t.left, p.left) && equals(t.right, p.right))
             {
                 //if everything has been true up to this point, we will check if the elements here are equal
                if(t.element == p.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
             }
             else
                return false;
         }
         //there is no right child, so check the left side of the branch
         else if(t.left != null && p.left != null)
         {
            if(equals(t.left, p.left))
            {
                if(t.element == p.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
            }
            else
                return false;
         }
         //there is no left child
         else if(t.right != null && p.right != null)
         {
            if(equals(t.right, p.right))
            {
                if(t.element == p.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
            }
            else
                return false;
         }
         //there are no more children
         else
        {
            if(t.element == p.element) //if: the elements are equal
            {
                return true;
            }
            else 
                return false; 
        }    
    }

    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t, BinaryNode<AnyType> c) 
    {
        //function similar to the insert() function
        AnyType h = (AnyType) t.element; //set h equal to t's element
        if (c == null) //create a new node
            c = new BinaryNode<>(h, null, null);

        //if t has a left, then give c a left equal to that t
        if(t.left != null)
        {
            c.left = copy(t.left, c.left); 
        }

        //if t has a right, then give c a right equal to that t
        if(t.right != null)
        {
            c.right = copy(t.right, c.right);
        }
        return c; //return what we found
    }

    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t, BinaryNode<AnyType> m) 
    {
        //works very similar to copy()
        AnyType h = t.element; //set h equal to t's element
        if (m == null)
            m = new BinaryNode<>(h, null, null);

        //if t has a left element, put it on m's right side
        if(t.left != null)
        {
            m.right = mirror(t.left, m.right);
        }

        //if t has a right element, put it on m's left side
        if(t.right != null)
        {
            m.left = mirror(t.right, m.left);
        }
        return m; //return what we found
    }

    private boolean isMirror(BinaryNode<AnyType> t, BinaryNode<AnyType> m) 
    {
         //first two ifs are base cases to check to see if one tree has a node in a place the other tree does not; if it does, then they aren't equal
         //if((one of the trees has a null ==> true) && (not both trees are null)) //if both trees are null in their perspective places (t.left==m.right) (t.right==m.left), there is a chance they are equal
         if((t.left == null || m.right == null) && !(t.left == null && m.right == null)) 
         {
             return false;
         }     
         else if((t.right == null || m.left == null) && !(t.right == null && m.left == null)) 
         {
             return false;
         }
          
         //if there are two children, we need to check both branches
         else if((t.left != null && m.right != null) && (t.right != null && m.left != null))
         {
             //check both branches
             if(isMirror(t.left, m.right) && isMirror(t.right, m.left))
             {
                 //if everything has been true up to this point, we will check if the elements here are equal
                if(t.element == m.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
             }
             else
                return false;
         }
         //there is no right child for t, so check the left side of t and right side of m
         else if(t.left != null && m.right != null)
         {
            if(isMirror(t.left, m.right))
            {
                if(t.element == m.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
            }
            else
                return false;
         }
         //there is no left child for t
         else if(t.right != null && m.left != null)
         {
            if(isMirror(t.right, m.left))
            {
                if(t.element == m.element) //if: the elements are equal
                {
                    return true;
                }
                else 
                    return false; 
            }
            else
                return false;
         }
         //there are no more children
         else
        {
            if(t.element == m.element) //if: the elements are equal
            {
                return true;
            }
            else 
                return false; 
        }    
    }

    /**
     * rotates the tree right
     * @param i the value to rotate at
     * @param t the tree
     * @return the root node of t
     */
    private BinaryNode<AnyType> rotateRight(AnyType i, BinaryNode<AnyType> t) 
    {
        if (t == null) //given the code, this should never end up true, but this will catch any just in case
            return t;
        else if(t.element == i)
        {
            //if t does not have a left, throw an exception
            if(t.left == null)
            {
                throw new UnderflowException();
            }
                BinarySearchTree<AnyType> subTree = new BinarySearchTree<>(); //subTree of root
                subTree.copy(t.left); //set the temporary tree equal to the left side of the root
                t.left = subTree.root.right;
                subTree.root.right = t;

                return subTree.root;
        }
        
        int compareResult = i.compareTo(t.element);

        if (compareResult < 0) //if i is on the left side of the root
            t.left = rotateRight(i, t.left);
        else if (compareResult > 0) //if i is on the right side of the root
            t.right = rotateRight(i, t.right);
        return t;
    }

    /**
     * rotates the tree left
     * @param i the value to rotate at
     * @param t the tree
     * @return the root node of t
     */
    private BinaryNode<AnyType> rotateLeft(AnyType i, BinaryNode<AnyType> t) 
    {
        if (t == null) //given the code, this should never end up true, but this will catch any just in case
            return t;
        else if(t.element == i)
        {
            //if t does not have a right, throw an exception
            if(t.right == null)
            {
                throw new UnderflowException();
            }
                BinarySearchTree<AnyType> subTree = new BinarySearchTree<>(); //subTree of root
                subTree.copy(t.right); //set the temporary tree equal to the left side of the root
                t.right = subTree.root.left;
                subTree.root.left = t;

                return subTree.root;
        }
        
        int compareResult = i.compareTo(t.element);

        if (compareResult < 0) //if i is on the left side of the root
            t.left = rotateLeft(i, t.left);
        else if (compareResult > 0) //if i is on the right side of the root
            t.right = rotateLeft(i, t.right);
        return t;
    }
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element; // The data in the node
        BinaryNode<AnyType> left; // Left child
        BinaryNode<AnyType> right; // Right child
    }

    /** The tree root. */
    private BinaryNode<AnyType> root;

    // Test program
    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>(); //original tree the author created; used as the main tree
        BinarySearchTree<Integer> p = new BinarySearchTree<>(); //secondary tree used to compare equality and structure 
        BinarySearchTree<Integer> c = new BinarySearchTree<>(); //third tree to set equal to from copy method; c for copy
        BinarySearchTree<Integer> m = new BinarySearchTree<>(); //fourth tree to set equal to from mirror method; m for mirror
        // For testing purposes:
        // set up t's tree
        t.insert(25);
        t.insert(20);
        t.insert(30);
        t.insert(17);
        t.insert(22);
        t.insert(28);
        t.insert(33);
        t.insert(15);
        t.insert(18);
        t.insert(21);
        t.insert(23);
        t.insert(26);
        t.insert(29);
        t.insert(31);
        t.insert(35);

        // set up p's tree
        p.insert(25);
        p.insert(20);
        p.insert(30);
        p.insert(17);
        p.insert(22);
        p.insert(28);
        p.insert(33);
        p.insert(15);
        p.insert(18);
        p.insert(21);
        p.insert(23);
        p.insert(26);
        p.insert(29);
        p.insert(31);
        p.insert(35);

        ////////////////////////////// MY TESTS ///////////////////////

        // nodeCount
        System.out.println("There are " + t.nodeCount() + " nodes.");

        // isFull
        if (t.isFull())
        {
            if(!t.isEmpty())
                System.out.println("The tree is full");
            else 
                System.out.println("The tree is empty, but this is still considered a full tree.");
        }
        else
            System.out.println("The tree is not full");

        // compareStructure
        if (p.compareStructure(t.root)) 
        {
            System.out.println("The first tree and the second tree match in structure");
        }
        else
            System.out.println("The trees have different structures.");

        // equals
        if (t.equals(p.root)) 
        {
            System.out.println("The first tree and the second tree are equal");
        }
        else
            System.out.println("The trees are not equal.");

        //copy
        System.out.println("The first tree is:");
        t.printTree();
        System.out.println("");

        System.out.println("The second tree is (before copying):");
        c.printTree();
        c.copy(t.root);
        System.out.println("");

        System.out.println("Now the second tree is (after copy):");
        c.printTree();
        System.out.println("");


        //mirror
        System.out.println("The first tree is:");
        t.printTree();
        System.out.println("");

        System.out.println("The second tree before mirroring is:");
        m.printTree();
        m.mirror(t.root);
        System.out.println("");

        System.out.println("Now the second tree after mirroring is:");
        m.printTree();
        System.out.println("");


        //isMirror
        if (m.isMirror(t.root)) //m is automatically a mirror since it was created to be one
        {
            System.out.println("The first tree and the second tree are mirrors of each other");
        }
        else
            System.out.println("The trees are not mirrors of each oher.");

        if (c.isMirror(t.root)) //c is not necessarily a mirror since it was created to be a copy (it might be a mirror under certain (but uncommon) conditions)
        {
            System.out.println("The first tree and the second tree are mirrors of each other");
        }
        else
            System.out.println("The trees are not mirrors of each other.");

        //rotateRight
        System.out.println("Here is the first tree:");
        t.printRealTree();


        t.rotateRight(28);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Here is the second tree (Rotate Right):");
        t.printRealTree();

        t.rotateLeft(30);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Here is the third tree (Rotate Left):");
        t.printRealTree();
        System.out.println("");


        System.out.println("The height is " + t.height());

        //printing Methods
        System.out.println("Here is the tree printed level by level:");
        t.printRealTree();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Here is a tree printed up and down:");
        t.printLevels();
        

    }
}
