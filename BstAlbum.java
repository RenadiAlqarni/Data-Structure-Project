public class BstAlbum {
    private String name;
    private String condition;
    private InvIndexPhotoManager invmanager;
    private int NbComps;

    public BstAlbum(String name, String condition, InvIndexPhotoManager manager)
    {
        this.name = name;
        this.condition = condition;
        this.invmanager = manager;
        NbComps =0 ;
    }

    public String getName()
    {
        return name;
    }

    public String getCondition()
    {
        return condition;
    }

    public InvIndexPhotoManager getManager()
    {
        return invmanager;
    }

    public LinkedList<Photo> getPhotos(){
        BST<LinkedList<Photo>> photosBST = invmanager.getPhotos(); 
        LinkedList<Photo> AllPhotos = new LinkedList<Photo>();
        NbComps =0 ;
        String [] tags;
        
        if (this.condition.compareTo("") != 0)
            tags = condition.split(" AND ");
        else
            tags = photosBST.inOrder().split(" AND ");

        for ( int i = 0 ; i < tags.length ; i++){
            if ( photosBST.findkey(tags[i]) == true){
                if (i == 0){
                    LinkedList<Photo > TagCon = photosBST.retrieve();
                    TagCon.findFirst();
                    while ( ! TagCon.last()){
                        AllPhotos.insert(TagCon.retrieve());
                        TagCon.findNext();
                    }
                    AllPhotos.insert(TagCon.retrieve());
                }
                else{
                    if (condition.compareToIgnoreCase("") != 0 )
                        AllPhotos  = EmptyCondition ( AllPhotos , photosBST.retrieve());
                    else
                        AllPhotos  = intersectSwap ( AllPhotos , photosBST.retrieve()); }
            }
            else{
                AllPhotos = new LinkedList<Photo>();
                break;
            }
        }
        return AllPhotos;
    }
   

    public int getNbComps()
    {
        return NbComps;
    }

    
    private LinkedList<Photo> EmptyCondition( LinkedList<Photo> a ,LinkedList<Photo> b)
    {
        LinkedList<Photo> result = new LinkedList<Photo>();
        
        if (! b.empty())
        {
            b.findFirst();
            while (! b.last())
            {
                if (! a.empty())
                {
                    boolean found = false;
                    a.findFirst();
                    while (! a.last() && ! found)
                    {
                        if (b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()))
                        {
                            NbComps++;
                            found = true;
                        }
                        a.findNext();
                    }
                    if (! found && b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()) )
                    {
                        NbComps++;
                        found = true;
                    }
                    if (found )
                        result.insert(b.retrieve());
                        
                }
                b.findNext();
            }
            
            boolean found = false;
            a.findFirst();
            while (! a.last() && ! found)
            {
                if (b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()))
                {
                    NbComps++;
                    found = true;
                }
                a.findNext();
            }
            if (! found && b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()))
            {
                NbComps++;
                found = true;
            }
            if (found )
                result.insert(b.retrieve());
                              
        }
        return result;
    }
    private LinkedList<Photo> intersectSwap ( LinkedList<Photo> a ,LinkedList<Photo> b)
    {
        if (! b.empty())
        {
            b.findFirst();
            while (! b.last())
            {
                if (! a.empty())
                {
                    boolean found = false;
                    a.findFirst();
                    while (! a.last() && ! found)
                    {
                        if (b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()) )
                        {
                            NbComps++;
                            found = true;
                        }
                        a.findNext();
                    }
                    if (! found && b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()) )
                    {
                        NbComps++;
                        found = true;
                    }
                    if (!found )
                        a.insert(b.retrieve());
                        
                }
                b.findNext();
            }
            
            boolean found = false;
            a.findFirst();
            while (! a.last() && ! found)
            {
                if (b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()))
                {
                    NbComps++;
                    found = true;
                }
                a.findNext();
            }
            if (! found && b.retrieve().getPath().equalsIgnoreCase(a.retrieve().getPath()))
            {
                NbComps++;
                found = true;
            }
            if (!found )
                a.insert(b.retrieve());
                              
        }
        return a;
    }

    
   
}
