public class InvIndexPhotoManager {
    private final BST<LinkedList<Photo>> invertedIndex;

      public InvIndexPhotoManager() {
          this.invertedIndex = new BST<>();
      }

      // Adds a photo under the empty‐tag key and under each of its real tags
      public void addPhoto(Photo p) {
          // Always index under the "empty" tag
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

      // Helper: insert p into the list for tag, creating that list if needed
      private void indexTag(String tag, Photo p) {
          if (invertedIndex.findkey(tag)) {
              LinkedList<Photo> list = invertedIndex.retrieve();
              list.insert(p);
              // no need to call update() since list is mutated in place
          } else {
              LinkedList<Photo> list = new LinkedList<>();
              list.insert(p);
              invertedIndex.insert(tag, list);
          }
      }

      // Deletes all occurrences of a photo path from every tag list
      public void deletePhoto(String path) {
          // gather all keys first (so we don't modify the BST while iterating)
          String allKeys = invertedIndex.inOrder();
          if (allKeys.isEmpty()) return;

          String[] tags = allKeys.split(" AND ");
          for (String tag : tags) {
              if (!invertedIndex.findkey(tag)) continue;

              LinkedList<Photo> list = invertedIndex.retrieve();
              // traverse and remove matching photo
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

              // if list is now empty, drop the key; otherwise update its data
              if (list.getSize() == 0) {
                  invertedIndex.removeKey(tag);
              } else {
                  invertedIndex.update(tag, list);
              }
          }
      }
    

      // Expose the entire inverted index
      public BST<LinkedList<Photo>> getPhotos() {
          return invertedIndex;
      }
}