public class Photo {
    
    private String path;
    LinkedList<String> TotalTags = new LinkedList<>();
   
  // Constructor
   public Photo(String path, LinkedList<String> tags)
   {
       this.path = path;
          
       if (! tags.empty())
       {   
          tags.findFirst();
          while ( !tags.last())
          {
            TotalTags.insert(tags.retrieve());
              tags.findNext();
          }
          TotalTags.insert(tags.retrieve());
       }
  }
   
  // Return the path (full file name) of the photo. A photo is uniquely identified by its path.
  public String getPath()
  {
          return path;
  }
  
  public LinkedList<String> getTags()
  {
      LinkedList<String> tagsNew = new LinkedList<String>();
      
      if (! TotalTags.empty())
      {
        TotalTags.findFirst();
          while ( ! TotalTags.last())
          {
              tagsNew.insert(TotalTags.retrieve());
              TotalTags.findNext();
          }
          tagsNew.insert(TotalTags.retrieve());
      }
      return tagsNew;
  }

@Override
public String toString() { 
  
  String str = "Photo{" + "path=" + path + ", TotalTags=" ;
  
  TotalTags.findFirst();
  while ( ! TotalTags.last())
  {
      str += TotalTags.retrieve().toString() + "; ";
      TotalTags.findNext();
  }
  
  str += TotalTags.retrieve().toString()  + "}";
  return str ;
}
}
