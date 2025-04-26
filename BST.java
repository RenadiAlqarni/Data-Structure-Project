public class BST<T> {
        public class BSTNode <T> {
            public String key;
            public T data;
            public BSTNode<T> left, right;
                
            public BSTNode(String k, T val) {
                    key = k;
                    data = val;
                    left = right = null;
            }

            public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
                    key = k;
                    data = val;
                    left = l;
                    right = r;
            }
        }

        BSTNode<T> root, current;
        String AllKeys;
        
        public BST() {
                root = current = null;
        }

        public boolean empty() {
                return root == null;
        }

        public boolean full() {
                return false;
        }

        public T retrieve () {
                return current.data;
        }

        public boolean findkey(String  tkey) {
                BSTNode<T> p = root;
                BSTNode<T> q = root;

                if(empty())
                        return false;

                while(p != null) {
                        q = p;
                        if(p.key.compareToIgnoreCase(tkey) == 0) {
                                current = p;
                                return true;
                        }
                        else if(tkey.compareToIgnoreCase(p.key) < 0 )
                                p = p.left;
                        else
                                p = p.right;
                }
                current = q;
                return false;
        }

        public boolean insert(String k, T val) {
                BSTNode<T> p;
                BSTNode<T> q = current;

                if(findkey(k)) {
                        current = q;  
                        return false; 
                }

                p = new BSTNode<T>(k, val);
                if (empty()) {
                        root = current = p;
                        return true;
                }
                else {
                        if (k.compareToIgnoreCase(current.key) < 0)
                                current.left = p;
                        else
                                current.right = p;
                        current = p;
                        return true;
                }
        }

        public boolean remove_key (String tkey){
                Boolean removed = new Boolean(false);
                BSTNode<T> p;
                p = remove_aux(tkey, root, removed);
                current = root = p;
                return removed;
        }

        private BSTNode<T> remove_aux(String  key, BSTNode<T> p, boolean flag) {
                BSTNode<T> q, child = null;
                if(p == null)
                        return null;
                if(key.compareToIgnoreCase(p.key) <0)
                        p.left = remove_aux(key, p.left, flag); 
                else if(key.compareToIgnoreCase(p.key) > 0)
                        p.right = remove_aux(key, p.right, flag); 
                else {
                        flag = true;
                        if (p.left != null && p.right != null){ 
                                q = find_min(p.right);
                                p.key = q.key;
                                p.data = q.data;
                                p.right = remove_aux(q.key, p.right, flag);
                        }
                else {
                                if (p.right == null) 
                                        child = p.left;
                                else if (p.left == null) 
                                        child = p.right;
                                return child;
                        }
                }
                return p;
        }

        private BSTNode<T> find_min(BSTNode<T> p)
        {
                if(p == null)
                        return null;

                while(p.left != null){
                        p = p.left;
                }

                return p;
        }

        public boolean update(String key, T data)
        {
                    remove_key(current.key);
                    return insert(key, data);
        }

        //Method removeKey: iterative  
        public boolean removeKey(String k) {
            // Search 
            String  k1 = k;      
            BSTNode<T> p = root;      
            BSTNode<T> q = null;    

            while (p != null) 
            {
                if (k1.compareToIgnoreCase(p.key) < 0) 
                {
                    q =p;
                    p = p.left;
                }
                else if (k1.compareToIgnoreCase(p.key) >0) 
                {
                    q = p;
                    p = p.right;
                } 
                else { 
                    if ((p.left != null) && (p.right != null)) 
                    { 
                            BSTNode<T> min = p.right;
                            q = p;
                            while (min.left != null) 
                            {
                                q = min;
                                min = min.left;
                            }
                            p.key = min.key;               
                            p.data = min.data;
                            k1 = min.key;
                            p = min;
                           
                    }            
                    if (p.left != null) 
                    { 
                        // One child
                        p = p.left;
                    } 
                    else 
                    { 
                       
                        p = p.right;
                    }

                    if (q == null) 
                    { 
                        root = p;
                    } 
                    else 
                    {
                        if (k1.compareToIgnoreCase(q.key) < 0) 
                        {
                            q.left = p;
                        } 
                        else 
                        {
                            q.right = p;
                        }
                    }
                    current = root;
                    return true;
                }
            }
            return false; 
        }

        public String inOrder ()
         {
             AllKeys = "" ;
              if ( root != null)
                  inorder( root );
              return AllKeys;

         }

         private void inorder(BSTNode<T> p )
         {
             if ( p.left !=null)
                 inorder ( p.left);
             if (AllKeys == "")
                 AllKeys = p.key;
             else
                  AllKeys += " AND " + p.key ;
             if ( p.right !=null)
                 inorder ( p.right);
         }
}
