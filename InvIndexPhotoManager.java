public class InvIndexPhotoManager {
    private final BST<LinkedList<Photo>> invertedIndex;

      public InvIndexPhotoManager() {
          this.invertedIndex = new BST<>();
      }

      public void addPhoto(Photo p) {
  
          indexTag("", p);

          LinkedList<String> tags = p.getTags();
          if (tags.empty()) return;

          tags.findFirst();
          while (true) {
              indexTag(tags.retrieve(), p);
              if (tags.last())
               break;
              tags.findNext();
          }
      }

      private void indexTag(String tag, Photo p) {
          if (invertedIndex.findkey(tag)) {
              LinkedList<Photo> list = invertedIndex.retrieve();
              list.insert(p);
          } else {
              LinkedList<Photo> list = new LinkedList<>();
              list.insert(p);
              invertedIndex.insert(tag, list);
          }
      }

      public void deletePhoto(String path) {
          String allKeys = invertedIndex.inOrder();
          if (allKeys.isEmpty()) return;

          String[] tags = allKeys.split(" AND ");
          for (String tag : tags) {
              if (!invertedIndex.findkey(tag)) continue;

              LinkedList<Photo> list = invertedIndex.retrieve();
              if (!list.empty()) {
                  list.findFirst();
                  while (true) {
                      Photo curr = list.retrieve();
                      if (curr.getPath().equalsIgnoreCase(path)) {
                          list.remove();
                          break;
                      }
                      if (list.last()) break;
                      list.findNext();
                  }
              }

              if (list.getSize() == 0) {
                  invertedIndex.removeKey(tag);
              } else {
                  invertedIndex.update(tag, list);
              }
          }
      }
    
      public BST<LinkedList<Photo>> getPhotos() {
          return invertedIndex;
      }
}
