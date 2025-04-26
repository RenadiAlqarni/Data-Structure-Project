public class PhotoManager {
    
    LinkedList<Photo> photos;

    // Constructor
   public PhotoManager()
   {
           photos = new LinkedList<Photo>();
   }
   
   // Add a photo
   public void addPhoto(Photo p)
   {
    if (photos.empty()) 
    photos.insert(p);
    else{
    String PathName=p.getPath();
    photos.findFirst();
    while ( !photos.last())
    {
       if (photos.retrieve().getPath().compareToIgnoreCase(PathName)== 0)
           return ;

           photos.findNext();
    }

    if (photos.retrieve().getPath().compareToIgnoreCase(PathName)== 0)
       return ;

              photos.insert(p);
            }
   }
   
   // Delete a photo
   public void deletePhoto(String path)
   {
      // if (! this.IsPhototAvailable(path, photos))
       //    return;
   
       if ( photos.empty())
       return;
       else
        {
           boolean flage = false;
       
           photos.findFirst();
           while (!flage && !photos.last() )
           {
               Photo p = photos.retrieve();
               if (p.getPath().compareToIgnoreCase(path) == 0)
               {
                flage = true; 
                   photos.remove();
               }
               photos.findNext();
           }
           
           if (!flage ) {
               Photo p = photos.retrieve();
               if (p.getPath().compareToIgnoreCase(path) == 0){
                photos.remove();
                flage = true;      
 }}
}
   }

   // Return all managed photos
   public LinkedList<Photo>  getPhotos()
   {
       return photos;
   }
}