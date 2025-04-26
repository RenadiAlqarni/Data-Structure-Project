public class PhotoManager {
    
    LinkedList<Photo> photos;

   public PhotoManager()
   {
           photos = new LinkedList<Photo>();
   }

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

   public void deletePhoto(String path)
   {
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

   public LinkedList<Photo>  getPhotos()
   {
       return photos;
   }
}
