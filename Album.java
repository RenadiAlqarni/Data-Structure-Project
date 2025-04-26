public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int NbComps;

    public Album(String name, String condition, PhotoManager manager)
    {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        NbComps =0 ;
    }

    // Return the name of the album
    public String getName()
    {
        return name;
    }
    public String getCondition()
    {
        return condition;
    }

    // Return the manager
    public PhotoManager getManager()
    {
        return manager;
    }
    

    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> Rphotos = new LinkedList<Photo>();
        LinkedList<Photo> photos1 = manager.getPhotos();
         if (!photos1.empty()) {
            photos1.findFirst();
            do {
                Photo p = photos1.retrieve();
                Rphotos.insert(new Photo(p.getPath(), p.getTags()));
                
                if (photos1.last()) break;
                photos1.findNext();
            } while (true);
        }
        NbComps = 0;
        if (this.condition.compareTo("")!=0) {
            String[] terms = condition.split(" AND ");
            LinkedList<Photo> filtered = new LinkedList<Photo>();
             if (!Rphotos.empty()) {
                Rphotos.findFirst();
                do {
                    Photo curr = Rphotos.retrieve();
                    if (allAvilable(curr.TotalTags, terms)) {
                        filtered.insert(curr);
                    }
                    if (Rphotos.last()) break;
                    Rphotos.findNext();
                } while (true);
            }
            return filtered;
        }
        return Rphotos;
    }
    

    public int getNbComps() {
        return NbComps;
    }
    
    private boolean allAvilable(LinkedList<String> AllTags, String[] terms) {
        if (AllTags.empty()) {
            return false;
        }
        
        for (String term : terms) {
            boolean found = false;
            AllTags.findFirst();
            
            do {
                NbComps++;
                if (AllTags.retrieve().equalsIgnoreCase(term)) {
                    found = true;
                    break;
                }
                
                if (AllTags.last()) break;
                AllTags.findNext();
            } while (true);
            
            if (!found) {
                return false;
            }
        }
        
        return true;
    }
}
